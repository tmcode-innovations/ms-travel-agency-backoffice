package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTourDayCrmRequest {

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("days")
    private Integer days;

}
