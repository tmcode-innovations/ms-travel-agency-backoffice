package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderServiceChangeStateRequest {

    @JsonProperty("requestOrderServiceState")
    private String requestOrderServiceState;

    @JsonProperty("observation")
    private String observation;
}
