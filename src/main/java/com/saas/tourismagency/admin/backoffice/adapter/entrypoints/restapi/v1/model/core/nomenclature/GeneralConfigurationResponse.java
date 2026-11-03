package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralConfigurationResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static GeneralConfigurationResponse from(GeneralConfigurationTypeEnum.GeneralConfigurationTypeModel domain) {
        GeneralConfigurationResponse generalConfigurationResponse = new GeneralConfigurationResponse();
        generalConfigurationResponse.setId(domain.id());
        generalConfigurationResponse.setName(domain.name());
        generalConfigurationResponse.setDescription(domain.description());
        return generalConfigurationResponse;
    }
}
