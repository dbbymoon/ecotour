package com.kakao.ecotour.controller;

import com.kakao.ecotour.elastic.EcoProgramDto;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.util.ModelMapperUtils;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EcoProgramCsv {

    @CsvBindByName(column = "번호", required = true)
    private long prgmSeq;

    @CsvBindByName(column = "프로그램명", required = true)
    private String prgmName;

    @CsvBindByName(column = "테마별 분류", required = true)
    private String theme;

    @CsvBindByName(column = "서비스 지역", required = true)
    private String region;

    @CsvBindByName(column = "프로그램 소개")
    private String prgmInfo = "";

    @CsvBindByName(column = "프로그램 상세 소개")
    private String prgmDetailInfo = "";

    public static EcoProgramCsv of(EcoProgram ecoProgram) {
        return ModelMapperUtils.getModelMapper().map(ecoProgram, EcoProgramCsv.class);
    }

    public static List<EcoProgramCsv> of(List<EcoProgram> ecoProgramList) {
        return ecoProgramList.stream().map(EcoProgramCsv::of).collect(Collectors.toList());
    }

    public static EcoProgramCsv ofDto(EcoProgramDto ecoProgramDto) {
        return ModelMapperUtils.getModelMapper().map(ecoProgramDto, EcoProgramCsv.class);
    }

    public static List<EcoProgramCsv> ofDto(List<EcoProgramDto> ecoProgramDtoList) {
        return ecoProgramDtoList.stream().map(EcoProgramCsv::ofDto).collect(Collectors.toList());
    }

}
