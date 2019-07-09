package com.kakao.ecotour.kakaoapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RegionInfoRefiner {

    static RegionDTO getRegionDTO(String regionCode, String regionName) {
        return new RegionDTO(getRegionCode(regionCode), getRegionName(regionName));
    }

    private static String getRegionCode(String regionCode) {
        return "reg" + regionCode.substring(0, 5);
    }

    private static String getRegionName(String regionName) {
        String[] addressArr = regionName.split(" ");
        return addressArr.length < 2 ? addressArr[0] : addressArr[0] + " " + addressArr[1];
    }


    static String refineAddress(String address) {
        // 쉼표(,) 로 끊겨있는 지역 주소
        // ex : 강원도 속초, 양양, 고성 => 강원도
        if (address.contains(",")) {
            address = address.substring(0, address.indexOf(','));
            address = address.substring(0, address.lastIndexOf(' '));
        }
        return getRegionName(address);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionDTO {
        String regionCode;
        String regionName;
    }

}
