package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static GenderResponse from(GenderEnum.GenderModel domain) {
        GenderResponse genderResponse = new GenderResponse();
        genderResponse.setId(domain.id());
        genderResponse.setName(domain.name());
        genderResponse.setDescription(domain.description());
        return genderResponse;
    }
}
