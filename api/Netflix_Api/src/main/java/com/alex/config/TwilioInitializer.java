package com.alex.config;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.alex.config.TwilioConstants.*;

@Configuration
@Slf4j
public class TwilioInitializer {
    @Autowired
    public TwilioInitializer() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        log.info("Twilio initialized");
    }
}
