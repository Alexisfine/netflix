package com.alex.dto;

import com.alex.enums.MovieStatus;
import lombok.Data;
import java.util.Date;

@Data
public class MovieDto {
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

    private Date createdAt;

    private Date updatedAt;


}
