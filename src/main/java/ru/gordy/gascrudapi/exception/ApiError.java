package ru.gordy.gascrudapi.exception;

import org.springframework.http.HttpStatus;

public class ApiError {


    private String message;


    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ApiError(String message, int errorCode) {

        this.message = message;
        this.errorCode = errorCode;
    }

    public ApiError() {
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;
}
