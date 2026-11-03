package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleBaseResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public static SimpleBaseResponse from(SimpleBaseDomain domain) {
        SimpleBaseResponse simpleBaseResponse = new SimpleBaseResponse();
        simpleBaseResponse.setId(domain.getId());
        simpleBaseResponse.setName(domain.getName());
        return simpleBaseResponse;
    }
}
