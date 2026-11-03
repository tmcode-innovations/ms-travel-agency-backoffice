package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderServiceStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static RequestOrderServiceStateResponse from(RequestOrderServiceStateEnum.RequestOrderServiceStateModel domain) {
        RequestOrderServiceStateResponse requestOrderServiceStateResponse = new RequestOrderServiceStateResponse();
        requestOrderServiceStateResponse.setId(domain.id());
        requestOrderServiceStateResponse.setName(domain.name());
        requestOrderServiceStateResponse.setDescription(domain.description());
        return requestOrderServiceStateResponse;
    }
}
