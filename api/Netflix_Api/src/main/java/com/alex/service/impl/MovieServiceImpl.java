package com.alex.service.impl;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.exception.BizException;
import com.alex.mapper.MovieMapper;
import com.alex.model.Movie;
import com.alex.repository.MovieDao;
import com.alex.service.MovieService;
import com.alex.utils.UpdateColumnUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.alex.enums.MovieStatus.*;
import static com.alex.exception.ExceptionType.*;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;
    private MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, MovieMapper movieMapper) {
        this.movieDao = movieDao;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDto createMovie(MovieCreateDto movieCreateDto) {
        Movie movie = movieMapper.createEntity(movieCreateDto);
        movie.setMovieStatus(DRAFT);
        movieDao.save(movie);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto updateMovie(String id, MovieUpdateDto movieUpdateDto) {
        Movie movie = getMovie(id);
        Movie updateMovie = movieMapper.updateEntity(movieUpdateDto);
        BeanUtils.copyProperties(updateMovie, movie, UpdateColumnUtils.getNullPropertyNames(updateMovie));
        return movieMapper.toDto(movieDao.save(movie));
    }


    @Override
    public List<MovieDto> getAllMovies() {
        return movieDao
                .findAll()
                .stream()
                .map(movie -> movieMapper.toDto(movie))
                .collect(Collectors.toList());
    }

    @Override
    public void publish(String id) {
        Movie movie = getMovie(id);
        movie.setMovieStatus(PUBLISHED);
        movieDao.save(movie);
    }

    @Override
    public void drop(String id) {
        Movie movie = getMovie(id);
        movie.setMovieStatus(DROPPED);
        movieDao.save(movie);
    }

    private Movie getMovie(String id) {
        return movieDao.findById(id).orElseThrow(() -> new BizException(MOVIE_NOT_FOUND));
    }
}
