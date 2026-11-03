package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("priceSale")
    private Double priceSale;

    @JsonProperty("dateProcess")
    private String dateProcess;

    @JsonProperty("dateProcessDate")
    private LocalDate dateProcessDate;

    public ChangeTypeDomain to() {
        ChangeTypeDomain domain = new ChangeTypeDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setPriceSale(priceSale);
        return domain;
    }

    public ChangeTypeDomain to(DateProcessDomain dateProcessDomain) {
        ChangeTypeDomain domain = new ChangeTypeDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setPriceSale(priceSale);
        if (dateProcessDomain != null)
            domain.setDateProcess(dateProcessDomain);
        return domain;
    }
}
