package com.alex.vo;

import com.alex.enums.MovieStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MovieVo {
    private String id;

    private String title;

    private String description;

    private MovieStatus movieStatus;

    private String image;

    private String imageSmall;

    private String imageTitle;

    private String trailer;

    private String video;

    private String year;

    private String lim;

    private String genre;

    private boolean isSeries;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private Date updatedAt;

}
