package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoChangeStateRequest {

    @JsonProperty("costSheetTwoState")
    private String costSheetTwoState;

    @JsonProperty("observation")
    private String observation;
}
