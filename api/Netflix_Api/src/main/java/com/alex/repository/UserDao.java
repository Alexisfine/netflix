package com.alex.repository;

import com.alex.dto.UserDto;
import com.alex.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserDao extends JpaRepository<User, String> {
    Optional<User> getByUsername(String username);

    Optional<User> getByEmail(String email);

    @Override
    Page<User> findAll(Pageable pageable);
}
