package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCSV;
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

    public EcoProgramCSV getEcoProgram(long id) throws ProgramNotFoundException {
        return EcoProgramCSV.of(ecoProgramRepository.findById(id).orElseThrow(ProgramNotFoundException::new));
    }

    public List<EcoProgramCSV> getEcoProgramList() {
        return EcoProgramCSV.of(ecoProgramRepository.findAll());
    }

    public List<EcoProgramCSV> getEcoProgramListByRegionCode(String regionCode) {
        return EcoProgramCSV.ofDto(ecoProgramElasticRepository.findByRegionCode(regionCode));
    }

    public RegionSearchResultVO getEcoProgramListByRegion(String regionName) throws SearchResultNotExistException {
        return ecoProgramElasticRepository.findByRegionName(regionName);
    }

    public KeywordSearchRegionCountResultVO getRegionCountByProgramInfoKeyword(String keyword) {
        return ecoProgramElasticRepository.findRegionCountByProgramInfoKeyword(keyword);
    }

    public KeywordFrequencyResultVO getFrequencyByProgramDetailInfoKeyword(String keyword) {
        return ecoProgramElasticRepository.findFrequencyByProgramDetailInfoKeyword(keyword);
    }

    public RecommendProgramVO recommendProgramByRegionAndKeyword(String region, String keyword) throws SearchResultNotExistException {
        return ecoProgramElasticRepository.findProgramByRegionAndKeyword(region, keyword);
    }

}
