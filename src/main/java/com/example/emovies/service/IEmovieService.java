package com.example.emovies.service;

import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;
import com.example.emovies.exception.MovieRequestException;

import java.util.List;

public interface IEmovieService {
    public GetEmoviesResponseDto addMovie(EmovieDto movie) throws MovieRequestException;

    List<GetEmoviesResponseDto> getAllMovies();

    void deleteMovie(int id) throws MovieRequestException;

    GetEmoviesResponseDto getMovieById(int id) throws MovieRequestException;
}
