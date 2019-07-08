package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCsv;
import com.kakao.ecotour.elastic.EcoProgramDto;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.Region;
import com.kakao.ecotour.jpa.RegionRepository;
import com.kakao.ecotour.kakaoapi.SearchAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ManageProgramService {

    private final SearchAddressService searchAddressService;

    private final EcoProgramRepository ecoProgramRepository;

    private final RegionRepository regionRepository;

    private final EcoProgramElasticRepository ecoProgramElasticRepository;

    public ManageProgramService(SearchAddressService searchAddressService, EcoProgramRepository ecoProgramRepository,
                                RegionRepository regionRepository, EcoProgramElasticRepository ecoProgramElasticRepository) {
        this.searchAddressService = searchAddressService;
        this.ecoProgramRepository = ecoProgramRepository;
        this.regionRepository = regionRepository;
        this.ecoProgramElasticRepository = ecoProgramElasticRepository;
    }

    public EcoProgramDto saveEcoProgram(EcoProgramCsv ecoProgramCsv) {
        // DB save
        Region region = saveRegion(ecoProgramCsv.getRegion());
        EcoProgram ecoProgram = ecoProgramRepository.save(EcoProgram.of(ecoProgramCsv, region));
        // ES save
        EcoProgramDto ecoProgramDto = ecoProgramElasticRepository.save(EcoProgramDto.of(ecoProgram));
        return ecoProgramDto;
    }


    public Region saveRegion(String address) {

        Region region = searchAddressService.getRegion(address);

        return regionRepository.findByRegionName(region.getRegionName())
                .orElseGet(() -> regionRepository.save(region));

    }


}
