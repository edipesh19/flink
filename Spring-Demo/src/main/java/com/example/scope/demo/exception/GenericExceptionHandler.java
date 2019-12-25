package com.example.scope.demo.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate(
            new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionTemplate, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate(
            new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionTemplate, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate(
            new Date(), "validation failed", ex.getBindingResult().getFieldErrors().toString());
        return new ResponseEntity(exceptionTemplate, HttpStatus.BAD_REQUEST);

    }
}
