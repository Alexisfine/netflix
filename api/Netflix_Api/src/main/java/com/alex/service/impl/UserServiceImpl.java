package com.alex.service.impl;

import com.alex.dto.UserDto;
import com.alex.dto.UserRegisterDto;
import com.alex.exception.BizException;
import com.alex.mapper.UserMapper;
import com.alex.model.User;
import com.alex.repository.UserDao;
import com.alex.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public UserServiceImpl(UserDao userDao, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("begin finding user with username {}", username);
        User user = userDao
                .getByUsername(username)
                .orElseThrow(() -> new BizException(USER_NOT_FOUND));
        log.info("user is found in the db");
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
        checkUsernameAndEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        User user = userMapper.createEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.save(user);
        return userMapper.toDto(savedUser);
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

}
