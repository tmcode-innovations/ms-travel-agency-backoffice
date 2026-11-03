package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateUpdateOnlyNameRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("quotation")
    private String quotation;

}
