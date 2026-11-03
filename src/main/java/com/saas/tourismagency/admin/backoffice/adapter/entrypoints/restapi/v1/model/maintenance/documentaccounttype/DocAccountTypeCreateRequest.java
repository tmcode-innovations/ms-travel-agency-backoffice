package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocAccountTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("abbrev")
    private String abbrev;

    public DocumentAccountTypeDomain to() {
        DocumentAccountTypeDomain documentAccountTypeDomain = new DocumentAccountTypeDomain();
        documentAccountTypeDomain.setName(name);
        documentAccountTypeDomain.setDescription(description);
        documentAccountTypeDomain.setAbbrev(abbrev);
        return documentAccountTypeDomain;
    }
}
