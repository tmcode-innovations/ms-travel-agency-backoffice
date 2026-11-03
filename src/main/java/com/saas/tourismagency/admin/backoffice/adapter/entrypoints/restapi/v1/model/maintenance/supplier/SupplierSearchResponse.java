package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierSearchResponse {

    @JsonProperty("suppliers")
    private List<SupplierResponse> suppliers;

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

}
