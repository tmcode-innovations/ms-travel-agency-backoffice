package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("description")
    private String description;

    public SupplierTypeDomain to() {
        SupplierTypeDomain supplierTypeDomain = new SupplierTypeDomain();
        supplierTypeDomain.setName(name);
        supplierTypeDomain.setIdentify(identify);
        supplierTypeDomain.setDescription(description);
        return supplierTypeDomain;
    }
}
