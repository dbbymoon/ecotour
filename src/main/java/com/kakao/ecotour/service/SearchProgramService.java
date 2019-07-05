package com.kakao.ecotour.service;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.dto.RegionDto;
import com.kakao.ecotour.entity.Region;
import com.kakao.ecotour.repository.EcoProgramRepository;
import com.kakao.ecotour.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class SearchProgramService {

    private final EcoProgramRepository ecoProgramRepository;

    private final RegionRepository regionRepository;

    public SearchProgramService(EcoProgramRepository ecoProgramRepository, RegionRepository regionRepository) {
        this.ecoProgramRepository = ecoProgramRepository;
        this.regionRepository = regionRepository;
    }

    public List<EcoProgramDto> getEcoProgramList() {
        return EcoProgramDto.of(ecoProgramRepository.findAll());
    }

    public List<EcoProgramDto> getEcoProgramListByRegionCode(String regionCode) {
        return EcoProgramDto.of(
                regionRepository.findByRegionCode(regionCode).orElseThrow(RuntimeException::new)
                        .getEcoProgramList());
    }

    public List<RegionDto> getEcoProgramListByRegion(String regionName) {

        List<Region> regionList = regionRepository.findByRegionNameContaining(regionName);

        return RegionDto.of(regionList);
    }
}
