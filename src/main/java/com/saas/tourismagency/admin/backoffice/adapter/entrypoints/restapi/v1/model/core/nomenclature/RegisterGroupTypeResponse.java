package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.RegisterGroupTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterGroupTypeResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static RegisterGroupTypeResponse from(RegisterGroupTypeEnum.RegisterGroupTypeModel domain) {
        RegisterGroupTypeResponse registerGroupTypeResponse = new RegisterGroupTypeResponse();
        registerGroupTypeResponse.setId(domain.id());
        registerGroupTypeResponse.setName(domain.name());
        registerGroupTypeResponse.setDescription(domain.description());
        return registerGroupTypeResponse;
    }
}
