package com.alex.repository;

import com.alex.enums.MovieStatus;
import com.alex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends JpaRepository<Movie, String> {
    @Override
    Optional<Movie> findById(String id);

    List<Movie> findByMovieStatus(MovieStatus status);

    List<Movie> findByMovieStatusAndIsSeries(MovieStatus status, Boolean isSeries);
}
