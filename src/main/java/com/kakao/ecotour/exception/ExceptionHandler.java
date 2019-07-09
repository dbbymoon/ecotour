package com.kakao.ecotour.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProgramNotFoundException.class)
    public ResponseEntity<String> programNotFoundException(ProgramNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SearchResultNotExistException.class)
    public ResponseEntity<String> searchResultNotExistException(SearchResultNotExistException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

}