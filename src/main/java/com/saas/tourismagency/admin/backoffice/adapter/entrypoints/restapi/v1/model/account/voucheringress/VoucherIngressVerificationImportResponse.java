package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherIngressVerificationImportResponse {

    @JsonProperty("cancelImportTotalUsd")
    private Double cancelImportTotalUsd;

    @JsonProperty("cancelImportTotalSoles")
    private Double cancelImportTotalSoles;

    @JsonProperty("proformImportTotalUsd")
    private Double proformImportTotalUsd;

    @JsonProperty("proformImportTotalSoles")
    private Double proformImportTotalSoles;

    @JsonProperty("pendientImportTotalUsd")
    private Double pendientImportTotalUsd;

    @JsonProperty("pendientImportTotalSoles")
    private Double pendientImportTotalSoles;
}
