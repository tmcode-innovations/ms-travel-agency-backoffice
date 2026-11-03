package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateRegisterGroupResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static StateRegisterGroupResponse from(StateRegisterGroupEnum.StateRegisterGroupModel domain) {
        StateRegisterGroupResponse stateRegisterGroupResponse = new StateRegisterGroupResponse();
        stateRegisterGroupResponse.setId(domain.id());
        stateRegisterGroupResponse.setName(domain.name());
        stateRegisterGroupResponse.setDescription(domain.description());
        return stateRegisterGroupResponse;
    }
}
