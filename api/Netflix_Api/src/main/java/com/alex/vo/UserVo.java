package com.alex.vo;

import com.alex.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private String id;
    private String username;
    private String email;
    private String nickname;
    private List<RoleVo> roles;
    private Gender gender;
    private Boolean locked;
    private Boolean enabled;
    private String profilePic;

}
