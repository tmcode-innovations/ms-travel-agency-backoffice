package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDateCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("program")
    private String program;

    public ProgramDateDomain to(ProgramDomain program) {
        ProgramDateDomain programDateDomain = new ProgramDateDomain();
        programDateDomain.setName(name);
        programDateDomain.setDescription(description);
        programDateDomain.setIdentify(identify);
        programDateDomain.setDay(day);
        if (program != null)
            programDateDomain.setProgram(program);
        return programDateDomain;
    }
}
