package com.workintech.s19d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleException(ApiException ex){
        log.error("apiexception occured",ex);
        ExceptionResponse error=new ExceptionResponse(ex.getMessage(),ex.getHttpStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException ex){
        log.error("RuntimeException occurred",ex);
        ExceptionResponse error=new ExceptionResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
