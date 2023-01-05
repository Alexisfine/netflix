package com.alex.model;

import com.alex.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

@Entity(name = "User")
@Table(
        name = "user_table",
        uniqueConstraints = {
                @UniqueConstraint(name="user_table_username_uk", columnNames = "username"),
                @UniqueConstraint(name="user_table_email_uk", columnNames = "email")
        }
)
@Data
public class User extends AbstractEntity implements UserDetails{
    @Column(nullable = false)
    private String username;

    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private java.util.List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Boolean locked = false;

    @Column(nullable = true)
    private Boolean enabled = false;

    private String lastLoginIp;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date lastLoginTime;

    private String profilePic; // S3 key


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }



}
