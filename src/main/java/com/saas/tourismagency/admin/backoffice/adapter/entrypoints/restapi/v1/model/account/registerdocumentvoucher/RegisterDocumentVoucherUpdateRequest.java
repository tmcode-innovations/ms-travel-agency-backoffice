package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.RegisterDocumentVoucherDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDocumentVoucherUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    public RegisterDocumentVoucherDomain to() {
        RegisterDocumentVoucherDomain domain = new RegisterDocumentVoucherDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        return domain;
    }

}
