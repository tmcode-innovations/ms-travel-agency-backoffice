package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestStateResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ChangeRequestStateResponse from(ChangeRequestStatusEnum.ChangeRequestStatusModel domain) {
        ChangeRequestStateResponse changeRequestStateResponse = new ChangeRequestStateResponse();
        changeRequestStateResponse.setId(domain.id());
        changeRequestStateResponse.setName(domain.name());
        changeRequestStateResponse.setDescription(domain.description());
        return changeRequestStateResponse;
    }
}
