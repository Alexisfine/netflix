package com.alex.service;

import com.alex.dto.ListCreateDto;
import com.alex.dto.ListDto;
import com.alex.dto.ListUpdateDto;
import com.alex.vo.ListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ListService {
    ListDto createList(ListCreateDto listCreateDto);

    void deleteList(String id);

    ListDto getList(String id);

    ListDto updateList(String id, ListUpdateDto listUpdateDto);

    Page<ListDto> search(Pageable pageable, String type, String genre);
}
