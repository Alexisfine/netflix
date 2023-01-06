package com.alex.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Table(
        name = "list",
        uniqueConstraints = @UniqueConstraint(name = "list_title_uk", columnNames = "title")
)
@Data
public class List extends AbstractEntity {

    private String title;
    private String type;
    private String genre;

    @ManyToMany(cascade = {}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "list_movie",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private java.util.List<Movie> content = new ArrayList<>();


    public void addMovie(Movie movie) {
        content.add(movie);
        movie.getLists().add(this);
    }

    public void removeMovie(Movie movie) {
        content.remove(movie);
        movie.getLists().remove(this);
    }



}
