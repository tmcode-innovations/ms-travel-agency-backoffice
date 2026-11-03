package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDocumentVoucherResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public static RegisterDocumentVoucherResponse from(RegisterDocumentVoucherDomain domain) {
        RegisterDocumentVoucherResponse response = new RegisterDocumentVoucherResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        return response;
    }
}
