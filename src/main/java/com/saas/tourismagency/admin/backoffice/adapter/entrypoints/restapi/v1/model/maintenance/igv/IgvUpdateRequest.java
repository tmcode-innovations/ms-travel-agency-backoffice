package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IgvUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("percent")
    private Double percent;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("observation")
    private String observation;

    public IgvDomain to() {
        IgvDomain igvDomain = new IgvDomain();
        igvDomain.setId(id);
        igvDomain.setName(name);
        igvDomain.setDescription(description);
        igvDomain.setPercent(percent);
        igvDomain.setDate(date);
        igvDomain.setObservation(observation);
        return igvDomain;
    }

}
