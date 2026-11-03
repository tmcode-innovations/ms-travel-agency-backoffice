package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFinanceDetailUpdateRequest {

    @JsonProperty("id")
    private String id;

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

    @JsonProperty("importIgvUsd")
    private Double importIgvUsd;

    @JsonProperty("importIgvSoles")
    private Double importIgvSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("serviceFinance")
    private String serviceFinance;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("documentFinance")
    private String documentFinance;

    @JsonProperty("changeType")
    private String changeType;

    // Rubro
    @JsonProperty("entry")
    private String entry;

    @JsonProperty("affectIgv")
    private String affectIgv;


    public DocumentFinanceDetailDomain to() {
        DocumentFinanceDetailDomain domain = new DocumentFinanceDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setImportIgvUsd(importIgvUsd);
        domain.setImportIgvSoles(importIgvSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setCant(cant);
        domain.setServiceFinance(serviceFinance);
        domain.setNoItem(noItem);

        domain.setAffectIgv(affectIgv);
        return domain;
    }

    public DocumentFinanceDetailDomain to(
            DocumentFinanceDomain documentFinanceDomain,
            ChangeTypeDomain changeTypeDomain,
            EntryDomain entryDomain
    ) {
        DocumentFinanceDetailDomain domain = new DocumentFinanceDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setImportIgvUsd(importIgvUsd);
        domain.setImportIgvSoles(importIgvSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setCant(cant);
        domain.setServiceFinance(serviceFinance);
        domain.setNoItem(noItem);

        domain.setAffectIgv(affectIgv);

        if (documentFinanceDomain != null)
            domain.setDocumentFinance(documentFinanceDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        // Rubro
        if (entryDomain != null)
            domain.setEntry(entryDomain);

        return domain;
    }

}
