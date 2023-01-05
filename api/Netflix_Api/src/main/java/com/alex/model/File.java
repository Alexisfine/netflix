package com.alex.model;

import com.alex.enums.FileStatus;
import com.alex.enums.FileType;
import com.alex.enums.Storage;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "file")
@Data
public class File extends AbstractEntity{
    private String name;
    private String key;
    private String ext;
    private Integer size;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Enumerated(EnumType.STRING)
    private Storage storage;

    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;

}
