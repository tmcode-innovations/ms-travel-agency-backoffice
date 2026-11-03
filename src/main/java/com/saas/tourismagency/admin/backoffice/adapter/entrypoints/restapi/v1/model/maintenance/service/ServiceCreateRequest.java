package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("abbrev")
    private String abbrev;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("noApplyRate")
    private Boolean noApplyRate;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("withoutCost")
    private Boolean withoutCost;

    @JsonProperty("typeTour")
    private Boolean typeTour;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("touristDestine")
    private String touristDestine;

    @JsonProperty("serviceCategory")
    private String serviceCategory;

    public ServiceDomain to() {
        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setName(name);
        serviceDomain.setDescription(description);
        serviceDomain.setIdentify(identify);
        serviceDomain.setAbbrev(abbrev);
        serviceDomain.setObservation(observation);
        serviceDomain.setNoApplyRate(noApplyRate);
        serviceDomain.setDay(day);
        serviceDomain.setWithoutCost(withoutCost);
        serviceDomain.setTypeTour(typeTour);
        serviceDomain.setCost(cost);
        serviceDomain.setServiceType(serviceType);
        return serviceDomain;
    }

    public ServiceDomain to(TouristDestineDomain touristDestine, ServiceCategoryDomain serviceCategory) {
        ServiceDomain serviceDomain = new ServiceDomain();
        serviceDomain.setName(name);
        serviceDomain.setDescription(description);
        serviceDomain.setIdentify(identify);
        serviceDomain.setAbbrev(abbrev);
        serviceDomain.setObservation(observation);
        serviceDomain.setNoApplyRate(noApplyRate);
        serviceDomain.setDay(day);
        serviceDomain.setWithoutCost(withoutCost);
        serviceDomain.setTypeTour(typeTour);
        serviceDomain.setCost(cost);
        serviceDomain.setServiceType(serviceType);
        if (touristDestine != null)
            serviceDomain.setTouristDestine(touristDestine);
        if (serviceCategory != null)
            serviceDomain.setServiceCategory(serviceCategory);
        return serviceDomain;
    }
}
