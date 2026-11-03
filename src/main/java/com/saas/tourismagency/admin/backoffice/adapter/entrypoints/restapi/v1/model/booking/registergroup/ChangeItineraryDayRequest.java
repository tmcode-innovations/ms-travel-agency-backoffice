package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeItineraryDayRequest {

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("from")
    private String from;
}
