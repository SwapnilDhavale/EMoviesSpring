package com.example.emovies.exception;

public class MovieException {
    private String message;

    public MovieException(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
