package com.kakao.ecotour.dto;

import com.kakao.ecotour.entity.EcoProgram;
import com.kakao.ecotour.entity.Region;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class RegionDto {

    private String region;

    private List<Map<String, String>> programs;

    public static RegionDto of(Region region) {
        RegionDto regionDto = new RegionDto();

        List<EcoProgram> ecoPrograms = region.getEcoProgramList();
        List<Map<String, String>> programs = new ArrayList<>();
        ecoPrograms.forEach(ecoProgram -> {
                    Map<String, String> programInfo = new HashMap<>();
                    programInfo.put("prgm_name", ecoProgram.getPrgmName());
                    programInfo.put("theme", ecoProgram.getTheme());
                    programs.add(programInfo);
                });

        regionDto.setPrograms(programs);
        regionDto.setRegion(region.getRegionCode());

        return regionDto;
    }

    public static List<RegionDto> of(List<Region> regionList) {
        return regionList.stream().map(RegionDto::of)
                .collect(Collectors.toList());
    }
}
