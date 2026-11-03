package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ProformaTypeResponse from(ProformaTypeEnum.ProformaTypeModel domain) {
        ProformaTypeResponse proformaTypeResponse = new ProformaTypeResponse();
        proformaTypeResponse.setId(domain.id());
        proformaTypeResponse.setName(domain.name());
        proformaTypeResponse.setDescription(domain.description());
        return proformaTypeResponse;
    }
}
