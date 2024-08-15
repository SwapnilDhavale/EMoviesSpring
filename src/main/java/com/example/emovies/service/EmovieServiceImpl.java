package com.example.emovies.service;

import com.example.emovies.dto.EmovieDto;
import com.example.emovies.dto.GetEmoviesResponseDto;
import com.example.emovies.model.EmovieModel;
import com.example.emovies.repository.EmovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmovieServiceImpl implements IEmovieService{

    @Autowired
    EmovieRepo movierepo;

    @Override
    public GetEmoviesResponseDto addMovie(EmovieDto movie) {
        //validation

        //create model and save
        EmovieModel model = createMovieModel(movie);

        //save in db
        EmovieModel savedModel = movierepo.save(model);

        GetEmoviesResponseDto responseDto = generateEmovieResponseDto(savedModel);

        //return response
        return responseDto;
    }

    @Override
    public List<GetEmoviesResponseDto> getAllMovies() {
        List<EmovieModel> movies = movierepo.findAll();
        return createGetAllMovieList(movies);
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        //validate
        EmovieModel movie = movierepo.findEmovieModelById(id);
        if (movie == null) {
            throw new Exception("NO SUC MOVIE");
        }

        movierepo.delete(movie);
    }

    @Override
    public GetEmoviesResponseDto getMovieById(int id) throws Exception {
        EmovieModel movie = movierepo.findEmovieModelById(id);
        if (movie == null) {
            throw new Exception("NO SUC MOVIE");
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
