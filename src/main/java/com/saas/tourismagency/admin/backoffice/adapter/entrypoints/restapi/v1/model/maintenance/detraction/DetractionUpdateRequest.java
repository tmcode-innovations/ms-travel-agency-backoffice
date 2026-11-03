package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetractionUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("valueDefault")
    private Boolean valueDefault;

    @JsonProperty("percent")
    private Double percent;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("observation")
    private String observation;

    public DetractionDomain to() {
        DetractionDomain detractionDomain = new DetractionDomain();
        detractionDomain.setId(id);
        detractionDomain.setName(name);
        detractionDomain.setDescription(description);
        detractionDomain.setValueDefault(valueDefault);
        detractionDomain.setPercent(percent);
        detractionDomain.setAmount(amount);
        detractionDomain.setDate(date);
        detractionDomain.setObservation(observation);
        return detractionDomain;
    }

}
