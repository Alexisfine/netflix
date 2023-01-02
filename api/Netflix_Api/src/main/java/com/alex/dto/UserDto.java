package com.alex.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String nickname;
    private List<RoleDto> roles;
}
