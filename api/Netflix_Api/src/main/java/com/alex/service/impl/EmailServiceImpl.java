package com.alex.service.impl;

import com.alex.dto.UserRegisterDto;
import com.alex.exception.BizException;
import com.alex.exception.ExceptionType;
import com.alex.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.alex.exception.ExceptionType.*;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private RedisTemplate<String, Object> redisTemplate;


    @Value("Alexxue1234@gmail.com")
    private String sendEmail;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, RedisTemplate redisTemplate) {
        this.javaMailSender = javaMailSender;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    public void sendCode(String email) {
        try {
            // set up email
            log.info("Init email sending");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("admin@netflix.com");
            Random random = new Random();
            int code = random.nextInt(100000,999999);
            message.setSubject("Netflix Email Code: " + code);
            message.setText("Your verification code is: " + code);

            // send email
            javaMailSender.send(message);
            log.info("Email sent");

            // Save info to redis
            String key = "code:" + email;
            redisTemplate.opsForValue().set(key, code +"", 15, TimeUnit.MINUTES);
            log.info("Email code is saved in redis");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Email sending unsuccessful");
            throw new BizException(EMAIL_SENDING_ERROR);
        }
    }

    @Override
    public void verifyCode(UserRegisterDto userRegisterDto, String code) {
        log.info("Begin verifying code");
        // Check if code is non expired
        String key = "code:" + userRegisterDto.getEmail();
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            log.error("Verification code expired");
            throw new BizException(VERIFICATION_CODE_EXPIRED);
        } else {

            if (code.equals(obj)) {
                log.info("Verification code is correct");
            }
            else {
                throw new BizException(VERIFICATION_CODE_INCORRECT);
            }
        }
    }
}























