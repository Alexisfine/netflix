package com.alex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TokenCreateDto {
    @NotBlank(message = "username can't be blank")
    @Size(min = 4, max = 64, message = "username length should be between 4 and 64 characters")
    private String username;

    @NotBlank(message = "password can't be blank")
    @Size(min = 6, max = 64, message = "password length should be between 6 and 64 characters")
    private String password;
}
