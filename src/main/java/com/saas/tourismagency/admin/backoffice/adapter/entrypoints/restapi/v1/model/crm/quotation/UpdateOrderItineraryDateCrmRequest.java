package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderItineraryDateCrmRequest {

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("sourceItineraryDate")
    private String sourceItineraryDate;

    @JsonProperty("targetItineraryDate")
    private String targetItineraryDate;
}
