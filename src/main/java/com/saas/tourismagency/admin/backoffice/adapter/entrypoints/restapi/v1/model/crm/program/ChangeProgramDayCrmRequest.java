package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProgramDayCrmRequest {

    @JsonProperty("program")
    private String program;

    @JsonProperty("days")
    private Integer days;

}
