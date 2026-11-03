package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeItineraryDateRequest {

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;
}
