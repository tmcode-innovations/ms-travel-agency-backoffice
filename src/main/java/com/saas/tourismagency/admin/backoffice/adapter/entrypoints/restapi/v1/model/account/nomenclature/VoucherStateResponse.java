package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherStateResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static VoucherStateResponse from(VoucherStateEnum.VoucherStateModel domain) {
        VoucherStateResponse voucherStateResponse = new VoucherStateResponse();
        voucherStateResponse.setId(domain.id());
        voucherStateResponse.setName(domain.name());
        voucherStateResponse.setDescription(domain.description());
        return voucherStateResponse;
    }

    public static VoucherStateResponse fromConfiguration(VoucherStateEnum.VoucherStateModel domain) {
        VoucherStateResponse voucherStateResponse = new VoucherStateResponse();
        voucherStateResponse.setId(domain.id());
        voucherStateResponse.setName(domain.name());
        if (domain.name().equals(VoucherStateEnum.CANCELLED.getName())){
            voucherStateResponse.setDescription("Pagado");
        }else{
            voucherStateResponse.setDescription(domain.description());
        }
        return voucherStateResponse;
    }
}
