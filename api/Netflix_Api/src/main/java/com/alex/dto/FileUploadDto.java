package com.alex.dto;

import lombok.Data;

// Developing

@Data
public class FileUploadDto {
    private String secretId;
    private String secretKey;
    private String sessionToken;
    private String key;
    private String bucket;
    private String region;
}
