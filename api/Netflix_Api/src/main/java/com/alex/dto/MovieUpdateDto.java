package com.alex.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class MovieUpdateDto {
    @NotBlank(message = "Movie title can't be blank")
    private String title;

    private String description;

    private String year;

    private String limit;

    private String genre;

    private boolean isSeries;


}
