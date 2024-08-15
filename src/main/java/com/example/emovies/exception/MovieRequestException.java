package com.example.emovies.exception;

import org.springframework.http.HttpStatus;

public class MovieRequestException extends RuntimeException {

    HttpStatus code;

    public HttpStatus getCode() {
        return code;
    }
    public MovieRequestException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public MovieRequestException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
