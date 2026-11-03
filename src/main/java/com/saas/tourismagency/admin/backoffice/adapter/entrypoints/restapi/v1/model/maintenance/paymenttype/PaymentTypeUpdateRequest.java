package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public PaymentTypeDomain to() {
        PaymentTypeDomain paymentTypeDomain = new PaymentTypeDomain();
        paymentTypeDomain.setId(id);
        paymentTypeDomain.setName(name);
        paymentTypeDomain.setDescription(description);
        return paymentTypeDomain;
    }

}
