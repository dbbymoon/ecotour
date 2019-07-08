package com.kakao.ecotour.elastic;

import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;

@AllArgsConstructor
public class RecommendProgramDto {

    private String program;     // 프로그램 코드

    public static RecommendProgramDto of(SearchResponse response) {
        String id = response.getHits().getHits()[0].getId();
        return new RecommendProgramDto("prgm" + id);
    }
}
