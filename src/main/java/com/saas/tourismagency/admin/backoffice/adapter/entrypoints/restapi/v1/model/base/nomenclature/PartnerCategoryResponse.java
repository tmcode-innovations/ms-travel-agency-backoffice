package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.PartnerCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerCategoryResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public static PartnerCategoryResponse from(PartnerCategoryEnum.PartnerCategoryModel domain) {
        PartnerCategoryResponse partnerCategoryResponse = new PartnerCategoryResponse();
        partnerCategoryResponse.setId(domain.id());
        partnerCategoryResponse.setName(domain.name());
        return partnerCategoryResponse;
    }
}
