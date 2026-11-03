package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherIngressCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("identifyOther")
    private String identifyOther;

    @JsonProperty("cantRegister")
    private Integer cantRegister;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("voucherState")
    private String voucherState;

    @JsonProperty("dateProcess")
    private String dateProcess;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("responsible")
    private String responsible;

    public VoucherIngressDomain to() {
        VoucherIngressDomain domain = new VoucherIngressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setYear(year);
        domain.setIdentifyOther(identifyOther);
        domain.setCantRegister(cantRegister);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setDate(date);
        domain.setVoucherState(voucherState);
        return domain;
    }

    public VoucherIngressDomain to(DateProcessDomain dateProcess, ChangeTypeDomain changeType, CurrencyDomain currency, EmployeeDomain responsible) {
        VoucherIngressDomain domain = new VoucherIngressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setYear(year);
        domain.setIdentifyOther(identifyOther);
        domain.setCantRegister(cantRegister);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setDate(date);
        domain.setVoucherState(voucherState);
        if (dateProcess != null)
            domain.setDateProcess(dateProcess);
        if (changeType != null)
            domain.setChangeType(changeType);
        if (currency != null)
            domain.setCurrency(currency);
        if (responsible != null)
            domain.setResponsible(responsible);
        return domain;
    }
}
