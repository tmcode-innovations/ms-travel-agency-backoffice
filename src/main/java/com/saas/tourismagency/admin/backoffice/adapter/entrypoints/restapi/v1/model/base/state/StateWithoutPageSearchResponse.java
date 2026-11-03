package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateWithoutPageSearchResponse {

    @JsonProperty("entities")
    private List<StateResponse> entities;

}
