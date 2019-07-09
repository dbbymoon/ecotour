package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCSV;
import com.kakao.ecotour.elastic.EcoProgramDocument;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.exception.ProgramNotFoundException;
import com.kakao.ecotour.jpa.EcoProgramEntity;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchProgramServiceTest {

    @InjectMocks
    private SearchProgramService searchProgramService;

    @Mock
    private EcoProgramElasticRepository ecoProgramElasticRepository;

    @Mock
    private EcoProgramRepository ecoProgramRepository;

    private EcoProgramEntity generateEcoProgramEntity(int n) {
        EcoProgramEntity ecoProgramEntity = new EcoProgramEntity();
        ecoProgramEntity.setPrgmSeq(n);
        ecoProgramEntity.setPrgmName("프로그램명" + n);
        return ecoProgramEntity;
    }

    private EcoProgramDocument generateEcoProgramDocument(int n, String regionCode) {
        EcoProgramDocument ecoProgramDocument = new EcoProgramDocument();
        ecoProgramDocument.setPrgmSeq(n);
        ecoProgramDocument.setPrgmName("프로그램명" + n);
        ecoProgramDocument.setRegionCode(regionCode);
        return ecoProgramDocument;
    }

    @Test(expected = ProgramNotFoundException.class)
    public void getEcoProgram_PROGRAM_NOT_FOUND_EXCEPTION() {
        when(ecoProgramRepository.findById(anyLong())).thenReturn(Optional.empty());
        searchProgramService.getEcoProgram(1);
    }

    @Test
    public void getEcoProgram() {
        EcoProgramEntity ecoProgramEntity = generateEcoProgramEntity(1);

        when(ecoProgramRepository.findById(1L)).thenReturn(Optional.of(ecoProgramEntity));

        EcoProgramCSV ecoProgramCSVResult = searchProgramService.getEcoProgram(1);

        assertEquals(ecoProgramEntity.getPrgmSeq(), ecoProgramCSVResult.getPrgmSeq());
        assertEquals(ecoProgramEntity.getPrgmName(), ecoProgramCSVResult.getPrgmName());
    }

    @Test
    public void getEcoProgramList_EMPTY_LIST() {
        when(ecoProgramRepository.findAll()).thenReturn(Collections.emptyList());
        List<EcoProgramCSV> ecoProgramCSVListResult = searchProgramService.getEcoProgramList();

        assertTrue(ecoProgramCSVListResult.isEmpty());
    }

    @Test
    public void getEcoProgramList() {
        List<EcoProgramEntity> ecoProgramEntityList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ecoProgramEntityList.add(generateEcoProgramEntity(i));
        }

        when(ecoProgramRepository.findAll()).thenReturn(ecoProgramEntityList);

        List<EcoProgramCSV> ecoProgramCSVListResult = searchProgramService.getEcoProgramList();

        assertEquals(10, ecoProgramCSVListResult.size());
        assertEquals(1, ecoProgramCSVListResult.get(0).getPrgmSeq());
        assertEquals("프로그램명1", ecoProgramCSVListResult.get(0).getPrgmName());
    }

    @Test
    public void getEcoProgramListByRegionCode_EMPTY_LIST() {
        when(ecoProgramElasticRepository.findByRegionCode(anyString())).thenReturn(Collections.emptyList());
        List<EcoProgramCSV> ecoProgramCSVListResult = searchProgramService.getEcoProgramListByRegionCode("reg42210");

        assertTrue(ecoProgramCSVListResult.isEmpty());
    }

    @Test
    public void getEcoProgramListByRegionCode() {
        final String regionCode = "reg42210";

        List<EcoProgramDocument> ecoProgramDocumentList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) ecoProgramDocumentList.add(generateEcoProgramDocument(i, regionCode));
        when(ecoProgramElasticRepository.findByRegionCode(regionCode)).thenReturn(ecoProgramDocumentList);

        List<EcoProgramCSV> ecoProgramCSVListResult = searchProgramService.getEcoProgramListByRegionCode(regionCode);

        assertEquals(10, ecoProgramCSVListResult.size());
        assertEquals(1, ecoProgramCSVListResult.get(0).getPrgmSeq());
        assertEquals("프로그램명1", ecoProgramCSVListResult.get(0).getPrgmName());
    }


}