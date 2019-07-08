package com.kakao.ecotour.elastic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.util.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor(onConstructor = @__({@JsonCreator}))
@Document(indexName = "program", type = "eco_program")
@Setting(settingPath = "/ngram-analyzer.json")
public class EcoProgramDto {

    @Id
    private long prgmSeq;

    private String prgmName;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String theme;

    @Field(type = FieldType.Keyword)
    private String regionCode;

    @Field(type = FieldType.Keyword)
    private String regionName;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String region;

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
    private String prgmInfo = "";

    @Field(type = FieldType.Text, analyzer = "ngram_analyzer")
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
