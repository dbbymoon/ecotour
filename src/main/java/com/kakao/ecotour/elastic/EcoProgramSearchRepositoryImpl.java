package com.kakao.ecotour.elastic;

import com.alibaba.fastjson.JSON;
import com.kakao.ecotour.exception.SearchResultNotExistException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EcoProgramSearchRepositoryImpl implements EcoProgramSearchRepository {

    private final Client client;

    public EcoProgramSearchRepositoryImpl(Client client) {
        this.client = client;
    }

    @Override
    public List<EcoProgramDocument> findByRegionCode(String regionCode) {

        SearchResponse response = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("regionCode", regionCode))
                .execute()
                .actionGet();

        return Arrays.stream(response.getHits().getHits())
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), EcoProgramDocument.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegionSearchResultVO findByRegionName(String regionName) throws SearchResultNotExistException {

        SearchResponse response = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("region", regionName))
                .execute()
                .actionGet();

        return RegionSearchResultVO.of(response);
    }

    @Override
    public KeywordSearchRegionCountResultVO findRegionCountByProgramInfoKeyword(String keyword) {
        SearchResponse response = client.prepareSearch()
                .setQuery(QueryBuilders.termQuery("prgmInfo", keyword))
                .addAggregation(AggregationBuilders.terms("byRegionName").field("regionName"))
                .setSize(0)
                .execute()
                .actionGet();

        return KeywordSearchRegionCountResultVO.of(keyword, response);
    }

    @Override
    public KeywordFrequencyResultVO findFrequencyByProgramDetailInfoKeyword(String keyword) {

        SearchResponse response = client.prepareSearch()
                .setQuery(QueryBuilders.termQuery("prgmDetailInfo", keyword))
                .setSize(0)
                .execute()
                .actionGet();

        return KeywordFrequencyResultVO.of(keyword, response);
    }

    @Override
    public RecommendProgramVO findProgramByRegionAndKeyword(String region, String keyword) throws SearchResultNotExistException {

        final float regionScore = 10;
        final float keywordScore = 0.1f;

        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.matchQuery("region", region).boost(regionScore))
                .add(QueryBuilders.multiMatchQuery(keyword, "theme^3", "prgmInfo^2", "prgmDetailInfo").boost(keywordScore));

        SearchResponse response = client.prepareSearch()
                .setQuery(queryBuilder)
                .execute()
                .actionGet();

        return RecommendProgramVO.of(response);
    }
}
