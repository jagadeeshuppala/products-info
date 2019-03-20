package com.zensar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.zensar.model.Error;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Error> handleException(Exception excption){
        Error error = new Error(500, excption.getMessage() , "visit Zensar help page for troubleshooting");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}