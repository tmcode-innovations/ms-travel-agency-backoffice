package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NationalityCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public NationalityDomain to() {
        NationalityDomain nationalityDomain = new NationalityDomain();
        nationalityDomain.setName(name);
        nationalityDomain.setDescription(description);
        return nationalityDomain;
    }
}
