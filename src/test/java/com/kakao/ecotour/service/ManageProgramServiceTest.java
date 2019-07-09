package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCSV;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.jpa.EcoProgramEntity;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.RegionEntity;
import com.kakao.ecotour.jpa.RegionRepository;
import com.kakao.ecotour.kakaoapi.APISearchAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ManageProgramServiceTest {

    @InjectMocks
    private ManageProgramService manageProgramService;

    @Mock
    private EcoProgramRepository ecoProgramRepository;

    @Mock
    private EcoProgramElasticRepository elasticRepository;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private APISearchAddressService apiSearchAddressService;

    private final String regionCodeForTest = "reg42210";

    private final String regionNameForTest = "강원도 속초";

    private RegionEntity regionEntityForTest = new RegionEntity(regionCodeForTest, regionNameForTest);


    @Test
    public void saveEcoProgram() {

        EcoProgramCSV ecoProgramCSV = new EcoProgramCSV();
        ecoProgramCSV.setRegion(regionNameForTest);

        when(ecoProgramRepository.save(any())).thenReturn(EcoProgramEntity.of(ecoProgramCSV, regionEntityForTest));
        when(apiSearchAddressService.getRegion(any())).thenReturn(regionEntityForTest);
        when(regionRepository.findByRegionName(any())).thenReturn(Optional.of(regionEntityForTest));

        manageProgramService.saveEcoProgram(ecoProgramCSV);

        verify(ecoProgramRepository, times(1)).save(any());
        verify(elasticRepository, times(1)).save(any());

    }

    @Test
    public void saveRegion_FOUND_REGION_IN_REGION_TABLE_orElseGet_수행_0번_수행() {

        when(apiSearchAddressService.getRegion(regionNameForTest)).thenReturn(regionEntityForTest);
        when(regionRepository.findByRegionName(regionNameForTest)).thenReturn(Optional.of(regionEntityForTest));

        manageProgramService.saveRegion(regionNameForTest);

        verify(regionRepository, times(0)).save(regionEntityForTest);
    }

    @Test
    public void saveRegion_FOUND_REGION_IN_REGION_TABLE_결과_비교() {

        when(apiSearchAddressService.getRegion(regionNameForTest)).thenReturn(regionEntityForTest);
        when(regionRepository.findByRegionName(regionNameForTest)).thenReturn(Optional.of(regionEntityForTest));

        RegionEntity regionEntityResult = manageProgramService.saveRegion(regionNameForTest);

        assertEquals(regionCodeForTest, regionEntityResult.getRegionCode());
        assertEquals(regionNameForTest, regionEntityResult.getRegionName());
    }

    @Test
    public void saveRegion_NOT_FOUND_REGION_IN_REGION_TABLE_orElseGet_1번_수행() {

        when(apiSearchAddressService.getRegion(regionNameForTest)).thenReturn(regionEntityForTest);
        when(regionRepository.findByRegionName(regionNameForTest)).thenReturn(Optional.empty());
        when(regionRepository.save(regionEntityForTest)).thenReturn(regionEntityForTest);

        manageProgramService.saveRegion(regionNameForTest);

        verify(regionRepository, times(1)).save(regionEntityForTest);
    }

    @Test
    public void saveRegion_NOT_FOUND_REGION_IN_REGION_TABLE_결과_비교() {

        when(apiSearchAddressService.getRegion(regionNameForTest)).thenReturn(regionEntityForTest);
        when(regionRepository.findByRegionName(regionNameForTest)).thenReturn(Optional.empty());
        when(regionRepository.save(regionEntityForTest)).thenReturn(regionEntityForTest);

        RegionEntity regionEntityResult = manageProgramService.saveRegion(regionNameForTest);

        assertEquals(regionCodeForTest, regionEntityResult.getRegionCode());
        assertEquals(regionNameForTest, regionEntityResult.getRegionName());
    }
}