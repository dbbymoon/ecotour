package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCsv;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.jpa.Region;
import com.kakao.ecotour.enumeration.RegionEnum;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.RegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

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
/*
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

        EcoProgramCsv ecoProgramCsv = new EcoProgramCsv();
        ecoProgramCsv.setRegion(address);

        EcoProgram ecoProgramResult = new EcoProgram();
        ecoProgramResult.setRegionCity(new Region(regionCode, "경기도 성남"));

        when(ecoProgramRepository.save(any())).thenReturn(ecoProgramResult);

        ecoProgramCsv = manageProgramService.saveEcoProgram(ecoProgramCsv);

        assertEquals(ecoProgramResult.getRegion(), ecoProgramCsv.getRegion());

    }*/


    @Test
    public void getRegionCode1() throws JSONException {
        System.out.println(manageProgramService.getRegionCode("경기도 광주시"));
    }
}