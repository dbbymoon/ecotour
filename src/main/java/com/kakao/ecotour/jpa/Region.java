package com.kakao.ecotour.jpa;

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
    private long regionSeq;

    @Column(nullable = false)
    private String regionCode;  // 서비스 지역 코드

    @Column(nullable = false)
    private String regionName;  // 서비스 지역 이름

    @OneToMany(mappedBy = "regionCity")
    private List<EcoProgram> ecoProgramList;

    public Region(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}
