package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCostTourCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("programDate")
    private String programDate;

    @JsonProperty("program")
    private String program;

    @JsonProperty("tour")
    private String tour;

    public ProgramCostTourDomain to(ProgramDomain program, ProgramDateDomain programDate, TourDomain tour) {
        ProgramCostTourDomain programCostTourDomain = new ProgramCostTourDomain();
        programCostTourDomain.setName(name);
        programCostTourDomain.setDescription(description);
        programCostTourDomain.setIdentify(identify);
        programCostTourDomain.setDay(day);
        if (program != null)
            programCostTourDomain.setProgram(program);
        if (programDate != null)
            programCostTourDomain.setProgramDate(programDate);
        if (tour != null)
            programCostTourDomain.setTour(tour);
        return programCostTourDomain;
    }

}
