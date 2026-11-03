package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public static PaymentTypeResponse from(PaymentTypeEnum.PaymentTypeModel domain) {
        PaymentTypeResponse paymentTypeResponse = new PaymentTypeResponse();
        paymentTypeResponse.setId(domain.id());
        paymentTypeResponse.setName(domain.name());
        return paymentTypeResponse;
    }
}
