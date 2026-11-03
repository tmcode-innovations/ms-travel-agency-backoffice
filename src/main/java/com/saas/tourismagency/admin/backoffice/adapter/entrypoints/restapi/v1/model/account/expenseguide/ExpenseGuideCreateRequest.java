package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGuideCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateExpenseGuide")
    private LocalDate dateExpenseGuide;

    @JsonProperty("nroPax")
    private Integer nroPax;

    @JsonProperty("lodging")
    private String lodging;

    @JsonProperty("importSubTotalUsd")
    private Double importSubTotalUsd;

    @JsonProperty("importSubTotalSoles")
    private Double importSubTotalSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("subTotalPrice")
    private Double subTotalPrice;

    @JsonProperty("expenseGuideState")
    private String expenseGuideState;

    @JsonProperty("guideTransfer")
    private String guideTransfer;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("supplierLodging")
    private String supplierLodging;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("dateProcess")
    private String dateProcess;

    public ExpenseGuideDomain to() {
        ExpenseGuideDomain domain = new ExpenseGuideDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setDateExpenseGuide(dateExpenseGuide);
        domain.setNroPax(nroPax);
        domain.setLodging(lodging);
        domain.setImportSubTotalUsd(importSubTotalUsd);
        domain.setImportSubTotalSoles(importSubTotalSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setSubTotalPrice(subTotalPrice);
        domain.setExpenseGuideState(expenseGuideState);
        return domain;
    }

    public ExpenseGuideDomain to(
            GuideDomain guideTransferDomain,
            RegisterGroupDomain registerGroupDomain,
            SupplierLodgingDomain supplierLodgingDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            DateProcessDomain dateProcessDomain
    ) {
        ExpenseGuideDomain domain = new ExpenseGuideDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setDateExpenseGuide(dateExpenseGuide);
        domain.setNroPax(nroPax);
        domain.setLodging(lodging);
        domain.setImportSubTotalUsd(importSubTotalUsd);
        domain.setImportSubTotalSoles(importSubTotalSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setSubTotalPrice(subTotalPrice);
        domain.setExpenseGuideState(expenseGuideState);

        if (guideTransferDomain != null)
            domain.setGuideTransfer(guideTransferDomain);

        if (registerGroupDomain != null)
            domain.setRegisterGroup(registerGroupDomain);

        if (supplierLodgingDomain != null)
            domain.setSupplierLodging(supplierLodgingDomain);

        if (currencyDomain != null)
            domain.setCurrency(currencyDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        if (dateProcessDomain != null)
            domain.setDateProcess(dateProcessDomain);

        return domain;
    }
}
