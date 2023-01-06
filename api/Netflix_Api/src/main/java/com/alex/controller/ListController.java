package com.alex.controller;

import com.alex.dto.ListCreateDto;
import com.alex.dto.ListDto;
import com.alex.dto.ListUpdateDto;
import com.alex.mapper.ListMapper;
import com.alex.service.ListService;
import com.alex.vo.ListVo;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ListController {
    private ListService listService;
    private ListMapper listMapper;

    @Autowired
    public ListController(ListService listService, ListMapper listMapper) {
        this.listService = listService;
        this.listMapper = listMapper;
    }

    @GetMapping
    public Page<ListVo> search(@PageableDefault(
            sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable,
                               @RequestParam(value = "type",required = false) String type,
                               @RequestParam(value = "genre", required = false) String genre) {
        return listService.search(pageable, type, genre).map(listMapper::toVo);
    }


    @PostMapping
    @RolesAllowed("ADMIN")
    public ListVo createList(@RequestBody ListCreateDto listCreateDto) {
        return listMapper.toVo(listService.createList(listCreateDto));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteList(@PathVariable("id") String id) {
        listService.deleteList(id);
    }

    @GetMapping("/find/{id}")
    public ListVo getList(@PathVariable("id") String id) {
        return listMapper.toVo(listService.getList(id));
    }


    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ListVo updateList(@PathVariable("id") String id, @RequestBody ListUpdateDto listUpdateDto) {
        return listMapper.toVo(listService.updateList(id, listUpdateDto));
    }




}
