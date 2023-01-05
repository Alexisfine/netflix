package com.alex.service;

import com.alex.dto.FileUploadDto;
import com.alex.dto.FileUploadRequest;

public interface FileService {
    FileUploadDto initUpload(FileUploadRequest fileUploadRequest);
}
