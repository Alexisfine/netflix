package com.alex.repository;

import com.alex.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
