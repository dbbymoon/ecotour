package com.kakao.ecotour.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

@Getter
@Setter
@AllArgsConstructor
public class KeywordFrequencyResultDto {

    private String keyword;

    private long count;

    static KeywordFrequencyResultDto of(String keyword, SearchResponse response) {
        return new KeywordFrequencyResultDto(keyword, response.getHits().getTotalHits());
    }
}
