package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCsv;
import com.kakao.ecotour.elastic.EcoProgramDto;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.Region;
import com.kakao.ecotour.jpa.RegionRepository;
import com.kakao.ecotour.kakaoapi.APISearchAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ManageProgramService {

    private final APISearchAddressService APISearchAddressService;

    private final EcoProgramRepository ecoProgramRepository;

    private final RegionRepository regionRepository;

    private final EcoProgramElasticRepository ecoProgramElasticRepository;

    public ManageProgramService(APISearchAddressService APISearchAddressService, EcoProgramRepository ecoProgramRepository,
                                RegionRepository regionRepository, EcoProgramElasticRepository ecoProgramElasticRepository) {
        this.APISearchAddressService = APISearchAddressService;
        this.ecoProgramRepository = ecoProgramRepository;
        this.regionRepository = regionRepository;
        this.ecoProgramElasticRepository = ecoProgramElasticRepository;
    }

    public void saveEcoProgram(EcoProgramCsv ecoProgramCsv) {
        // DB save
        Region region = saveRegion(ecoProgramCsv.getRegion());
        EcoProgram ecoProgram = ecoProgramRepository.save(EcoProgram.of(ecoProgramCsv, region));
        // ES save
        ecoProgramElasticRepository.save(EcoProgramDto.of(ecoProgram));
    }


    public Region saveRegion(String address) {

        Region region = APISearchAddressService.getRegion(address);

        return regionRepository.findByRegionName(region.getRegionName())
                .orElseGet(() -> regionRepository.save(region));

    }


}
