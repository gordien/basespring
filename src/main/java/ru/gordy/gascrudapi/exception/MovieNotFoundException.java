package ru.gordy.gascrudapi.exception;


import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends RuntimeException {



    public HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public int errorCode = 10000;


    public MovieNotFoundException() {
        super("Movie not found message");

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
