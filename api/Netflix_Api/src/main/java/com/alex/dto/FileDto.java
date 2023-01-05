package com.alex.dto;

import com.alex.enums.FileStatus;
import com.alex.enums.FileType;
import com.alex.enums.Storage;
import lombok.Data;

import java.util.Date;
// Developing
@Data
public class FileDto {
    private String id;
    private String name;
    private String key;
    private String ext;
    private FileType fileType;
    private FileStatus fileStatus;
    private Storage storage;
    private Date createdAt;
    private Date updatedAt;
    private Integer size;
}
