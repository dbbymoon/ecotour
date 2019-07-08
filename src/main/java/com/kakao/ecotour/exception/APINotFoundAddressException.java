package com.kakao.ecotour.exception;

public class APINotFoundAddressException extends RuntimeException {
    @Override
    public String getMessage() {
        return "API failed to search address";
    }
}
