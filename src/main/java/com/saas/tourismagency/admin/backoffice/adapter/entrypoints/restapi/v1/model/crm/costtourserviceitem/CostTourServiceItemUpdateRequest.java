package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourServiceItemUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("costTour")
    private String costTour;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("service")
    private String service;


    public CostTourServiceItemDomain to(CostTourDomain costTour, QuotationDomain quotation, ItineraryDateDomain itineraryDate, TourDomain tour, SupplierTypeDomain supplierType, SupplierDomain supplier, ServiceDomain service) {
        CostTourServiceItemDomain costTourServiceItemDomain = new CostTourServiceItemDomain();
        costTourServiceItemDomain.setId(id);
        costTourServiceItemDomain.setName(name);
        costTourServiceItemDomain.setDescription(description);
        costTourServiceItemDomain.setIdentify(identify);
        costTourServiceItemDomain.setObservation(observation);
        if (costTour != null)
            costTourServiceItemDomain.setCostTour(costTour);
        if (quotation != null)
            costTourServiceItemDomain.setQuotation(quotation);
        if (itineraryDate != null)
            costTourServiceItemDomain.setItineraryDate(itineraryDate);
        if (tour != null)
            costTourServiceItemDomain.setTour(tour);
        if (supplierType != null)
            costTourServiceItemDomain.setSupplierType(supplierType);
        if (supplier != null)
            costTourServiceItemDomain.setSupplier(supplier);
        if (service != null)
            costTourServiceItemDomain.setService(service);
        return costTourServiceItemDomain;
    }

}
