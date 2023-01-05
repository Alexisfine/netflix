package com.alex.service;

import com.alex.dto.UserRegisterDto;

public interface EmailService {
    void sendCode(String email);

    void verifyCode(UserRegisterDto userRegisterDto, String code);
}
