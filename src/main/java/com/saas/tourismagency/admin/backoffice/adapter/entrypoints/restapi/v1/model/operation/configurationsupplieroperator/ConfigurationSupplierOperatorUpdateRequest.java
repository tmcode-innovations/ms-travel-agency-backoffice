package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationSupplierOperatorUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("supplierOperator")
    private String supplierOperator;

    public ConfigurationSupplierOperatorDomain to(
            SupplierOperatorDomain supplierOperator
    ) {
        ConfigurationSupplierOperatorDomain domain = new ConfigurationSupplierOperatorDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setActive(active);

        if (supplierOperator != null)
            domain.setSupplierOperator(supplierOperator);
        return domain;
    }

}
