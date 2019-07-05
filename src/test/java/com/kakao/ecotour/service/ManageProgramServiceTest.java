package com.kakao.ecotour.service;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.entity.EcoProgram;
import com.kakao.ecotour.entity.Region;
import com.kakao.ecotour.enumeration.RegionEnum;
import com.kakao.ecotour.repository.EcoProgramRepository;
import com.kakao.ecotour.repository.RegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ManageProgramServiceTest {

    @InjectMocks
    private ManageProgramService manageProgramService;

    @Mock
    private EcoProgramRepository ecoProgramRepository;

    @Mock
    private RegionRepository regionRepository;

    private String getRegionCode(String address) {
        return RegionEnum.경기도.getCode(address)+address.hashCode();
    }

    @Test
    public void saveRegionTest() {
        String address = "경기도";
        String regionCode = getRegionCode(address);

        when(regionRepository.findByRegionName("경기도")).thenReturn(
                Optional.of(new Region(regionCode, "경기도")));

        Region region = manageProgramService.saveRegion(address);

        assertEquals(regionCode, region.getRegionCode());
        assertEquals("경기도", region.getRegionName());
    }

    @Test
    public void saveRegionTest1() {
        String address = "경기도 성남시";
        String regionCode = getRegionCode(address);

        when(regionRepository.findByRegionName("경기도 성남")).thenReturn(
                Optional.of(new Region(regionCode, "경기도 성남")));

        Region region = manageProgramService.saveRegion(address);

        assertEquals(regionCode, region.getRegionCode());
        assertEquals("경기도 성남", region.getRegionName());
    }

    @Test
    public void saveRegionTest2() {
        String address = "경기도 성남시 분당구";
        String regionCode = getRegionCode(address);

        when(regionRepository.findByRegionName("경기도 성남")).thenReturn(
                Optional.of(new Region(regionCode, "경기도 성남")));

        Region region = manageProgramService.saveRegion(address);

        assertEquals(regionCode, region.getRegionCode());
        assertEquals("경기도 성남", region.getRegionName());
    }

    @Test
    public void saveEcoProgram() {
        //manageProgramService.saveEcoProgram()
        String address = "경기도 성남시 분당구";
        String regionCode = getRegionCode(address);
        when(regionRepository.findByRegionName("경기도 성남")).thenReturn(
                Optional.of(new Region(regionCode, "경기도 성남")));

        EcoProgramDto ecoProgramDto = new EcoProgramDto();
        ecoProgramDto.setRegion(address);

        EcoProgram ecoProgramResult = new EcoProgram();
        ecoProgramResult.setRegionCity(new Region(regionCode, "경기도 성남"));

        when(ecoProgramRepository.save(any())).thenReturn(ecoProgramResult);

        ecoProgramDto = manageProgramService.saveEcoProgram(ecoProgramDto);

        assertEquals(ecoProgramResult.getRegion(),ecoProgramDto.getRegion());

    }


}