package ru.gordy.gascrudapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    protected ResponseEntity<Object> handleMovieNotFound (MovieNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(),ex.getErrorCode());
        logger.warn("Movie not found");
        return new ResponseEntity<>(apiError,ex.getHttpStatus());

    }

    @ExceptionHandler(MovieAlreadyExistException.class)
    protected ResponseEntity<Object> handleMovieAlreadyExistException (MovieAlreadyExistException ex) {
        ApiError apiError = new ApiError(ex.getMessage(),ex.getErrorCode());
        logger.warn("Movie already exist");
        return new ResponseEntity<>(apiError,ex.getHttpStatus());

    }





}
