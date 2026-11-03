package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderItineraryDateRequest {

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("sourceItineraryDate")
    private String sourceItineraryDate;

    @JsonProperty("targetItineraryDate")
    private String targetItineraryDate;
}
