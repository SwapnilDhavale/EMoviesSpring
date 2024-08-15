package com.example.emovies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmoviesResponseDto {
    private int id;
    private String movieName;
    private String director;
    private int rating;
}
