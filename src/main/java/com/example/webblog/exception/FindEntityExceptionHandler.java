package com.example.webblog.exception;

import com.example.webblog.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FindEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionModel> handleNotFoundException(NotFoundException e){
        ExceptionModel exceptionModel = new ExceptionModel(
                e.getMessage(),
                e.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }
}
