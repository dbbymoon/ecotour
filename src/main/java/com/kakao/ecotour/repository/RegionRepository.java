package com.kakao.ecotour.repository;

import com.kakao.ecotour.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByRegionNameContaining(String regionName);
    Optional<Region> findByRegionName(String regionName);
    Optional<Region> findByRegionCode(String regionCode);
}
