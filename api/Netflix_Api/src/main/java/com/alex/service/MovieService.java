package com.alex.service;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.vo.MovieVo;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieCreateDto movieCreateDto);

    MovieDto updateMovie(String id, MovieUpdateDto movieUpdateDto);

    List<MovieDto> getAllMovies();

    void publish(String id);

    void drop(String id);
}
