package com.alex.model;

import com.alex.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

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
public class User extends AbstractEntity{
    @Column(nullable = false)
    private String username;

    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = {}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
