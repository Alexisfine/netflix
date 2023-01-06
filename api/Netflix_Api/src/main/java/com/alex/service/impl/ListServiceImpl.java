package com.alex.service.impl;

import com.alex.dto.ListCreateDto;
import com.alex.dto.ListDto;
import com.alex.dto.ListUpdateDto;
import com.alex.enums.MovieStatus;
import com.alex.exception.BizException;
import com.alex.exception.ExceptionType;
import com.alex.mapper.ListMapper;
import com.alex.model.Movie;
import com.alex.repository.ListDao;
import com.alex.repository.MovieDao;
import com.alex.service.ListService;
import com.alex.utils.UpdateColumnUtils;
import com.alex.vo.ListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.alex.enums.MovieStatus.PUBLISHED;
import static com.alex.exception.ExceptionType.*;

@Service
@Slf4j
public class ListServiceImpl implements ListService {
    private ListDao listDao;
    private MovieDao movieDao;
    private ListMapper listMapper;

    @Autowired
    public ListServiceImpl(ListDao listDao, MovieDao movieDao, ListMapper listMapper) {
        this.listDao = listDao;
        this.movieDao = movieDao;
        this.listMapper = listMapper;
    }

    @Override
    @Transactional
    public ListDto createList(ListCreateDto listCreateDto) {
        // check if list title is unique
        boolean titleDuplicate = listDao.existsByTitle(listCreateDto.getTitle());
        if (titleDuplicate) throw new BizException(LIST_TITLE_DUPLiCATE);

        // check every movie in the list is valid
        List<Movie> movieLists = listCreateDto
                .getContentIds()
                .stream()
                .map(id -> movieDao.findById(id).orElseThrow(() -> new BizException(MOVIE_NOT_FOUND)))
                .filter(movie -> PUBLISHED.equals(movie.getMovieStatus()))
                .collect(Collectors.toList());


        com.alex.model.List newEntity = listMapper.createEntity(listCreateDto);
        movieLists.forEach(movie -> newEntity.addMovie(movie));

        return listMapper.toDto(listDao.save(newEntity));
    }

    @Override
    public void deleteList(String id) {
        // find the list
        com.alex.model.List list = listDao.findById(id).orElseThrow(() -> new BizException(LIST_NOT_FOUND));
        log.info("list is found");
        listDao.delete(list);
        log.info("successfully deleted list");
    }

    @Override
    public ListDto getList(String id) {
        return listMapper.toDto(listDao.findById(id).orElseThrow(() -> new BizException(LIST_NOT_FOUND)));
    }

    @Override
    public ListDto updateList(String id, ListUpdateDto listUpdateDto) {
        List<Movie> movieLists = listUpdateDto
                .getContentIds()
                .stream()
                .map(movieId -> movieDao.findById(movieId).orElseThrow(() -> new BizException(MOVIE_NOT_FOUND)))
                .filter(movie -> PUBLISHED.equals(movie.getMovieStatus()))
                .collect(Collectors.toList());


        com.alex.model.List oldList = listDao.findById(id).orElseThrow(() -> new BizException(LIST_NOT_FOUND));

        BeanUtils.copyProperties(listUpdateDto, oldList, UpdateColumnUtils.getNullPropertyNames(listUpdateDto));
        movieLists.forEach(movie -> oldList.addMovie(movie));

        return listMapper.toDto(listDao.save(oldList));
    }

    @Override
    public Page<ListDto> search(Pageable pageable, String type, String genre) {
        return listDao
                .findByTypeAndGenreWithPagination(type, genre, pageable)
                .map(list -> listMapper.toDto(list));
    }
}
