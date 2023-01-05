package com.alex.controller;

import com.alex.dto.*;
import com.alex.mapper.UserMapper;
import com.alex.service.UserService;
import com.alex.vo.UserVo;
import com.amazonaws.services.s3.AmazonS3;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    private final AmazonS3 S3;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, AmazonS3 S3) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.S3 = S3;

    }

    @GetMapping
    @RolesAllowed("ADMIN")
    Page<UserVo> search(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.ASC) Pageable pageable){
        Page<UserVo> users = userService
                .search(pageable).map(userMapper::toVo);
        return users;
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    UserVo addUser(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        UserVo userVo = userMapper.toVo(userService.addUser(userRegisterDto));
        return userVo;
    }

    @GetMapping("/{id}")
    UserVo getUser(@PathVariable String id) {
        return userMapper.toVo(userService.getUser(id));
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    UserVo updateUser(@PathVariable String id, @Validated @RequestBody UserUpdateDto userUpdateDto) {
        return userMapper.toVo(userService.updateUser(id, userUpdateDto));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PostMapping("/register")
    UserVo register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        return userMapper.toVo(userService.register(userRegisterDto));
    }

    @PostMapping("/register/verify/email/{code}")
    UserVo verifyEmail(@Validated @RequestBody UserRegisterDto userRegisterDto, @PathVariable("code") String code) {
        return userMapper.toVo(userService.verifyEmailInRegister(userRegisterDto, code));
    }

    // Developing
//    @PostMapping("/register/phone")
//    public UserVo registerByPhone(@Validated @RequestBody SmsDto smsDto) {
//        return userMapper.toVo(userService.registerByPhone(smsDto));
//    }


    @PostMapping(
            path = "/{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadProfileImg(@PathVariable("id") String id, @RequestBody MultipartFile file) {
        userService.uploadProfileImg(id, file);
    }

    @GetMapping("/{id}/image/download")
    public byte[] downloadProfileImg(@PathVariable("id") String id) {
        return userService.downloadProfileImg(id);
    }

    @GetMapping("/me")
    UserVo me() {
        System.out.println(userService.getCurrentUser());
        return userMapper.toVo(userService.getCurrentUser());
    }


}
