package com.alex.service;

import com.alex.dto.UserDto;
import com.alex.dto.UserRegisterDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto addUser(UserRegisterDto userRegisterDto);

}
