package com.kakao.ecotour.kakaoapi;

import com.kakao.ecotour.exception.APINotFoundAddressException;
import com.kakao.ecotour.jpa.RegionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@PropertySource("classpath:/kakaoapi.properties")
public class APISearchAddressService {

    @Value("${kakao.api.key}")
    private String apiKey;

    @Value("${kakao.api.url}")
    private String apiUrl;

    // RegionEntity 반환
    public RegionEntity getRegion(String address) {

        String regionName = RegionInfoRefiner.refineAddress(address);

        APIResponseVO response = getResponse(regionName);

        try {
            return response.getRegion()
                    .orElseGet(() -> getResponse(regionName.split(" ")[0]).getRegion()
                            .orElseThrow(APINotFoundAddressException::new));
        } catch (APINotFoundAddressException e) {
            log.debug(e.getMessage());
            return new RegionEntity("reg" + address.hashCode(), address);
        }

    }

    // REST API 요청
    private APIResponseVO getResponse(String regionName) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", apiKey);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<APIResponseVO> response
                = restTemplate.exchange(apiUrl + regionName, HttpMethod.GET, new HttpEntity<>(httpHeaders), APIResponseVO.class);

        return response.getBody();
    }


}
