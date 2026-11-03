package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ProformaStateResponse from(ProformaStateEnum.ProformaStateModel domain) {
        ProformaStateResponse proformaStateResponse = new ProformaStateResponse();
        proformaStateResponse.setId(domain.id());
        proformaStateResponse.setName(domain.name());
        proformaStateResponse.setDescription(domain.description());
        return proformaStateResponse;
    }
}
