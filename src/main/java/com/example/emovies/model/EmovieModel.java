package com.example.emovies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "emovies")
@Entity
@Getter
@Setter
public class EmovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emovie_generator")
    @SequenceGenerator(name = "emovie_generator", sequenceName = "emovie_generator", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "moviename")
    private String movieName;

    @Column(name = "director")
    private String director;

    @Column(name = "rating")
    private int rating;
}
