package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestCategoryModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestCategoryModelResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ChangeRequestCategoryModelResponse from(ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel domain) {
        ChangeRequestCategoryModelResponse changeRequestCategoryResponse = new ChangeRequestCategoryModelResponse();
        changeRequestCategoryResponse.setId(domain.id());
        changeRequestCategoryResponse.setName(domain.name());
        changeRequestCategoryResponse.setDescription(domain.description());
        return changeRequestCategoryResponse;
    }
}
