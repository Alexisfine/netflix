package com.alex.vo;

import com.alex.model.Movie;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListVo {
    private String id;
    private String title;
    private String type;
    private String genre;
    private java.util.List<Movie> content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
