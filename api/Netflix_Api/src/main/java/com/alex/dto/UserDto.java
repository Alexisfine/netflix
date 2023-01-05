package com.alex.dto;

import com.alex.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String email;

    private String profilePic;

    private String id;

    private String username;

    private String nickname;

    private List<RoleDto> roles;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private LocalDateTime lastLoginTime;
}
