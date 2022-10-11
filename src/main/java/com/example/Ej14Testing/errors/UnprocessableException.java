package com.example.Ej14Testing.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends RuntimeException{
    public UnprocessableException  (String errorMessage) {
        super(errorMessage);
}}
