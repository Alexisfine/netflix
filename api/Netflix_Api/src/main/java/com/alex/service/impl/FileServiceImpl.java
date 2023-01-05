package com.alex.service.impl;

import com.alex.dto.FileUploadDto;
import com.alex.dto.FileUploadRequest;
import com.alex.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public FileUploadDto initUpload(FileUploadRequest fileUploadRequest) {
        return null;
    }
}
