package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItemChangeStateInvoiceRequest {

    @JsonProperty("stateItineraryItem")
    private String stateItineraryItem;

    @JsonProperty("observation")
    private String observation;
}
