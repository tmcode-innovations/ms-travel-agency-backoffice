package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.TypeTourConductEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeTourConductResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public static TypeTourConductResponse from(TypeTourConductEnum.TypeTourConductModel domain) {
        TypeTourConductResponse typeTourConductResponse = new TypeTourConductResponse();
        typeTourConductResponse.setId(domain.id());
        typeTourConductResponse.setName(domain.name());
        return typeTourConductResponse;
    }
}
