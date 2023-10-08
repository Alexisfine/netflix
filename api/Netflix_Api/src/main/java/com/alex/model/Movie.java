package com.alex.model;

import com.alex.enums.MovieStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movie")
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Movie extends AbstractEntity{

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieStatus movieStatus = MovieStatus.DRAFT;

    private String image;

    private String imageSmall;

    private String imageTitle;

    private String trailer;

    private String video;

    private String year;

    private String lim;

    private String genre;

    private boolean isSeries = false;

    @ManyToMany(
            mappedBy = "content",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<com.alex.model.List> lists = new ArrayList<>();


}
