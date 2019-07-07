package com.kakao.ecotour.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface EcoProgramElasticRepository extends ElasticsearchCrudRepository<EcoProgramDto, Long>, EcoProgramSearchRepository {
}
