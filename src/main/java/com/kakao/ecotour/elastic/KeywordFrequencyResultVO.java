package com.kakao.ecotour.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

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
