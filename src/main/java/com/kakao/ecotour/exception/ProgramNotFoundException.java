package com.kakao.ecotour.exception;

public class ProgramNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Program Not Found Exception";
    }

}
