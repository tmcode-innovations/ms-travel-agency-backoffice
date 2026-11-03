package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapItineraryEditAdvancedResponse {

    @JsonProperty("suppliers")
    private List<WrapItineraryEditAdvancedSupplierResponse> suppliers;

    public static WrapItineraryEditAdvancedResponse from(List<WrapItineraryEditAdvancedSupplierResponse> suppliers) {
        WrapItineraryEditAdvancedResponse response = new WrapItineraryEditAdvancedResponse();
        response.setSuppliers(suppliers);
        return response;
    }

}
