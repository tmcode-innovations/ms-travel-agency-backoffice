package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramTourItemCreateRequest {

    @JsonProperty("programDate")
    private String programDate;

    @JsonProperty("tour")
    private String tour;

}
