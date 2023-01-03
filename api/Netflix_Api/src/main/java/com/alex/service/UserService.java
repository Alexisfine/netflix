package com.alex.service;

import com.alex.dto.*;
import com.alex.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

public interface UserService  extends UserDetailsService {
    List<UserDto> getAll();

    UserDto addUser(UserRegisterDto userRegisterDto);

    UserDto getUser(String id);

    UserDto updateUser(String id, UserUpdateDto userUpdateDto);

    void deleteUser(String id);

    String login(UserLoginDto userLoginDto);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateDto tokenCreateDto);

    UserDto getCurrentUser();
}
