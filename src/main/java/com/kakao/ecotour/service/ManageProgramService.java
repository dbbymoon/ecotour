package com.kakao.ecotour.service;

import com.kakao.ecotour.controller.EcoProgramCsv;
import com.kakao.ecotour.elastic.EcoProgramDto;
import com.kakao.ecotour.elastic.EcoProgramElasticRepository;
import com.kakao.ecotour.jpa.EcoProgram;
import com.kakao.ecotour.jpa.EcoProgramRepository;
import com.kakao.ecotour.jpa.Region;
import com.kakao.ecotour.jpa.RegionRepository;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Transactional
@Service
public class ManageProgramService {

    private final EcoProgramRepository ecoProgramRepository;

    private final RegionRepository regionRepository;

    private final EcoProgramElasticRepository ecoProgramElasticRepository;

    public ManageProgramService(EcoProgramRepository ecoProgramRepository, RegionRepository regionRepository,
                                EcoProgramElasticRepository ecoProgramElasticRepository) {
        this.ecoProgramRepository = ecoProgramRepository;
        this.regionRepository = regionRepository;
        this.ecoProgramElasticRepository = ecoProgramElasticRepository;
    }

    public EcoProgramDto saveEcoProgram(EcoProgramCsv ecoProgramCsv) {
        // DB save
        Region region = new Region();
        try {
            region = saveRegion(ecoProgramCsv.getRegion());
        } catch (JSONException e) {}

        EcoProgram ecoProgram = ecoProgramRepository.save(EcoProgram.of(ecoProgramCsv, region));
        // ES save
        EcoProgramDto ecoProgramDto = ecoProgramElasticRepository.save(EcoProgramDto.of(ecoProgram));
        return ecoProgramDto;
    }

    public JSONObject getRegion(String address) throws JSONException {

        String[] addressArr = address.split(" ");
        String regionName = addressArr.length < 2 ? addressArr[0] : addressArr[0] + " " + addressArr[1];

        final String kakaoApiKey = "KakaoAK 06d5b3ce748adc9daebf56edad7b5876";
        final String url = "https://dapi.kakao.com/v2/local/search/address.json?query=";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", kakaoApiKey);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> response = restTemplate.exchange(url + regionName, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        JSONArray json = new JSONObject(response.getBody()).getJSONArray("documents");
        if (json.length() == 0 && addressArr.length > 2) {
            regionName = regionName.split(" ")[0];
            response = restTemplate.exchange(url + regionName, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        }
        json = new JSONObject(response.getBody()).getJSONArray("documents");

        if (json.length() == 0) {
            // 검색 결과 없음
            return null;
        } else {
            return json.getJSONObject(0).getJSONObject("address");
        }

    }

    protected Region saveRegion(String address) throws JSONException {

        JSONObject regionJsonObject = getRegion(address);

        String regionCode = ((String) regionJsonObject.get("b_code")).substring(0, 5);
        String[] addressArr = ((String) regionJsonObject.get("address_name")).split(" ");

        String regionName = addressArr.length > 1 ? addressArr[0] : addressArr[0] + " " + addressArr[1];

        Region region = regionRepository.findByRegionName(regionName)
                .orElseGet(() -> regionRepository.save(new Region(regionCode, regionName)));

        return region;
    }

}
