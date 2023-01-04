package com.alex.mapper;

import com.alex.dto.MovieCreateDto;
import com.alex.dto.MovieDto;
import com.alex.dto.MovieUpdateDto;
import com.alex.model.Movie;
import com.alex.vo.MovieVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MovieMapper {
    MovieDto toDto(Movie movie);

    MovieVo toVo(MovieDto movieDto);

    Movie createEntity(MovieCreateDto movieCreateDto);

    Movie updateEntity(MovieUpdateDto movieUpdateDto);

}
