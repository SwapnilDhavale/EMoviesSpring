package com.example.emovies.controller;

import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;
import com.example.emovies.exception.MovieException;
import com.example.emovies.exception.MovieRequestException;
import com.example.emovies.service.IEmovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Emovies",
        description = "CRUD REST APIs in Emovies CREATE, UPDATE, FETCH AND DELETE movies"
)
@RestController
@RequestMapping(value = "/api/emovies")
public class EmovieController {

    @Autowired
    IEmovieService service;

    @Operation(
            summary = "Add movie",
            description = "REST API to add movie"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = @Content(
                            schema = @Schema(implementation = GetEmoviesResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = MovieException.class)
                    )
            )
    }
    )
    @PostMapping(value = "/movie")
    public ResponseEntity<GetEmoviesResponseDto> addMovie(@RequestBody EmovieDto movie) throws MovieRequestException {
        GetEmoviesResponseDto savedMovie = service.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @Operation(
            summary = "Get All movies",
            description = "REST API to get all movies"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetEmoviesResponseDto.class)
                            )
                    )
            )
    }
    )
    @GetMapping(value = "/movies")
    public ResponseEntity<List<GetEmoviesResponseDto>> getAllMovies() {
        List<GetEmoviesResponseDto> movies = service.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @Operation(
            summary = "Get movie by id",
            description = "REST API to get movie by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = GetEmoviesResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = MovieException.class)
                    )
            )
    }
    )
    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<GetEmoviesResponseDto> getMovieById(@PathVariable("id") int id) throws MovieRequestException {
        GetEmoviesResponseDto movie = service.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @Operation(
            summary = "Delete movie by id",
            description = "REST API to delete movie by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status NO CONTENT"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = MovieException.class)
                    )
            )
    }
    )
    @DeleteMapping(value = "/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") int id) throws MovieRequestException {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
