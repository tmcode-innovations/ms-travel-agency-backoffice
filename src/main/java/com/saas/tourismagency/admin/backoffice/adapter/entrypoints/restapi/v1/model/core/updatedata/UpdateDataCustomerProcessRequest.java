package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.updatedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDataCustomerProcessRequest {

    @JsonProperty("identifyQuotation")
    private String identifyQuotation;

    @JsonProperty("nameCustomer")
    private String nameCustomer;

}
