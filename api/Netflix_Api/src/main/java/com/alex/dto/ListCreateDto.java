package com.alex.dto;

import com.alex.model.Movie;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ListCreateDto {
    @NotBlank(message = "title can't be blank")
    private String title;
    private String type;
    private String genre;
    private java.util.List<String> contentIds;
}
