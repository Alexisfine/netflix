package com.alex.controller;

import com.alex.dto.FileUploadRequest;
import com.alex.mapper.FileUploadMapper;
import com.alex.service.FileService;
import com.alex.vo.FileUploadVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Developing

@RestController
@RequestMapping("/files")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class FileController {
    private FileService fileService;
    private FileUploadMapper fileUploadMapper;

    public FileController(FileService fileService, FileUploadMapper fileUploadMapper) {
        this.fileService = fileService;
        this.fileUploadMapper = fileUploadMapper;
    }

    @PostMapping("/upload/init")
    public FileUploadVo initUpload(@Validated @RequestBody FileUploadRequest fileUploadRequest) {
        return fileUploadMapper.toVo(fileService.initUpload(fileUploadRequest));
    }
}
