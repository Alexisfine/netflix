package com.alex.service;

import com.alex.dto.*;
import com.alex.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    void uploadProfileImg(String id, MultipartFile file);

    byte[] downloadProfileImg(String id);

    UserDto register(UserRegisterDto userRegisterDto);

    UserDto verifyEmailInRegister(UserRegisterDto userRegisterDto, String code);

    void sendSms(SmsDto smsDto);

    UserDto registerByPhone(SmsDto smsDto);

    Map<YearMonth,Integer> getMonthlyNewUsers();

    Map<YearMonth, Integer> getCumulativeTotalUsers();

    Long getTotalUsers();

    String createAdminToken(TokenCreateDto tokenCreateDto);
}
