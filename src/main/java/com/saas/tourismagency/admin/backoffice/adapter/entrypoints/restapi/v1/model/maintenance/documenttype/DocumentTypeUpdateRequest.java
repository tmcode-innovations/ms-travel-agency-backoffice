package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public DocumentTypeDomain to() {
        DocumentTypeDomain documentTypeDomain = new DocumentTypeDomain();
        documentTypeDomain.setId(id);
        documentTypeDomain.setName(name);
        documentTypeDomain.setDescription(description);
        return documentTypeDomain;
    }

}
