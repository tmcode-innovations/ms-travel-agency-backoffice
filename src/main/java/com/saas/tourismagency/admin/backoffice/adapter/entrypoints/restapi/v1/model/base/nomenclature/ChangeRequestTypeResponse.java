package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestTypeResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ChangeRequestTypeResponse from(ChangeRequestTypeEnum.ChangeRequestTypeModel domain) {
        ChangeRequestTypeResponse changeRequestTypeResponse = new ChangeRequestTypeResponse();
        changeRequestTypeResponse.setId(domain.id());
        changeRequestTypeResponse.setName(domain.name());
        changeRequestTypeResponse.setDescription(domain.description());
        return changeRequestTypeResponse;
    }
}
