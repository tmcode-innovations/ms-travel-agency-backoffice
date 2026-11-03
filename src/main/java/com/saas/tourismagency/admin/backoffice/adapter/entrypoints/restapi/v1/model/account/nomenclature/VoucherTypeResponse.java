package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherTypeResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static VoucherTypeResponse from(VoucherTypeEnum.VoucherTypeModel domain) {
        VoucherTypeResponse voucherTypeResponse = new VoucherTypeResponse();
        voucherTypeResponse.setId(domain.id());
        voucherTypeResponse.setName(domain.name());
        voucherTypeResponse.setDescription(domain.description());
        return voucherTypeResponse;
    }
}
