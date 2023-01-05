package com.alex.dto;

import jakarta.validation.constraints.NotBlank;

public class SmsDto {
    @NotBlank(message = "phone number can't be blank")
    private final String phoneNum;
    @NotBlank(message = "message can't be blank")
    private final String message;

    public SmsDto(String phoneNum, String message) {
        this.phoneNum = phoneNum;
        this.message = message;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsDto{" +
                "phoneNum='" + phoneNum + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
