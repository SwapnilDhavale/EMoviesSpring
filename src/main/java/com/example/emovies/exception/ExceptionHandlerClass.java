package com.example.emovies.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(value = {MovieRequestException.class})
    public ResponseEntity<Object> handleException(MovieRequestException ex) {
        MovieException movieException = new MovieException(ex.getMessage());
        return new ResponseEntity<>(movieException, ex.getCode());
    }





}
