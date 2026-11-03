package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseCreateRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerSupplierAdvancedUpdateRequest {

    @JsonProperty("supplier")
    private BaseCreateRequestById supplier;

    @JsonProperty("supplierType")
    private BaseCreateRequestById supplierType;

    @JsonProperty("departmentRegion")
    private BaseCreateRequestById departmentRegion;

    @JsonProperty("services")
    private Set<ContainerServiceAdvancedUpdateRequest> services;

    @JsonProperty("modeUpdateServiceAdvancedFlag")
    private String modeUpdateServiceAdvancedFlag;
}
