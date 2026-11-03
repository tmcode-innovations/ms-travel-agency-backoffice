package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationChangeStateRequest {

    @JsonProperty("stateQuotation")
    private String stateQuotation;

    @JsonProperty("employee")
    private String employee;

    @JsonProperty("observation")
    private String observation;
}
