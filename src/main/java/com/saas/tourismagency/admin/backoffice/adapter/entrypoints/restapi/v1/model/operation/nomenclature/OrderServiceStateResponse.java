package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static OrderServiceStateResponse from(OrderServiceStateEnum.OrderServiceStateModel domain) {
        OrderServiceStateResponse orderServiceStateResponse = new OrderServiceStateResponse();
        orderServiceStateResponse.setId(domain.id());
        orderServiceStateResponse.setName(domain.name());
        orderServiceStateResponse.setDescription(domain.description());
        return orderServiceStateResponse;
    }
}
