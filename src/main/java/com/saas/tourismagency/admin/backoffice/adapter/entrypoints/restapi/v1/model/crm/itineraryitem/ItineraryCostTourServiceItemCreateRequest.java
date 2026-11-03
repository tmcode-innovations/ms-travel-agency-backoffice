package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryCostTourServiceItemCreateRequest {

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("costTour")
    private String costTour;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("service")
    private String service;
}
