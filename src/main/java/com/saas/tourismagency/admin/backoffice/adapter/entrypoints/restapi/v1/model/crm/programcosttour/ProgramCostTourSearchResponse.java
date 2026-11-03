package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCostTourSearchResponse {

    @JsonProperty("entities")
    private List<ProgramCostTourResponse> programCostTours;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
