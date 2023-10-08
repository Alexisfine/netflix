package com.alex.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "username can't be blank")
    @Size(min = 4, max = 64, message = "username length should be between 4 and 64 characters")
    private String username;

    @NotBlank(message = "password can't be blank")
    @Size(min = 6, max = 64, message = "password length should be between 6 and 64 characters")
    private String password;

    @Email
    @NotBlank(message = "email can't be blank")
    private String email;

    private String nickname;

    private String gender;

}
