package com.kakao.ecotour.elastic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class RegionSearchResultDto {

    private String region;  // Region Code

    private List<Program> programs;

    public static RegionSearchResultDto of(SearchResponse response) {
        RegionSearchResultDto resultDto = new RegionSearchResultDto();
        List<Program> programs = new ArrayList<>();
        Arrays.stream(response.getHits().getHits())
                .forEach(hit -> programs.add(Program.of(hit.getSourceAsMap())));
        if(!programs.isEmpty()) {
            resultDto.setRegion((String) response.getHits().getHits()[0].getSourceAsMap().get("regionCode"));
            resultDto.setPrograms(programs);
        }
        return resultDto;
    }

    @Getter
    @Setter
    private static class Program {

        private String prgmName;   // 프로그램명

        private String theme;   // 테마

        static Program of(Map<String, Object> sourceMap) {
            Program program = new Program();
            program.setPrgmName((String) sourceMap.get("prgmName"));
            program.setTheme((String)sourceMap.get("theme"));
            return program;
        }
    }
}
