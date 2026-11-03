package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictSearchResponse {

    @JsonProperty("entities")
    private List<DistrictResponse> districts;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
