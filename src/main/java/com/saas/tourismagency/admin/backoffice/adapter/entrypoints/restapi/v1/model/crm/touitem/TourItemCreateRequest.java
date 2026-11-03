package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourItemCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("tourDate")
    private String tourDate;

    @JsonProperty("tourDates")
    private Set<String> tourDates;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("service")
    private String service;

    public TourItemDomain to(TourDomain tour, TourDateDomain tourDate) {
        TourItemDomain tourItemDomain = new TourItemDomain();
        tourItemDomain.setName(name);
        tourItemDomain.setDescription(description);
        tourItemDomain.setIdentify(identify);
        tourItemDomain.setObservation(observation);
        if (tourDate != null)
            tourItemDomain.setTourDate(tourDate);
        if (tour != null)
            tourItemDomain.setTour(tour);

        return tourItemDomain;
    }

    public TourItemDomain to(TourDomain tour, TourDateDomain tourDate, SupplierTypeDomain supplierType, SupplierDomain supplier, ServiceDomain service) {
        TourItemDomain tourItemDomain = new TourItemDomain();
        tourItemDomain.setName(name);
        tourItemDomain.setDescription(description);
        tourItemDomain.setIdentify(identify);
        tourItemDomain.setObservation(observation);
        if (tourDate != null)
            tourItemDomain.setTourDate(tourDate);
        if (tour != null)
            tourItemDomain.setTour(tour);
        if (supplierType != null)
            tourItemDomain.setSupplierType(supplierType);
        if (supplier != null)
            tourItemDomain.setSupplier(supplier);
        if (service != null)
            tourItemDomain.setService(service);

        return tourItemDomain;
    }

}
