package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDateUpdateOnlyNameRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("day")
    private String day;

    @JsonProperty("program")
    private String program;

}
