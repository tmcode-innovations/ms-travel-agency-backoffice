package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAccountTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("abbrev")
    private String abbrev;

    public static DocumentAccountTypeResponse from(DocumentAccountTypeEnum.DocumentAccountTypeModel domain) {
        DocumentAccountTypeResponse documentAccountTypeResponse = new DocumentAccountTypeResponse();
        documentAccountTypeResponse.setId(domain.id());
        documentAccountTypeResponse.setName(domain.name());
        documentAccountTypeResponse.setDescription(domain.description());
        documentAccountTypeResponse.setAbbrev(domain.abbrev());
        return documentAccountTypeResponse;
    }
}
