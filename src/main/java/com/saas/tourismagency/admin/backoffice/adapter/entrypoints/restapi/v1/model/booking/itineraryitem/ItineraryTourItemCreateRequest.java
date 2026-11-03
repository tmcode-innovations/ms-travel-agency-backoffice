package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryTourItemCreateRequest {

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("numMaxPax")
    private Integer numMaxPax;
}
