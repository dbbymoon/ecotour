package com.kakao.ecotour.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
        //extends ResponseEntityExceptionHandler {
/*
    @ExceptionHandler(ProgramNotFoundException.class)
    public ResponseEntity<String> programNotFoundException(ProgramNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SearchResultNotExistException.class)
    public ResponseEntity<String> searchResultNotExistException(SearchResultNotExistException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
*/
}