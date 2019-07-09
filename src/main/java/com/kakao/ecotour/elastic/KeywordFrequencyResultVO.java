package com.kakao.ecotour.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

/*
    elasticsearch 검색 결과 VO
        :   키워드 출현 빈도수 조회 by 프로그램 상세 소개 컬럼 키워드
 */

@Getter
@Setter
@AllArgsConstructor
public class KeywordFrequencyResultVO {

    private String keyword;

    private long count;

    static KeywordFrequencyResultVO of(String keyword, SearchResponse response) {
        return new KeywordFrequencyResultVO(keyword, response.getHits().getTotalHits());
    }
}
