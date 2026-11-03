package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.QuotationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationTypeResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static QuotationTypeResponse from(QuotationTypeEnum.QuotationTypeModel domain) {
        QuotationTypeResponse quotationTypeResponse = new QuotationTypeResponse();
        quotationTypeResponse.setId(domain.id());
        quotationTypeResponse.setName(domain.name());
        quotationTypeResponse.setDescription(domain.description());
        return quotationTypeResponse;
    }
}
