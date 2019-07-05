package com.kakao.ecotour.entity;

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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long regionSeq;    // 번호

    @Column(nullable = false)
    private String regionCode;

    @Column(nullable = false)
    private String regionName;  // 서비스 지역 이름

    @OneToMany(mappedBy = "regionCity")
    private List<EcoProgram> ecoProgramList;

    public Region(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}
