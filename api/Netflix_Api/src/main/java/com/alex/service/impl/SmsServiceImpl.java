package com.alex.service.impl;

import com.alex.config.TwilioConstants;
import com.alex.dto.SmsDto;
import com.alex.exception.BizException;
import com.alex.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.alex.exception.ExceptionType.INVALID_PHONE_NUMBER;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Override
    public void sendSms(SmsDto smsDto) {
        if (!isPhoneNumberValid(smsDto.getPhoneNum())) throw new BizException(INVALID_PHONE_NUMBER);
        log.info("Phone number is valid");
        PhoneNumber to = new PhoneNumber(smsDto.getPhoneNum());
        PhoneNumber from = new PhoneNumber(TwilioConstants.TRIAL_NUMBER);
        String message = smsDto.getMessage();
        MessageCreator creator = Message.creator(to, from, message);
        creator.create();
        log.info("Sms sent");
    }

    private boolean isPhoneNumberValid(String phoneNum) {
        // TODO: implement phone number validator
        return true;
    }
}
