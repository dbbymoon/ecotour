package com.kakao.ecotour.enumeration;

import java.util.Arrays;


public enum RegionEnum {

    세종특별자치시("reg3611"),
    제주특별자치도("reg5000"),
    경상남도("reg4800"),
    경상북도("reg4700"),
    전라남도("reg4600"),
    전라북도("reg4500"),
    충청남도("reg4400"),
    충청북도("reg4300"),
    강원도("reg4200"),
    경기도("reg4100"),
    울산광역시("reg3100"),
    대전광역시("reg3000"),
    광주광역시("reg2900"),
    인천광역시("reg2800"),
    대구광역시("reg2700"),
    부산광역시("reg2600"),
    서울특별시("reg2500"),
    NONE("");

    private String code;

    RegionEnum(String code) {
        this.code = code;
    }

    public static RegionEnum findByRegionName(String regionName) {
        return Arrays.stream(RegionEnum.values())
                .filter(region -> regionName.equals(region.name()))
                .findAny()
                .orElse(NONE);
    }

    public String getCode(String regionName) {
        return this.code + regionName.hashCode();
    }

}
