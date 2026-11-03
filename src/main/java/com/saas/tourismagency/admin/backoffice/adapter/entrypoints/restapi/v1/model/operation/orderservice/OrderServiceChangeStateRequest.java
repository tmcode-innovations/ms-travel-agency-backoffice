package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceChangeStateRequest {

    @JsonProperty("orderServiceState")
    private String orderServiceState;

    @JsonProperty("observation")
    private String observation;
}
