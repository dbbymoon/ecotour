package com.kakao.ecotour.elastic;

import com.kakao.ecotour.exception.SearchResultNotExistException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

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
