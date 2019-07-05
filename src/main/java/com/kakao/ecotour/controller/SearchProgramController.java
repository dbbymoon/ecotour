package com.kakao.ecotour.controller;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.dto.RegionDto;
import com.kakao.ecotour.service.SearchProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class SearchProgramController {

    private final SearchProgramService searchProgramService;

    public SearchProgramController(SearchProgramService searchProgramService) {
        this.searchProgramService = searchProgramService;
    }

    @GetMapping
    public @ResponseBody List<EcoProgramDto> getEcoProgramList() {
        return searchProgramService.getEcoProgramList();
    }

    @GetMapping("/{regionCode}")
    public @ResponseBody List<EcoProgramDto> getEcoProgramListByRegionCode(@PathVariable("regionCode") String regionCode) {
        return searchProgramService.getEcoProgramListByRegionCode(regionCode);
    }

    @GetMapping("/search")
    public @ResponseBody List<RegionDto> getEcoProgramListByRegion(@RequestParam("region") String region) {
        return searchProgramService.getEcoProgramListByRegion(region);
    }

}
