package com.alex.repository;

import com.alex.model.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ListDao extends JpaRepository<List, String> {
    Boolean existsByTitle(String title);

    @Override
    Page<List> findAll(Pageable pageable);

    @Query("select l from List l where (?1 is null or lower(l.type) = lower(?1)) " +
            "and (?2 is null or lower(l.genre) = lower(?2))")
    Page<List> findByTypeAndGenreWithPagination(String type, String genre, Pageable pageable);
}
