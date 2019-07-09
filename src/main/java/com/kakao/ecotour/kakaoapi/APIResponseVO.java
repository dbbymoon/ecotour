package com.kakao.ecotour.kakaoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakao.ecotour.jpa.RegionEntity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
class APIResponseVO {

    @JsonProperty
    private List<Document> documents;

    Optional<RegionEntity> getRegion() {
        return documents.isEmpty() ? Optional.empty()
                : Optional.of(documents.get(0).address.getRegion());
    }

    private static class Document {

        @JsonProperty
        private Address address;

        private static class Address {

            @JsonProperty
            private String address_name;

            @JsonProperty
            private String h_code;

            RegionEntity getRegion() {
                return RegionEntity.of(RegionInfoRefiner.getRegionDTO(h_code, address_name));
            }

        }

    }
}
