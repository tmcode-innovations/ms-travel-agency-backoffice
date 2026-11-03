package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.SupplierTypeOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierTypeOperatorResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static SupplierTypeOperatorResponse from(SupplierTypeOperatorEnum.SupplierTypeOperatorModel domain) {
        SupplierTypeOperatorResponse supplierTypeOperatorResponse = new SupplierTypeOperatorResponse();
        supplierTypeOperatorResponse.setId(domain.id());
        supplierTypeOperatorResponse.setName(domain.name());
        supplierTypeOperatorResponse.setDescription(domain.description());
        return supplierTypeOperatorResponse;
    }
}
