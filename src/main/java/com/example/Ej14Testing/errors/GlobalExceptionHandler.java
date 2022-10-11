package com.example.Ej14Testing.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomError> handleNotFoundException(NotFoundException exception, WebRequest request){
        CustomError customError = new CustomError(new Date(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<CustomError> handleNotFoundException(UnprocessableException exception, WebRequest request){
        CustomError customError = new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<CustomError>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

