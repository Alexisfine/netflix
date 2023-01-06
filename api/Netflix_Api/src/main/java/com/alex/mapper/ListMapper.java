package com.alex.mapper;

import com.alex.dto.ListCreateDto;
import com.alex.dto.ListDto;
import com.alex.dto.ListUpdateDto;
import com.alex.model.List;
import com.alex.vo.ListVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ListMapper {
    ListDto toDto(List list);

    ListVo toVo(ListDto listVo);

    List createEntity(ListCreateDto listCreateDto);

    List updateEntity(ListUpdateDto listUpdateDto);

}
