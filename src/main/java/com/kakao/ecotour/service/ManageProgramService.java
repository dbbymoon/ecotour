package com.kakao.ecotour.service;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.entity.EcoProgram;
import com.kakao.ecotour.entity.Region;
import com.kakao.ecotour.enumeration.RegionEnum;
import com.kakao.ecotour.repository.EcoProgramRepository;
import com.kakao.ecotour.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ManageProgramService {

    private final EcoProgramRepository ecoProgramRepository;

    private final RegionRepository regionRepository;

    public ManageProgramService(EcoProgramRepository ecoProgramRepository, RegionRepository regionRepository) {
        this.ecoProgramRepository = ecoProgramRepository;
        this.regionRepository = regionRepository;
    }

    public EcoProgramDto saveEcoProgram(EcoProgramDto ecoProgramDto) {
        Region region = saveRegion(ecoProgramDto.getRegion());
        EcoProgram ecoProgram = ecoProgramRepository.save(EcoProgram.of(ecoProgramDto, region));
        return EcoProgramDto.of(ecoProgram);
    }

    protected Region saveRegion(String address) {

        String[] addressArr = address.split(" ");
        String regionName;

        if (addressArr.length > 1) {
            String regionCity = addressArr[1];
            int lastIdx = regionCity.length()-1;
            char lastChar = regionCity.charAt(lastIdx);
            while(lastChar == ',' || lastChar == '시' || lastChar == '군') {
                regionCity = regionCity.substring(0, lastIdx);
                lastIdx--;
                lastChar = regionCity.charAt(lastIdx);
            }

            regionName = addressArr[0] + " " + regionCity;
        } else {
            regionName = addressArr[0];
        }

        RegionEnum regionEnum = RegionEnum.findByRegionName(addressArr[0]);

        Region region = regionRepository.findByRegionName(regionName)
                .orElseGet(() -> regionRepository.save(new Region(regionEnum.getCode(regionName), regionName)));

        return region;
    }

}
