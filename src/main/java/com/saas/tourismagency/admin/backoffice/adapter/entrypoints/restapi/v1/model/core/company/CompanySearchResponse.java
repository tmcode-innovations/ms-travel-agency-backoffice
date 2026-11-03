package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySearchResponse {

    @JsonProperty("entities")
    private List<CompanyResponse> companies;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
