package com.alex.service.impl;

import com.alex.dto.UserDto;
import com.alex.dto.UserLoginDto;
import com.alex.dto.UserRegisterDto;
import com.alex.dto.UserUpdateDto;
import com.alex.exception.BizException;
import com.alex.mapper.UserMapper;
import com.alex.model.User;
import com.alex.repository.UserDao;
import com.alex.service.UserService;
import com.alex.utils.UpdateColumnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alex.exception.ExceptionType.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
            //AuthenticationManager authenticationManager
            ) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        //this.authenticationManager = authenticationManager;
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
        checkUsernameAndEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        User user = userMapper.createEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.save(user);
        log.info("user {} added", savedUser);
        UserDto userDto = userMapper.toDto(savedUser);
        System.out.println(userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String id) {
        User user = getUserPrivate(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(String id, UserUpdateDto userUpdateDto) {
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

}
