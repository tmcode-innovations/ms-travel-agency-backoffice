package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerBookingSearchResponse {

    @JsonProperty("items")
    private List<PassengerBookingResponse> items;

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

}
