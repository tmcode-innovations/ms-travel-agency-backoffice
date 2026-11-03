package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAccountStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static DocumentAccountStateResponse from(DocumentAccountStateEnum.DocumentAccountStateModel domain) {
        DocumentAccountStateResponse documentAccountStateResponse = new DocumentAccountStateResponse();
        documentAccountStateResponse.setId(domain.id());
        documentAccountStateResponse.setName(domain.name());
        documentAccountStateResponse.setDescription(domain.description());
        return documentAccountStateResponse;
    }
}
