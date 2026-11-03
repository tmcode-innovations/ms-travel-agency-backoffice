package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateProgramEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateProgramResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static StateProgramResponse from(StateProgramEnum.StateProgramModel domain) {
        StateProgramResponse stateProgramResponse = new StateProgramResponse();
        stateProgramResponse.setId(domain.id());
        stateProgramResponse.setName(domain.name());
        stateProgramResponse.setDescription(domain.description());
        return stateProgramResponse;
    }
}
