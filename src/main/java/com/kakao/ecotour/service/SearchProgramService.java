package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCsv;
import com.kakao.ecotour.elastic.*;
import com.kakao.ecotour.exception.ProgramNotFoundException;
import com.kakao.ecotour.exception.SearchResultNotExistException;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class SearchProgramService {

    private final EcoProgramElasticRepository ecoProgramElasticRepository;

    private final EcoProgramRepository ecoProgramRepository;

    public SearchProgramService(EcoProgramElasticRepository ecoProgramElasticRepository, EcoProgramRepository ecoProgramRepository) {
        this.ecoProgramElasticRepository = ecoProgramElasticRepository;
        this.ecoProgramRepository = ecoProgramRepository;
    }

    public EcoProgramCsv getEcoProgram(long id) throws ProgramNotFoundException {
        return EcoProgramCsv.of(ecoProgramRepository.findById(id).orElseThrow(ProgramNotFoundException::new));
    }

    public List<EcoProgramCsv> getEcoProgramList() {
        return EcoProgramCsv.of(ecoProgramRepository.findAll());
    }

    public List<EcoProgramCsv> getEcoProgramListByRegionCode(String regionCode) {
        return EcoProgramCsv.ofDto(ecoProgramElasticRepository.findByRegionCode(regionCode));
    }

    public RegionSearchResultDto getEcoProgramListByRegion(String regionName) {
        return ecoProgramElasticRepository.findByRegionName(regionName);
    }

    public KeywordSearchRegionCountResultDto getRegionCountByProgramInfoKeyword(String keyword) {
        return ecoProgramElasticRepository.findRegionCountByProgramInfoKeyword(keyword);
    }

    public KeywordFrequencyResultDto getFrequencyByProgramDetailInfoKeyword(String keyword) {
        return ecoProgramElasticRepository.findFrequencyByProgramDetailInfoKeyword(keyword);
    }

    public RecommendProgramDto recommendProgramByRegionAndKeyword(String region, String keyword) throws SearchResultNotExistException {
        return ecoProgramElasticRepository.findProgramByRegionAndKeyword(region, keyword);
    }
}
