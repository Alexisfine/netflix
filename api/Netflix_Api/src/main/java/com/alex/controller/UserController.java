package com.alex.controller;

import com.alex.dto.UserLoginDto;
import com.alex.dto.UserRegisterDto;
import com.alex.dto.UserUpdateDto;
import com.alex.mapper.UserMapper;
import com.alex.service.UserService;
import com.alex.vo.UserVo;
import com.electronwill.nightconfig.core.conversion.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    @GetMapping
    Page<UserVo> search(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.ASC) Pageable pageable){
        Page<UserVo> users = userService
                .search(pageable).map(userMapper::toVo);
        return users;
    }


    @PostMapping
    UserVo register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        UserVo userVo = userMapper.toVo(userService.addUser(userRegisterDto));
        System.out.println(userVo);
        return userVo;
    }

    @GetMapping("/{id}")
    UserVo getUser(@PathVariable String id) {
        return userMapper.toVo(userService.getUser(id));
    }

    @PutMapping("/{id}")
    UserVo updateUser(@PathVariable String id, @Validated @RequestBody UserUpdateDto userUpdateDto) {
        return userMapper.toVo(userService.updateUser(id, userUpdateDto));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
