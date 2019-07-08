package com.kakao.ecotour.exception;

public class SearchResultNotExistException extends RuntimeException {

    @Override
    public String getMessage() {
        return "No Result";
    }

}
