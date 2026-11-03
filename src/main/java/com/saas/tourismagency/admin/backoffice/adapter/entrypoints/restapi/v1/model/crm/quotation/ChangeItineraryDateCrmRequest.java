package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeItineraryDateCrmRequest {

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;
}
