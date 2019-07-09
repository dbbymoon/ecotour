package com.kakao.ecotour.elastic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.kakao.ecotour.jpa.EcoProgramEntity;
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
@Setting(settingPath = "/elastic/analyzer-settings.json")
public class EcoProgramDocument {

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

    public static EcoProgramDocument of(EcoProgramEntity ecoProgramEntity) {
        EcoProgramDocument ecoProgramDocument = ModelMapperUtils.getModelMapper().map(ecoProgramEntity, EcoProgramDocument.class);
        ecoProgramDocument.setRegionCode(ecoProgramEntity.getRegionCity().getRegionCode());
        ecoProgramDocument.setRegionName(ecoProgramEntity.getRegionCity().getRegionName());
        return ecoProgramDocument;
    }

    public static List<EcoProgramDocument> of(List<EcoProgramEntity> ecoProgramEntityList) {
        return ecoProgramEntityList.stream()
                .map(EcoProgramDocument::of)
                .collect(Collectors.toList());
    }

}
