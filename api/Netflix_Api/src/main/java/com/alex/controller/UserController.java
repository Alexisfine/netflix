package com.alex.controller;

import com.alex.dto.UserRegisterDto;
import com.alex.mapper.UserMapper;
import com.alex.service.UserService;
import com.alex.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    @GetMapping
    List<UserVo> getAllUsers() {
        return userService
                .getAll()
                .stream()
                .map(userDto -> userMapper.toVo(userDto))
                .collect(Collectors.toList());
    }

    @PostMapping
    UserVo register(@RequestBody UserRegisterDto userRegisterDto) {
        return userMapper.toVo(userService.addUser(userRegisterDto));
    }
}
