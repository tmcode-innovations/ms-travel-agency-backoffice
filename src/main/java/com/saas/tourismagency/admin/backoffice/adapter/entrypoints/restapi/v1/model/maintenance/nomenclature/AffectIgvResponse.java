package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.AffectIgvEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectIgvResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static AffectIgvResponse from(AffectIgvEnum.AffectIgvModel domain) {
        AffectIgvResponse affectIgvResponse = new AffectIgvResponse();
        affectIgvResponse.setId(domain.id());
        affectIgvResponse.setName(domain.name());
        affectIgvResponse.setDescription(domain.description());
        return affectIgvResponse;
    }
}
