package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierTypeUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("description")
    private String description;

    public SupplierTypeDomain to() {
        SupplierTypeDomain supplierTypeDomain = new SupplierTypeDomain();
        supplierTypeDomain.setId(id);
        supplierTypeDomain.setName(name);
        supplierTypeDomain.setDescription(description);
        return supplierTypeDomain;
    }

}
