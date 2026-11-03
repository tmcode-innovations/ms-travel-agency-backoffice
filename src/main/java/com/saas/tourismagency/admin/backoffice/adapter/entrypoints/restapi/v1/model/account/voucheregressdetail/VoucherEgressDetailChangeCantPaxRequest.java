package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressDetailChangeCantPaxRequest {

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("observation")
    private String observation;
}
