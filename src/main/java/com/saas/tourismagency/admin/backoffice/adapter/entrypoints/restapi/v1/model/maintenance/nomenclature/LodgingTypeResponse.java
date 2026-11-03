package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.LodgingTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static LodgingTypeResponse from(LodgingTypeEnum.LodgingTypeModel domain) {
        LodgingTypeResponse lodgingTypeResponse = new LodgingTypeResponse();
        lodgingTypeResponse.setId(domain.id());
        lodgingTypeResponse.setName(domain.name());
        lodgingTypeResponse.setDescription(domain.description());
        return lodgingTypeResponse;
    }
}
