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
    private long prgmSeq;

    @Column(nullable = false)
    private String prgmName;

    @Column(nullable = false)
    private String theme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "region_seq")
    private RegionEntity regionCity;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String prgmInfo;

    @Column(nullable = false, length = 1000000)
    private String prgmDetailInfo;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDatetime;

    public static EcoProgramEntity of(EcoProgramCSV ecoProgramCSV, RegionEntity regionEntity) {
        EcoProgramEntity ecoProgramEntity = ModelMapperUtils.getModelMapper().map(ecoProgramCSV, EcoProgramEntity.class);
        ecoProgramEntity.setRegionCity(regionEntity);
        return ecoProgramEntity;
    }

}
