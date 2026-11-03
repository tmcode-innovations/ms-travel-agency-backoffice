package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("programType")
    private String programType;

    @JsonProperty("stateProgram")
    private String stateProgram;

    public ProgramDomain to() {
        ProgramDomain programDomain = new ProgramDomain();
        programDomain.setName(name);
        programDomain.setDescription(description);
        programDomain.setIdentify(identify);
        programDomain.setDay(day);
        programDomain.setStateProgram(stateProgram);
        return programDomain;
    }

    public ProgramDomain to(ProgramTypeDomain programType) {
        ProgramDomain programDomain = new ProgramDomain();
        programDomain.setName(name);
        programDomain.setDescription(description);
        programDomain.setIdentify(identify);
        programDomain.setDay(day);
        programDomain.setStateProgram(stateProgram);
        if (programType != null)
            programDomain.setProgramType(programType);
        return programDomain;
    }
}
