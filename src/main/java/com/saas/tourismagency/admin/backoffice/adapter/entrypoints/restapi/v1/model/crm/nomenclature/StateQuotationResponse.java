package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateQuotationResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static StateQuotationResponse from(StateQuotationEnum.StateQuotationModel domain) {
        StateQuotationResponse stateQuotationResponse = new StateQuotationResponse();
        stateQuotationResponse.setId(domain.id());
        stateQuotationResponse.setName(domain.name());
        stateQuotationResponse.setDescription(domain.description());
        return stateQuotationResponse;
    }
}
