package com.alex.service;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.dto.UserDto;
import com.alex.vo.MovieVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Page<MovieDto> userSearch(Pageable pageable);

    MovieDto createMovie(MovieCreateDto movieCreateDto);

    MovieDto updateMovie(String id, MovieUpdateDto movieUpdateDto);

    List<MovieDto> getAllMovies();

    void publish(String id);

    void drop(String id);

    MovieDto userGetMovieById(String id);

    MovieDto getRandomMovie(String type);

    List<MovieDto> userFindAll();

    Page<MovieDto> search(Pageable pageable);
}
