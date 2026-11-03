package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public ProgramTypeDomain to() {
        ProgramTypeDomain programTypeDomain = new ProgramTypeDomain();
        programTypeDomain.setName(name);
        programTypeDomain.setDescription(description);
        programTypeDomain.setIdentify(identify);
        return programTypeDomain;
    }
}
