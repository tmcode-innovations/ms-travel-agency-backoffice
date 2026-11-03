package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationSearchResponse {

    @JsonProperty("entities")
    private List<QuotationResponse> quotations;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("totalElements")
    private long totalElements;

}
