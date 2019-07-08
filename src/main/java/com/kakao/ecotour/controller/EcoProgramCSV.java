package com.kakao.ecotour.controller;

import com.kakao.ecotour.elastic.EcoProgramDocument;
import com.kakao.ecotour.jpa.EcoProgramEntity;
import com.kakao.ecotour.util.ModelMapperUtils;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EcoProgramCSV {

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

    public static EcoProgramCSV of(EcoProgramEntity ecoProgramEntity) {
        return ModelMapperUtils.getModelMapper().map(ecoProgramEntity, EcoProgramCSV.class);
    }

    public static List<EcoProgramCSV> of(List<EcoProgramEntity> ecoProgramEntityList) {
        return ecoProgramEntityList.stream().map(EcoProgramCSV::of).collect(Collectors.toList());
    }

    public static EcoProgramCSV ofDto(EcoProgramDocument ecoProgramDocument) {
        return ModelMapperUtils.getModelMapper().map(ecoProgramDocument, EcoProgramCSV.class);
    }

    public static List<EcoProgramCSV> ofDto(List<EcoProgramDocument> ecoProgramDocumentList) {
        return ecoProgramDocumentList.stream().map(EcoProgramCSV::ofDto).collect(Collectors.toList());
    }

}
