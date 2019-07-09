package com.kakao.ecotour.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.List;
import java.util.stream.Collectors;

/*
    elasticsearch 검색 결과 VO
        :   서비스 지역 개수 조회 by 프로그램 소개 컬럼 키워드
 */

@Getter
@Setter
@AllArgsConstructor
public class KeywordSearchRegionCountResultVO {

    private String keyword;

    private List<Program> programs;

    static KeywordSearchRegionCountResultVO of(String keyword, SearchResponse response) {
        List<Program> programs = ((Terms) response.getAggregations().get("byRegionName"))
                .getBuckets().stream().map(Program::of).collect(Collectors.toList());
        return new KeywordSearchRegionCountResultVO(keyword, programs);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Program {

        private String region;   // 서비스 지역 이름

        private long count;   // 키워드를 포함하는 프로그램 개수

        static Program of(Terms.Bucket bucket) {
            return new Program((String) bucket.getKey(), bucket.getDocCount());
        }

    }
}
