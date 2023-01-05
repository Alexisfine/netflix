package com.alex.repository;

import com.alex.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, String> {
}
