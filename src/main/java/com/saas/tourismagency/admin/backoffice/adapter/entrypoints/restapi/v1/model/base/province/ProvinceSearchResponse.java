package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceSearchResponse {

    @JsonProperty("entities")
    private List<ProvinceResponse> provinces;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
