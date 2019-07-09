package com.kakao.ecotour.jpa;

import com.kakao.ecotour.kakaoapi.RegionInfoRefiner;
import com.kakao.ecotour.util.ModelMapperUtils;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "regionSeq", callSuper = false)
@Table(name = "region")
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long regionSeq;

    @Column(nullable = false)
    private String regionCode;

    @Column(nullable = false)
    private String regionName;

    @OneToMany(mappedBy = "regionCity")
    private List<EcoProgramEntity> ecoProgramEntityList;

    public RegionEntity(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public static RegionEntity of(RegionInfoRefiner.RegionDTO regionDTO) {
        return ModelMapperUtils.getModelMapper().map(regionDTO, RegionEntity.class);
    }
}
