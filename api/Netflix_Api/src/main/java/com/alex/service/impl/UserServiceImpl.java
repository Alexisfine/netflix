package com.alex.service.impl;

import com.alex.dto.UserDto;
import com.alex.dto.UserRegisterDto;
import com.alex.exception.BizException;
import com.alex.mapper.UserMapper;
import com.alex.model.User;
import com.alex.repository.UserDao;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alex.exception.ExceptionType.EMAIL_DUPLICATE;
import static com.alex.exception.ExceptionType.USERNAME_DUPLICATE;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
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
