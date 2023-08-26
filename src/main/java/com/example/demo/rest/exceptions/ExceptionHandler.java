package com.example.demo.rest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler( value = {
            IllegalArgumentException.class, UserAlreadyExistsException.class, RuntimeException.class
    })
    public ResponseEntity<Object> handleConflict(RuntimeException exc, WebRequest request) {
        String body = exc.getMessage();
        return handleExceptionInternal(exc, body, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
