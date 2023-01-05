package com.alex.controller;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.mapper.MovieMapper;
import com.alex.service.MovieService;
import com.alex.vo.MovieVo;
import com.alex.vo.UserVo;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService movieService;
    private MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public MovieVo createMovie(@Validated  @RequestBody MovieCreateDto movieCreateDto) {
        return movieMapper.toVo(movieService.createMovie(movieCreateDto));
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public MovieVo updateMovie(
            @PathVariable("id") String id,
             @Validated  @RequestBody MovieUpdateDto movieUpdateDto) {
        return movieMapper.toVo(movieService.updateMovie(id, movieUpdateDto));
    }

    // TODO change to page after adding search filter
    @GetMapping
    @RolesAllowed("ADMIN")
    public List<MovieVo> getAllMovies() {
        return movieService
                .getAllMovies()
                .stream()
                .map(movieDto -> movieMapper.toVo(movieDto))
                .collect(Collectors.toList());
    }

    // Make a movie public to users
    @PostMapping("/publish/{id}")
    @RolesAllowed("ADMIN")
    public void publishMovie(@PathVariable("id") String id) {
        movieService.publish(id);
    }

    @PostMapping("/drop/{id}")
    @RolesAllowed("ADMIN")
    public void dropMovie(@PathVariable("id") String id) {
        movieService.drop(id);
    }

    // This method returns the movie if movie is published
    @GetMapping("/find/{id}")
    public MovieVo userGetMovieById(@PathVariable("id") String id) {
        return movieMapper.toVo(movieService.userGetMovieById(id));
    }

    // TODO: Transfer to get featured (recommended movie) in future
    @GetMapping("/find-random")
    public MovieVo userGetRandomMovie(@RequestParam(name="type", required = false) String type) {
        return movieMapper.toVo(movieService.getRandomMovie(type));
    }

    // TODO change to page after adding search filter
    @GetMapping("/find-all")
    public List<MovieVo> userFindAll() {
        return movieService
                .userFindAll()
                .stream()
                .map(movieDto -> movieMapper.toVo(movieDto))
                .collect(Collectors.toList());
    }

}
