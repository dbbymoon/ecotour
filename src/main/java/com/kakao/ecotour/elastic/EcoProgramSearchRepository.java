package com.kakao.ecotour.elastic;

import java.util.List;

public interface EcoProgramSearchRepository {

    List<EcoProgramDto> findByRegionCode(String regionCode);

    RegionSearchResultDto findByRegionName(String regionName);

    KeywordSearchRegionCountResultDto findRegionCountByProgramInfoKeyword(String keyword);

    KeywordFrequencyResultDto findFrequencyByProgramDetailInfoKeyword(String keyword);

    RecommendProgramDto findProgramByRegionAndKeyword(String region, String keyword);

}
