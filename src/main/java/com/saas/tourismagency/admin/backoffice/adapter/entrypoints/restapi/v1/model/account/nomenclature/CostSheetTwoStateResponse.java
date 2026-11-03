package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.CostSheetTwoStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static CostSheetTwoStateResponse from(CostSheetTwoStateEnum.CostSheetTwoStateModel domain) {
        CostSheetTwoStateResponse costSheetTwoStateResponse = new CostSheetTwoStateResponse();
        costSheetTwoStateResponse.setId(domain.id());
        costSheetTwoStateResponse.setName(domain.name());
        costSheetTwoStateResponse.setDescription(domain.description());
        return costSheetTwoStateResponse;
    }
}
