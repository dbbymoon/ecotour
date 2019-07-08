package com.kakao.ecotour.controller;

import com.kakao.ecotour.elastic.KeywordFrequencyResultDto;
import com.kakao.ecotour.elastic.KeywordSearchRegionCountResultDto;
import com.kakao.ecotour.elastic.RecommendProgramDto;
import com.kakao.ecotour.elastic.RegionSearchResultDto;
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
    @ApiOperation(value = "프로그램 목록 조회")
    public List<EcoProgramCsv> getEcoProgramList() {
        return searchProgramService.getEcoProgramList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "단일 프로그램 조회 by id")
    public EcoProgramCsv getEcoProgram(@PathVariable("id") long id) throws ProgramNotFoundException {
        return searchProgramService.getEcoProgram(id);
    }

    @GetMapping("/region/{regionCode}")
    @ApiOperation(value = "프로그램 조회 by 서비스 지역 코드")
    public List<EcoProgramCsv> getEcoProgramListByRegionCode(@PathVariable("regionCode") String regionCode) {
        return searchProgramService.getEcoProgramListByRegionCode(regionCode);
    }

    @GetMapping("/search")
    @ApiOperation(value = "프로그램 조회 by 서비스 지역")
    public RegionSearchResultDto getEcoProgramListByRegion(@RequestParam("region") String region) throws SearchResultNotExistException {
        return searchProgramService.getEcoProgramListByRegion(region);
    }

    @GetMapping("/count/region")
    @ApiOperation(value = "서비스 지역 개수 조회 by 프로그램 소개 컬럼 키워드")
    public KeywordSearchRegionCountResultDto getRegionCountByProgramInfoKeyword(@RequestParam("keyword") String keyword) {
        return searchProgramService.getRegionCountByProgramInfoKeyword(keyword);
    }

    @GetMapping("/count/keyword")
    @ApiOperation(value = "키워드 출현 빈도수 조회 by 프로그램 상세 소개 컬럼 키워드")
    public KeywordFrequencyResultDto getFrequencyByProgramDetailInfoKeyword(@RequestParam("keyword") String keyword) {
        return searchProgramService.getFrequencyByProgramDetailInfoKeyword(keyword);
    }

    @GetMapping("/recommend")
    @ApiOperation(value = "프로그램 추천 by 서비스 지역, 키워드")
    public RecommendProgramDto recommendProgramByRegionAndKeyword(@RequestParam("region") String region, @RequestParam("keyword") String keyword) throws SearchResultNotExistException {
        return searchProgramService.recommendProgramByRegionAndKeyword(region, keyword);
    }

}
