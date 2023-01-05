package com.alex.service.impl;

import com.alex.dto.*;
import com.alex.exception.BizException;
import com.alex.mapper.UserMapper;
import com.alex.model.Role;
import com.alex.model.User;
import com.alex.repository.RoleDao;
import com.alex.repository.UserDao;
import com.alex.service.EmailService;
import com.alex.service.SmsService;
import com.alex.service.UserService;
import com.alex.upload.BucketName;
import com.alex.utils.UpdateColumnUtils;
import com.alex.validation.EmailValidator;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.util.IOUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.alex.config.SecurityConfig.*;
import static com.alex.exception.ExceptionType.*;
import static org.apache.http.entity.ContentType.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    private RoleDao roleDao;

    private AmazonS3 amazonS3;

    private EmailService emailService;
    private EmailValidator emailValidator;
    private SmsService smsService;
    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            RoleDao roleDao,
            AmazonS3 amazonS3,
            EmailValidator emailValidator,
            EmailService emailService,
            SmsService smsService
            ) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.amazonS3 = amazonS3;
        this.emailValidator = emailValidator;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao
                .getByUsername(username)
                .orElseThrow(() -> new BizException(USER_NOT_FOUND));

        return user;
    }

    @Override
    public List<UserDto> getAll() {
        return userDao
                .findAll()
                .stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserRegisterDto userRegisterDto) {
        log.info("Begin adding user");
        validateEmail(userRegisterDto.getEmail());
        checkUsernameAndEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        User user = userMapper.createEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleDao.findByName("ROLE_USER").get();
        user.setRoles(List.of(role));
        User savedUser = userDao.save(user);
        log.info("user {} added", savedUser);
        UserDto userDto = userMapper.toDto(savedUser);
        return userDto;
    }

    @Override
    public UserDto getUser(String id) {
        User user = getUserPrivate(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(String id, UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getEmail() != null) validateEmail(userUpdateDto.getEmail());
        checkUsernameAndEmail(userUpdateDto.getUsername(), userUpdateDto.getEmail());

        User user = getUserPrivate(id);
        BeanUtils.copyProperties(userUpdateDto, user, UpdateColumnUtils.getNullPropertyNames(userUpdateDto));
        return userMapper.toDto(userDao.save(user));
    }


    @Override
    public void deleteUser(String id) {
        User user = getUserPrivate(id);
        userDao.delete(user);
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userDao.findAll(pageable).map(user -> userMapper.toDto(user));
    }

    @Override
    public Map<YearMonth, Integer> getMonthlyNewUsers() {
        Map<YearMonth, Integer> monthlyNewUsers =
                userDao
                        .findAll()
                        .stream()
                        .collect(Collectors
                                .groupingBy(
                                        user ->
                                                YearMonth.from(user.getCreatedAt()),
                                                TreeMap::new,
                                                Collectors.summingInt(user->1)));

        return monthlyNewUsers;
    }

    @Override
    public Map<YearMonth, Integer> getCumulativeTotalUsers() {
        Map<YearMonth, Integer> monthlyNewUsers = getMonthlyNewUsers();
        Map<YearMonth, Integer> monthlyTotalUsers = new TreeMap<>();

        int totalUser = 0;
        List<Integer> cumulativeUser = new LinkedList<>();
        for (Integer value : monthlyNewUsers.values()) {
            totalUser += value;
            cumulativeUser.add(totalUser);
        }
        int index = 0;
        for (YearMonth time: monthlyNewUsers.keySet()) {
            monthlyTotalUsers.put(time, cumulativeUser.get(index++));
        }
        return monthlyTotalUsers;
    }

    @Override
    public String createToken(TokenCreateDto tokenCreateDto) {
        User user = loadUserByUsername(tokenCreateDto.getUsername());
        if (!passwordEncoder.matches(tokenCreateDto.getPassword(), user.getPassword())) {
            throw new BizException(USER_PASSWORD_NOT_MATCH);
        }

        if (!user.isEnabled()) {
            throw new BizException(USER_NOT_ENABLED);
        }

        if (!user.isAccountNonLocked()) {
            throw new BizException(USER_LOCKED);
        }

        return JWT
                .create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = loadUserByUsername(authentication.getName());
        return userMapper.toDto(user);
    }

    @Override
    public void uploadProfileImg(String id, MultipartFile file) {
        // Check is image is empty
        if (file.isEmpty()) throw new BizException(FILE_NOT_FOUND);
        // Check if file is an image
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new BizException(ILLEGAL_FILE_TYPE);
        }
        // Check if the user exists
        User user = getUserPrivate(id);

        // Grab  metadata from file
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type",file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        // Store image in s3 and upload db
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            save(path,fileName, Optional.of(metaData), file.getInputStream());
            user.setProfilePic(fileName);
            userDao.save(user);
        } catch (Exception ex) {
            throw new BizException(UNABLE_TO_WRITE_FILE);
        }
    }

    @Override
    public byte[] downloadProfileImg(String id) {
        User user = getUserPrivate(id);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getId());

        return download(path, user.getProfilePic());
    }

    @Override
    @Transactional
    public UserDto register(UserRegisterDto userRegisterDto) {
        log.info("Begin to register user");
        validateEmail(userRegisterDto.getEmail());
        log.info("Email format is passed");
        checkUsernameAndEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        log.info("Username and email are valid");
        User user = userMapper.createEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleDao.findByName("ROLE_USER").get();
        user.setRoles(List.of(role));
        User savedUser = userDao.save(user);
        log.info("user {} registered", savedUser);
        emailService.sendCode(user.getEmail());
        UserDto userDto = userMapper.toDto(savedUser);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto verifyEmailInRegister(UserRegisterDto userRegisterDto, String code) {
        // verify user's email and username
        User user = userDao
                .getByEmail(userRegisterDto.getEmail())
                .orElseThrow(() -> new BizException(USER_NOT_FOUND));
        User user1 = userDao
                .getByUsername(userRegisterDto.getUsername())
                        .orElseThrow(() -> new BizException(USER_NOT_FOUND));
        if (!user.getUsername().equals(user1.getUsername())) throw new BizException(USERNAME_EMAIL_MISMATCH);
        emailService.verifyCode(userRegisterDto, code);
        user.setEnabled(true);
        userDao.save(user);
        log.info("User is enabled");
        return userMapper.toDto(user);
    }



    // Developing
    @Override
    public UserDto registerByPhone(SmsDto smsDto) {
        return null;
    }


    @Override
    public void sendSms(SmsDto smsDto) {
        smsService.sendSms(smsDto);
    }



    @Override
    public String login(UserLoginDto userLoginDto) {
        return null;
    }

    private void checkUsernameAndEmail(String username, String email) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            throw new BizException(USERNAME_DUPLICATE);
        }
        Optional<User> user1 = userDao.getByEmail(email);
        if (user1.isPresent()) {
            throw new BizException(EMAIL_DUPLICATE);
        }
    }

    private User getUserPrivate(String id) {
        User user = userDao.findById(id).orElseThrow(() -> new BizException(USER_NOT_FOUND));
        return user;
    }

    private void save(
            String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData,
            InputStream inputStream) {

        TransferManager tm = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .build();

        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach((key,value) -> metaData.addUserMetadata(key, value));
            }
        });
        try {
            //amazonS3.putObject(path, fileName, inputStream, metaData);
            tm.upload(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException ex) {
            throw new IllegalStateException("Failed to store file to S3", ex);
        }
    }

    private byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException ex ) {
            throw new BizException(FAILED_TO_DOWNLOAD);
        }
    }

    private void validateEmail(String email ) {
        if (!emailValidator.test(email)) throw new BizException(INVALID_EMAIL_FORMAT);
    }
}
