package com.kakao.ecotour.kakaoapi;

import com.kakao.ecotour.jpa.RegionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APISearchAddressServiceTest {

    @Autowired
    private APISearchAddressService apiSearchAddressService;

    @Test
    public void getRegion_정확한_주소_입력() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 광주시 오포읍 신현리 806-6번지");
        assertEquals("경기 광주시", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_읍면동_주소() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 광주시 오포읍");
        assertEquals("경기 광주시", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_읍면동_주소2() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 오포읍");
        assertEquals("경기 광주시", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_시군구_주소() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 광주시");
        assertEquals("경기 광주시", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_시군구_주소2() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 광주");
        assertEquals("경기 광주시", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_도_주소() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도");
        assertEquals("경기", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_도_주소2() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기");
        assertEquals("경기", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_광역시_특별시_구_주소() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("서울특별시 강남구");
        assertEquals("서울 강남구", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_광역시_특별시_주소() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("서울특별시");
        assertEquals("서울", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_광역시_특별시_주소2() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("서울");
        assertEquals("서울", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_국립공원() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("강원도 오대산국립공원");
        assertEquals("강원", regionEntity.getRegionName());
    }

    @Test
    public void getRegion_쉼표_사용() {
        RegionEntity regionEntity = apiSearchAddressService.getRegion("경기도 광주시, 성남시");
        assertEquals("경기", regionEntity.getRegionName());
    }


}