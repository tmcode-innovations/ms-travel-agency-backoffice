package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPaymentCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @JsonProperty("nroDocument")
    private String nroDocument;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("importMontSoles")
    private Double importMontSoles;

    @JsonProperty("importMontUsd")
    private Double importMontUsd;

    @JsonProperty("paymentType")
    private String paymentType;

    @JsonProperty("proforma")
    private String proforma;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("changeType")
    private String changeType;

    public AccountPaymentDomain to() {
        AccountPaymentDomain domain = new AccountPaymentDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setNoItem(noItem);
        domain.setPaymentDate(paymentDate);
        domain.setNroDocument(nroDocument);
        domain.setObservation(observation);
        domain.setImportMontSoles(importMontSoles);
        domain.setImportMontUsd(importMontUsd);
        domain.setPaymentType(paymentType);
        return domain;
    }

    public AccountPaymentDomain to(
            ProformaDomain proformaDomain,
            BankDomain bankDomain,
            ChangeTypeDomain changeTypeDomain
    ) {
        AccountPaymentDomain domain = new AccountPaymentDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setNoItem(noItem);
        domain.setPaymentDate(paymentDate);
        domain.setNroDocument(nroDocument);
        domain.setObservation(observation);
        domain.setImportMontSoles(importMontSoles);
        domain.setImportMontUsd(importMontUsd);
        domain.setPaymentType(paymentType);

        if (proformaDomain != null)
            domain.setProforma(proformaDomain);

        if (bankDomain != null)
            domain.setBank(bankDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        return domain;
    }
}
