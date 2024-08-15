package com.example.emovies.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;
import com.example.emovies.exception.MovieRequestException;
import com.example.emovies.model.EmovieModel;
import com.example.emovies.repository.EmovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmovieServiceImpl implements IEmovieService{

    @Autowired
    EmovieRepo movierepo;

    @Override
    public GetEmoviesResponseDto addMovie(EmovieDto movie) throws MovieRequestException{
        //validation
        validateAddMovieRequest(movie);

        //create model and save
        EmovieModel model = createMovieModel(movie);

        //save in db
        EmovieModel savedModel = movierepo.save(model);

        GetEmoviesResponseDto responseDto = generateEmovieResponseDto(savedModel);

        //return response
        return responseDto;
    }

    private void validateAddMovieRequest(EmovieDto movie) {
        if (StringUtil.isNullOrEmpty(movie.getMovieName()) || StringUtil.isNullOrEmpty(movie.getDirector())) {
            throw new MovieRequestException(String.format("Movie name or Director Name cannot be empty"), HttpStatus.BAD_REQUEST);
        }
        int rating = movie.getRating();

        if (rating < 0 || rating > 10) {
            throw new MovieRequestException(String.format("Invalid rating : %s. Rating needs to be in the range 0 - 10", rating), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<GetEmoviesResponseDto> getAllMovies() {
        List<EmovieModel> movies = movierepo.findAll();
        return createGetAllMovieList(movies);
    }

    @Override
    public void deleteMovie(int id) throws MovieRequestException {
        //validate
        EmovieModel movie = movierepo.findEmovieModelById(id);
        if (movie == null) {
            throw new MovieRequestException(String.format("No movie exist with id : %s", id), HttpStatus.BAD_REQUEST);
        }

        movierepo.delete(movie);
    }

    @Override
    public GetEmoviesResponseDto getMovieById(int id) throws MovieRequestException {
        EmovieModel movie = movierepo.findEmovieModelById(id);
        if (movie == null) {
            throw new MovieRequestException(String.format("No movie exist with id : %s", id), HttpStatus.BAD_REQUEST);
        }
        GetEmoviesResponseDto responseDto = generateEmovieResponseDto(movie);

        return responseDto;
    }

    private List<GetEmoviesResponseDto> createGetAllMovieList(List<EmovieModel> movies) {
        List<GetEmoviesResponseDto> response = new ArrayList<>();
        for(EmovieModel emovie: movies) {
            GetEmoviesResponseDto movie = generateEmovieResponseDto(emovie);
            response.add(movie);
        }
        return response;
    }

    private GetEmoviesResponseDto generateEmovieResponseDto(EmovieModel emovie) {
        GetEmoviesResponseDto movie = new GetEmoviesResponseDto();
        movie.setMovieName(emovie.getMovieName());
        movie.setRating(emovie.getRating());
        movie.setDirector(emovie.getDirector());
        movie.setId(emovie.getId());
        return movie;

    }

    private EmovieModel createMovieModel(EmovieDto movie) {
        EmovieModel model = new EmovieModel();
        model.setDirector(movie.getDirector());
        model.setMovieName(movie.getMovieName());
        model.setRating(movie.getRating());

        return model;
    }
}
