package com.alex.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Developing

@Data
public class FileUploadRequest {
    @NotBlank(message = "File name can't be blank")
    private String name;

    private Integer size;

    @NotBlank(message = "Ext can't be blank")
    private String ext;

    @NotBlank(message = "Key can't be blank")
    private String key;

}
