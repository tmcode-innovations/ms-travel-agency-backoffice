package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDateSearchResponse {

    @JsonProperty("entities")
    private List<ProgramDateResponse> programDates;

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

}
