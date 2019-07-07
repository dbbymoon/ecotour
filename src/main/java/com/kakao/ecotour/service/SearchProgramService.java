package com.kakao.ecotour.service;

import com.kakao.ecotour.elastic.*;
import com.kakao.ecotour.exception.ProgramNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class SearchProgramService {

    private final EcoProgramElasticRepository ecoProgramElasticRepository;

    public SearchProgramService(EcoProgramElasticRepository ecoProgramElasticRepository) {
        this.ecoProgramElasticRepository = ecoProgramElasticRepository;
    }

    public EcoProgramDto getEcoProgram(long id) {
        return ecoProgramElasticRepository.findById(id).orElseThrow(ProgramNotFoundException::new);
    }

    public List<EcoProgramDto> getEcoProgramList() {
        List<EcoProgramDto> ecoProgramDtoList = new ArrayList<>();
        ecoProgramElasticRepository.findAll().forEach(ecoProgramDtoList::add);
        return ecoProgramDtoList;
    }

    public List<EcoProgramDto> getEcoProgramListByRegionCode(String regionCode) {
        return ecoProgramElasticRepository.findByRegionCode(regionCode);
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

    public RecommendProgramDto recommendProgramByRegionAndKeyword(String region, String keyword) {
        return null;
    }
}
