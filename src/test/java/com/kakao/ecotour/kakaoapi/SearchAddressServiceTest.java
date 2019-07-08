package com.kakao.ecotour.kakaoapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchAddressServiceTest {

    @InjectMocks
    private SearchAddressService searchAddressService;

    @Test
    public void test() {
        System.out.println(searchAddressService.getRegion("강원도 속초"));
        System.out.println(searchAddressService.getRegion("강원도 속초시"));
        System.out.println(searchAddressService.getRegion("강원도 오대산국립공원"));
        System.out.println(searchAddressService.getRegion("강원도 원주시 소초면 무쇠점2길"));
        System.out.println(searchAddressService.getRegion("경상남도 일대"));
        System.out.println(searchAddressService.getRegion("경상남도"));
        System.out.println(searchAddressService.getRegion("서울특별시"));
        System.out.println(searchAddressService.getRegion("서울특별시 도봉구"));
    }
}