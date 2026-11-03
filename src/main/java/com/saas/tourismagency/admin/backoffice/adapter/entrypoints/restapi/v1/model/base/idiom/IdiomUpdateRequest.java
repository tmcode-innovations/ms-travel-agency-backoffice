package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdiomUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public IdiomDomain to() {
        IdiomDomain idiomDomain = new IdiomDomain();
        idiomDomain.setId(id);
        idiomDomain.setName(name);
        idiomDomain.setDescription(description);
        return idiomDomain;
    }

}
