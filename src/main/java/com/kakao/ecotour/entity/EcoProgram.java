package com.kakao.ecotour.entity;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.util.ModelMapperUtils;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "prgmSeq", callSuper = false)
@Table(name = "eco_program")
public class EcoProgram extends SuperEntity {

    @Id
    @Column(nullable = false)
    private long prgmSeq;    // 번호

    @Column(nullable = false)
    private String prgmName;    // 프로그램명

    @Column(nullable = false)
    private String theme;       // 테마별 분류

    @ManyToOne(optional = false)
    @JoinColumn(name = "region_seq")
    private Region regionCity;  // 서비스 지역

    @Column(nullable = false)
    private String region;      // 서비스 지역

    @Column(nullable = false)
    private String prgmInfo;    // 프로그램 소개

    @Column(nullable = false, length = 1000000)
    private String prgmDetailInfo;  // 프로그램 상세 소개

    public static EcoProgram of(EcoProgramDto ecoProgramDto, Region region) {
        EcoProgram ecoProgram = ModelMapperUtils.getModelMapper().map(ecoProgramDto, EcoProgram.class);
        ecoProgram.setRegionCity(region);
        return ecoProgram;
    }

}
