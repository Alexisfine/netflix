package com.alex.service.impl;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.enums.MovieStatus;
import com.alex.exception.BizException;
import com.alex.mapper.MovieMapper;
import com.alex.model.Movie;
import com.alex.repository.MovieDao;
import com.alex.service.MovieService;
import com.alex.utils.UpdateColumnUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
    public Page<MovieDto> search(Pageable pageable) {
        return movieDao.findAll(pageable).map(movie -> movieMapper.toDto(movie));
    }

    @Override
    public void delete(String id) {
        movieDao.deleteById(id);
    }


    @Override
    public Page<MovieDto> userSearch(Pageable pageable) {
        return movieDao.findByMovieStatus(PUBLISHED, pageable).map(movie -> movieMapper.toDto(movie));
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

    @Override
    public MovieDto userGetMovieById(String id) {
        Movie movie = movieDao.findById(id).orElseThrow(() -> new BizException(MOVIE_NOT_FOUND));
        if (movie.getMovieStatus() != PUBLISHED) throw new BizException(MOVIE_NOT_FOUND);
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDto getRandomMovie(String type) {
        List<Movie> publishedMovies;
        if (type == null) {
        publishedMovies = movieDao.findByMovieStatus(PUBLISHED);

        } else {
             publishedMovies = movieDao.findByMovieStatusAndIsSeries(PUBLISHED, Boolean.parseBoolean(type));
        }
        Random random = new Random();
        int size = publishedMovies.size();
        if (size == 0) throw new BizException(MOVIE_NOT_FOUND);
        int num = random.nextInt(0, size);
        return movieMapper.toDto(publishedMovies.get(num));
    }

    @Override
    public List<MovieDto> userFindAll() {
        return movieDao
                .findByMovieStatus(PUBLISHED)
                .stream()
                .map(movie -> movieMapper.toDto(movie))
                .collect(Collectors.toList());
    }



    private Movie getMovie(String id) {
        return movieDao.findById(id).orElseThrow(() -> new BizException(MOVIE_NOT_FOUND));
    }
}
