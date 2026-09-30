package com.spring.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> exception(Throwable throwable){
        return ResponseEntity.badRequest().body(throwable.getMessage());
    }


}
