package com.kakao.ecotour.controller;

import com.kakao.ecotour.elastic.KeywordFrequencyResultVO;
import com.kakao.ecotour.elastic.KeywordSearchRegionCountResultVO;
import com.kakao.ecotour.elastic.RecommendProgramVO;
import com.kakao.ecotour.elastic.RegionSearchResultVO;
import com.kakao.ecotour.exception.ProgramNotFoundException;
import com.kakao.ecotour.exception.SearchResultNotExistException;
import com.kakao.ecotour.service.SearchProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "생태 관광 프로그램 검색")
@RestController
@RequestMapping("/programs")
public class SearchProgramController {

    private final SearchProgramService searchProgramService;

    public SearchProgramController(SearchProgramService searchProgramService) {
        this.searchProgramService = searchProgramService;
    }

    @GetMapping
    @ApiOperation(value = "프로그램 전체 조회")
    public List<EcoProgramCSV> getEcoProgramList() {
        return searchProgramService.getEcoProgramList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "단일 프로그램 조회 by id")
    public EcoProgramCSV getEcoProgram(@PathVariable("id") long id) throws ProgramNotFoundException {
        return searchProgramService.getEcoProgram(id);
    }

    @GetMapping("/region/{regionCode}")
    @ApiOperation(value = "프로그램 조회 by 서비스 지역 코드")
    public List<EcoProgramCSV> getEcoProgramListByRegionCode(@PathVariable("regionCode") String regionCode) {
        return searchProgramService.getEcoProgramListByRegionCode(regionCode);
    }

    @GetMapping("/search")
    @ApiOperation(value = "프로그램 조회 by 서비스 지역명")
    public RegionSearchResultVO getEcoProgramListByRegionName(@RequestParam("region") String region) throws SearchResultNotExistException {
        return searchProgramService.getEcoProgramListByRegionName(region);
    }

    @GetMapping("/count/region")
    @ApiOperation(value = "서비스 지역 개수 조회 by 프로그램 소개 컬럼 키워드")
    public KeywordSearchRegionCountResultVO getRegionCountByProgramInfoKeyword(@RequestParam("keyword") String keyword) {
        return searchProgramService.getRegionCountByProgramInfoKeyword(keyword);
    }

    @GetMapping("/count/keyword")
    @ApiOperation(value = "키워드 출현 빈도수 조회 by 프로그램 상세 소개 컬럼 키워드")
    public KeywordFrequencyResultVO getFrequencyByProgramDetailInfoKeyword(@RequestParam("keyword") String keyword) {
        return searchProgramService.getFrequencyByProgramDetailInfoKeyword(keyword);
    }

    @GetMapping("/recommend")
    @ApiOperation(value = "프로그램 추천 by 서비스 지역, 키워드")
    public RecommendProgramVO recommendProgramByRegionAndKeyword(@RequestParam("region") String region, @RequestParam("keyword") String keyword) throws SearchResultNotExistException {
        return searchProgramService.recommendProgramByRegionAndKeyword(region, keyword);
    }

}
