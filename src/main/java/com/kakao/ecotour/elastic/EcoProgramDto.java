package com.kakao.ecotour.elastic;

import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.util.ModelMapperUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Document(indexName = "program", type = "eco_program")
public class EcoProgramDto {

    @Id
    private long prgmSeq;

    private String prgmName;

    private String theme;

    @Field(fielddata = true)
    private String regionCode;

    private String regionName;

    private String region;

    private String prgmInfo = "";

    private String prgmDetailInfo = "";

    public static EcoProgramDto of(EcoProgram ecoProgram) {
        EcoProgramDto ecoProgramDto = ModelMapperUtils.getModelMapper().map(ecoProgram, EcoProgramDto.class);
        ecoProgramDto.setRegionCode(ecoProgram.getRegionCity().getRegionCode());
        ecoProgramDto.setRegionName(ecoProgram.getRegionCity().getRegionName());
        return ecoProgramDto;
    }

    public static List<EcoProgramDto> of(List<EcoProgram> ecoProgramList) {
        return ecoProgramList.stream()
                .map(EcoProgramDto::of)
                .collect(Collectors.toList());
    }

}
