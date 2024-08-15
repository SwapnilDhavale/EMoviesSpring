package com.example.emovies.service;

import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;

import java.util.List;

public interface IEmovieService {
    public GetEmoviesResponseDto addMovie(EmovieDto movie);

    List<GetEmoviesResponseDto> getAllMovies();

    void deleteMovie(int id) throws Exception;

    GetEmoviesResponseDto getMovieById(int id) throws Exception;
}
