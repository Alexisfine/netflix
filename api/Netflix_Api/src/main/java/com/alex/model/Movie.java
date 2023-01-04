package com.alex.model;

import com.alex.enums.MovieStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

//@Entity
//@Table(name = "movie")
@Data
public class Movie extends AbstractEntity{

    private String title;

    private String description;

    private MovieStatus movieStatus;

    private String image;

    private String imageTitle;

    private String trailer;

    private String video;

    private String year;

    private String limit;

    private String genre;

    private boolean isSeries;
}
