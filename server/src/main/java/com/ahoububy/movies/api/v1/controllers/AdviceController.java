package com.ahoububy.movies.api.v1.controllers;

import com.ahoububy.movies.exceptions.ApiError;
import com.ahoububy.movies.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class AdviceController  extends DefaultHandlerExceptionResolver {
    @ExceptionHandler({NotFoundException.class, IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<ApiError> handleNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ApiError.builder().code(404).msg(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}
