package com.example.emovies.controller;

import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;
import com.example.emovies.exception.MovieRequestException;
import com.example.emovies.service.IEmovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/emovies")
public class EmovieController {

    @Autowired
    IEmovieService service;

    @PostMapping(value = "/movie")
    public ResponseEntity<GetEmoviesResponseDto> addMovie(@RequestBody EmovieDto movie) throws MovieRequestException {
        GetEmoviesResponseDto savedMovie = service.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<GetEmoviesResponseDto>> getAllMovies() {
        List<GetEmoviesResponseDto> movies = service.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<GetEmoviesResponseDto> getMovieById(@PathVariable("id") int id) throws MovieRequestException {
        GetEmoviesResponseDto movie = service.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping(value = "/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") int id) throws MovieRequestException {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
