package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderItineraryDateToItemCrmRequest {

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("sourceItineraryDate")
    private String sourceItineraryDate;

    @JsonProperty("targetItineraryItem")
    private String targetItineraryItem;
}
