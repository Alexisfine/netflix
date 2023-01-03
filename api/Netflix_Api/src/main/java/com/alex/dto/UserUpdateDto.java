package com.alex.dto;

import com.alex.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotBlank(message = "username can't be blank")
    @Size(min = 4, max = 64, message = "username length should be between 4 and 64 characters")
    private String username;

    @Email
    @Nullable
    private String email;

    private String nickname;

    private Gender gender;

    private String profilePic;
}
