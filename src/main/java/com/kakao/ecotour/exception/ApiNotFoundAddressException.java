package com.kakao.ecotour.exception;

public class ApiNotFoundAddressException extends RuntimeException {
    @Override
    public String getMessage() {
        return "API failed to search address";
    }
}
