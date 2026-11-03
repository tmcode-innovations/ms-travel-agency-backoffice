package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapSupplierRegisterBookingGenerate {

    @JsonProperty("id")
    String id;

    @JsonProperty("supplierType")
    SupplierTypeDomain supplierType;

    @JsonProperty("supplier")
    SupplierDomain supplier;

}
