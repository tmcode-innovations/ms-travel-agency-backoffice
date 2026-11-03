package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ExpenseGuideStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGuideStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public static ExpenseGuideStateResponse from(ExpenseGuideStateEnum.ExpenseGuideStateModel domain) {
        ExpenseGuideStateResponse expenseGuideStateResponse = new ExpenseGuideStateResponse();
        expenseGuideStateResponse.setId(domain.id());
        expenseGuideStateResponse.setName(domain.name());
        return expenseGuideStateResponse;
    }
}
