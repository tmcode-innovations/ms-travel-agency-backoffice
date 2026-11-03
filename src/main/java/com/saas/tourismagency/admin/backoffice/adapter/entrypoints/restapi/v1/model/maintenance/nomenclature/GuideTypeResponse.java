package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.GuideTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static GuideTypeResponse from(GuideTypeEnum.GuideTypeModel domain) {
        GuideTypeResponse guideTypeResponse = new GuideTypeResponse();
        guideTypeResponse.setId(domain.id());
        guideTypeResponse.setName(domain.name());
        guideTypeResponse.setDescription(domain.description());
        return guideTypeResponse;
    }
}
