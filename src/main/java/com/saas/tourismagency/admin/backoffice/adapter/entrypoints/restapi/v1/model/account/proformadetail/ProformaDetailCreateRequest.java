package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaDetailCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("priceUnit")
    private Double priceUnit;

    @JsonProperty("importUsd")
    private Double importUsd;

    @JsonProperty("importSoles")
    private Double importSoles;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("service")
    private String service;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("proforma")
    private String proforma;

    public ProformaDetailDomain to() {
        ProformaDetailDomain domain = new ProformaDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setCant(cant);
        domain.setService(service);
        domain.setNoItem(noItem);
        domain.setDateTime(dateTime);

        return domain;
    }

    public ProformaDetailDomain to(
            ChangeTypeDomain changeTypeDomain,
            ProformaDomain proformaDomain
    ) {
        ProformaDetailDomain domain = new ProformaDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setCant(cant);
        domain.setService(service);
        domain.setNoItem(noItem);
        domain.setDateTime(dateTime);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        if (proformaDomain != null)
            domain.setProforma(proformaDomain);

        return domain;
    }
}
