package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGuideDetailUpdateRequest {

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

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("dateTimeService")
    private LocalDateTime dateTimeService;

    @JsonProperty("expenseGuide")
    private String expenseGuide;

    @JsonProperty("service")
    private String service;

    @JsonProperty("orderServiceItem")
    private String orderServiceItem;

    @JsonProperty("guideDrive")
    private String guideDrive;

    public ExpenseGuideDetailDomain to() {
        ExpenseGuideDetailDomain domain = new ExpenseGuideDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setCant(cant);
        domain.setDateTimeService(dateTimeService);
        return domain;
    }

    public ExpenseGuideDetailDomain to(
            ExpenseGuideDomain expenseGuideDomain,
            ServiceDomain serviceDomain,
            OrderServiceItemDomain orderServiceItemDomain,
            GuideDomain guideDriveDomain
    ) {
        ExpenseGuideDetailDomain domain = new ExpenseGuideDetailDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setPriceUnit(priceUnit);
        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setCant(cant);
        domain.setDateTimeService(dateTimeService);

        if (expenseGuideDomain != null)
            domain.setExpenseGuide(expenseGuideDomain);

        if (serviceDomain != null)
            domain.setService(serviceDomain);

        if (orderServiceItemDomain != null)
            domain.setOrderServiceItem(orderServiceItemDomain);

        if (guideDriveDomain != null)
            domain.setGuideDrive(guideDriveDomain);

        return domain;
    }
}
