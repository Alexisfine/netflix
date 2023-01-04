package com.alex.dto;

import com.alex.enums.MovieStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class MovieCreateDto {
    @NotBlank(message = "Movie title can't be blank")
    private String title;

    private String description;

    private String image;

    private String imageSmall;

    private String imageTitle;

    private String trailer;

    private String video;

    private String year;

    private String limit;

    private String genre;

    private boolean isSeries;

}
