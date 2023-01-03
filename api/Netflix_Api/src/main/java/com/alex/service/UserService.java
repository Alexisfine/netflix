package com.alex.service;

import com.alex.dto.UserDto;
import com.alex.dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAll();

    UserDto addUser(UserRegisterDto userRegisterDto);

}
