package com.kakao.ecotour.elastic;

import java.util.List;

public interface EcoProgramSearchRepository {

    List<EcoProgramDocument> findByRegionCode(String regionCode);

    RegionSearchResultVO findByRegionName(String regionName);

    KeywordSearchRegionCountResultVO findRegionCountByProgramInfoKeyword(String keyword);

    KeywordFrequencyResultVO findFrequencyByProgramDetailInfoKeyword(String keyword);

    RecommendProgramVO findProgramByRegionAndKeyword(String region, String keyword);

}
