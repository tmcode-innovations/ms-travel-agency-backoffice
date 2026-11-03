package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderServiceCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("requestOrderServiceState")
    private String requestOrderServiceState;

    @JsonProperty("registerBookingOperator")
    private String registerBookingOperator;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("supplierOperator")
    private String supplierOperator;

    @JsonProperty("activeRequestOrderService")
    private Boolean activeRequestOrderService;

    public RequestOrderServiceDomain to(
            RegisterBookingOperatorDomain registerBookingOperator,
            RegisterGroupDomain registerGroup,
            SupplierOperatorDomain supplierOperator
    ) {
        RequestOrderServiceDomain domain = new RequestOrderServiceDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setRequestOrderServiceState(requestOrderServiceState);
        domain.setActiveRequestOrderService(activeRequestOrderService);

        if (registerBookingOperator != null)
            domain.setRegisterBookingOperator(registerBookingOperator);

        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);

        if (supplierOperator != null)
            domain.setSupplierOperator(supplierOperator);

        return domain;
    }
}
