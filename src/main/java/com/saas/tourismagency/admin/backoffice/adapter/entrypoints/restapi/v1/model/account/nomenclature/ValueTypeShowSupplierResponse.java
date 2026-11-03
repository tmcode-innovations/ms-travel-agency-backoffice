package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueTypeShowSupplierResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static ValueTypeShowSupplierResponse from(ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel domain) {
        ValueTypeShowSupplierResponse valueTypeShowSupplierResponse = new ValueTypeShowSupplierResponse();
        valueTypeShowSupplierResponse.setId(domain.id());
        valueTypeShowSupplierResponse.setName(domain.name());
        valueTypeShowSupplierResponse.setDescription(domain.description() != null ? domain.description().toUpperCase() : "" );
        return valueTypeShowSupplierResponse;
    }
}
