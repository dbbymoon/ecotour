package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCSV;
import com.kakao.ecotour.elastic.EcoProgramDocument;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.jpa.EcoProgramEntity;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.RegionEntity;
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

    public void saveEcoProgram(EcoProgramCSV ecoProgramCSV) {
        // DB save
        RegionEntity regionEntity = saveRegion(ecoProgramCSV.getRegion());
        EcoProgramEntity ecoProgramEntity = ecoProgramRepository.save(EcoProgramEntity.of(ecoProgramCSV, regionEntity));
        // ES save
        ecoProgramElasticRepository.save(EcoProgramDocument.of(ecoProgramEntity));
    }


    public RegionEntity saveRegion(String address) {

        RegionEntity regionEntity = APISearchAddressService.getRegion(address);

        return regionRepository.findByRegionName(regionEntity.getRegionName())
                .orElseGet(() -> regionRepository.save(regionEntity));

    }


}