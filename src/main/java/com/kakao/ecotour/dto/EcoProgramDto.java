package com.kakao.ecotour.dto;

import com.kakao.ecotour.entity.EcoProgram;
import com.kakao.ecotour.util.ModelMapperUtils;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@Document(indexName = "program", type = "eco_program")
public class EcoProgramDto {

    @Id
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

    public static EcoProgramDto of(EcoProgram ecoProgram) {
        EcoProgramDto ecoProgramDto = ModelMapperUtils.getModelMapper().map(ecoProgram, EcoProgramDto.class);
        return ecoProgramDto;
    }

    public static List<EcoProgramDto> of(List<EcoProgram> ecoProgramList) {
        return ecoProgramList.stream()
                .map(EcoProgramDto::of)
                .collect(Collectors.toList());
    }
}
