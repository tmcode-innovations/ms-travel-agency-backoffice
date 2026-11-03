package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourModelViewResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("parentTourDate")
    private Boolean parentTourDate;

    @JsonProperty("subRows")
    private List<TourModelViewResponse> subRows;

    public static TourModelViewResponse from(
            String id,
            String name,
            SupplierTypeResponse supplierType,
            SupplierResponse supplier,
            ServiceResponse service,
            String observation,
            Boolean parentTourDate,
            List<TourModelViewResponse> subRows) {
        TourModelViewResponse tourModelViewResponse = new TourModelViewResponse();
        tourModelViewResponse.setId(id);
        tourModelViewResponse.setName(name);
        tourModelViewResponse.setSupplierType(supplierType);
        tourModelViewResponse.setSupplier(supplier);
        tourModelViewResponse.setService(service);
        tourModelViewResponse.setObservation(observation);
        tourModelViewResponse.setParentTourDate(parentTourDate);
        tourModelViewResponse.setSubRows(subRows);
        return tourModelViewResponse;
    }
}
