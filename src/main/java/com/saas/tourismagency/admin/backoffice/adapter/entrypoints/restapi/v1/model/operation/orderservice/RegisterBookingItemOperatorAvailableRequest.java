package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemOperatorAvailableRequest {

    @JsonProperty("idBookingOperator")
    private String idBookingOperator;

    @JsonProperty("idOrderService")
    private String idOrderService;

    @JsonProperty("idRequestOrderService")
    private String idRequestOrderService;;

}
