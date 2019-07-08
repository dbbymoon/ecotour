package com.kakao.ecotour.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProgramNotFoundException.class)
    public String programNotFoundException(ProgramNotFoundException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

}