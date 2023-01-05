package com.alex.service;

import com.alex.dto.SmsDto;

public interface SmsService {
    void sendSms(SmsDto smsDto);
}
