package com.example.webblog.exception;

import com.example.webblog.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionModel> handleMaxSizeException(MaxUploadSizeExceededException e){
        ExceptionModel exceptionModel = new ExceptionModel(
                "One or more files are too large!",
                null,
                HttpStatus.EXPECTATION_FAILED
        );

        return new ResponseEntity<>(exceptionModel, HttpStatus.EXPECTATION_FAILED);
    }
}
