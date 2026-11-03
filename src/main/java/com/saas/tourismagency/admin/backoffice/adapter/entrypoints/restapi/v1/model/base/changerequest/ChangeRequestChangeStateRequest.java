package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestChangeStateRequest {

    @JsonProperty("stateChangeRequest")
    private String stateChangeRequest;

    @JsonProperty("created")
    private String created;

    @JsonProperty("freeQuotation")
    private Boolean freeQuotation;

    @JsonProperty("employees")
    private Set<String> employees;

    @JsonProperty("observation")
    private String observation;
}
