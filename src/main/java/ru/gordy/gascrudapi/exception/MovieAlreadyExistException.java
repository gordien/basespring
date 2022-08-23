package ru.gordy.gascrudapi.exception;

import org.springframework.http.HttpStatus;

public class MovieAlreadyExistException extends RuntimeException {

    public int errorCode = 20000;
    public HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }



    public MovieAlreadyExistException() {
        super("Movie already exist");
    }
}
