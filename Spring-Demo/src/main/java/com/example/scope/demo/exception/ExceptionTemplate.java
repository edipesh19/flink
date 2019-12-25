package com.example.scope.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionTemplate {

    private Date timestamp;
    private String message;
    private String details;
}
