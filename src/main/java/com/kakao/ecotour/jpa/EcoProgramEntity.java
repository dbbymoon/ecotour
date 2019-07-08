package com.kakao.ecotour.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.ecotour.controller.EcoProgramCSV;
import com.kakao.ecotour.util.ModelMapperUtils;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "prgmSeq", callSuper = false)
@Table(name = "eco_program")
public class EcoProgramEntity {

    @Id
    @Column(nullable = false)
    private long prgmSeq;    // 번호

    @Column(nullable = false)
    private String prgmName;    // 프로그램명

    @Column(nullable = false)
    private String theme;       // 테마별 분류

    @ManyToOne(optional = false)
    @JoinColumn(name = "region_seq")
    private RegionEntity regionEntityCity;  // 서비스 지역

    @Column(nullable = false)
    private String region;      // 서비스 지역

    @Column(nullable = false)
    private String prgmInfo;    // 프로그램 소개

    @Column(nullable = false, length = 1000000)
    private String prgmDetailInfo;  // 프로그램 상세 소개

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDatetime;

    public static EcoProgramEntity of(EcoProgramCSV ecoProgramCSV, RegionEntity regionEntity) {
        EcoProgramEntity ecoProgramEntity = ModelMapperUtils.getModelMapper().map(ecoProgramCSV, EcoProgramEntity.class);
        ecoProgramEntity.setRegionEntityCity(regionEntity);
        return ecoProgramEntity;
    }

}
