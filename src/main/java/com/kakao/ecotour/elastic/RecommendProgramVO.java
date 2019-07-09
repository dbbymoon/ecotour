package com.kakao.ecotour.elastic;

import com.kakao.ecotour.exception.SearchResultNotExistException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

/*
    elasticsearch 검색 결과 VO
        :   프로그램 추천 by 서비스 지역, 키워드
 */

@Getter
@Setter
@AllArgsConstructor
public class RecommendProgramVO {

    private String program;     // 프로그램 코드

    public static RecommendProgramVO of(SearchResponse response) throws SearchResultNotExistException {

        try {
            String id = response.getHits().getHits()[0].getId();
            return new RecommendProgramVO("prgm" + id);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SearchResultNotExistException();
        }

    }
}
