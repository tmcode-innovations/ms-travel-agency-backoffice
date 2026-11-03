package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("cantRegister")
    private Integer cantRegister;

    @JsonProperty("noCheck")
    private String noCheck;

    @JsonProperty("voucherState")
    private String voucherState;

    @JsonProperty("voucherType")
    private String voucherType;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importSoles")
    private Double importSoles;

    @JsonProperty("importUsd")
    private Double importUsd;

    @JsonProperty("detractionSoles")
    private Double detractionSoles;

    @JsonProperty("detractionUsd")
    private Double detractionUsd;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateCancel")
    private LocalDate dateCancel;

    @JsonProperty("voucherDetraction")
    private Boolean voucherDetraction;

    @JsonProperty("dateProcess")
    private String dateProcess;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("responsible")
    private String responsible;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("parent")
    private String parent;

    @JsonProperty("detraction")
    private String detraction;

    @JsonProperty("generateDetraction")
    private Boolean generateDetraction;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    @JsonProperty("valueTypeShowSupplier")
    private String valueTypeShowSupplier;

    public VoucherEgressDomain to() {
        VoucherEgressDomain domain = new VoucherEgressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setYear(year);
        domain.setCantRegister(cantRegister);
        domain.setNoCheck(noCheck);
        domain.setVoucherState(voucherState);
        domain.setVoucherType(voucherType);

        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setDate(date);
        domain.setDateCancel(dateCancel);
        domain.setVoucherDetraction(voucherDetraction);

        domain.setValueTypeShowSupplier(valueTypeShowSupplier);

        return domain;
    }

    public VoucherEgressDomain to(DateProcessDomain dateProcess, ChangeTypeDomain changeType, CurrencyDomain currency, EmployeeDomain responsible, BankDomain bank, VoucherEgressDomain parent, DetractionDomain detraction) {
        VoucherEgressDomain domain = new VoucherEgressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setYear(year);
        domain.setCantRegister(cantRegister);
        domain.setNoCheck(noCheck);
        domain.setVoucherState(voucherState);
        domain.setVoucherType(voucherType);

        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setDate(date);
        domain.setDateCancel(dateCancel);
        domain.setVoucherDetraction(voucherDetraction);

        if (dateProcess != null)
            domain.setDateProcess(dateProcess);
        if (changeType != null)
            domain.setChangeType(changeType);
        if (currency != null)
            domain.setCurrency(currency);
        if (responsible != null)
            domain.setResponsible(responsible);
        if (bank != null)
            domain.setBank(bank);
        if (parent != null)
            domain.setParent(parent);
        if (detraction != null)
            domain.setDetraction(detraction);

        domain.setValueTypeShowSupplier(valueTypeShowSupplier);

        return domain;
    }

    public VoucherEgressDomain to(DateProcessDomain dateProcess, ChangeTypeDomain changeType, CurrencyDomain currency, EmployeeDomain responsible, BankDomain bank, VoucherEgressDomain parent, DetractionDomain detraction, RegisterGroupDomain registerGroup, SupplierDomain supplier, SupplierTypeDomain supplierType, DepartmentRegionDomain departmentRegion) {
        VoucherEgressDomain domain = new VoucherEgressDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setYear(year);
        domain.setCantRegister(cantRegister);
        domain.setNoCheck(noCheck);
        domain.setVoucherState(voucherState);
        domain.setVoucherType(voucherType);

        domain.setImportUsd(importUsd);
        domain.setImportSoles(importSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setDetractionUsd(detractionUsd);
        domain.setDetractionSoles(detractionSoles);
        domain.setDate(date);
        domain.setDateCancel(dateCancel);
        domain.setVoucherDetraction(voucherDetraction);

        if (dateProcess != null)
            domain.setDateProcess(dateProcess);
        if (changeType != null)
            domain.setChangeType(changeType);
        if (currency != null)
            domain.setCurrency(currency);
        if (responsible != null)
            domain.setResponsible(responsible);
        if (bank != null)
            domain.setBank(bank);
        if (parent != null)
            domain.setParent(parent);
        if (detraction != null)
            domain.setDetraction(detraction);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (supplier != null)
            domain.setSupplier(supplier);
        if (supplierType != null)
            domain.setSupplierType(supplierType);
        if (departmentRegion != null)
            domain.setDepartmentRegion(departmentRegion);

        domain.setValueTypeShowSupplier(valueTypeShowSupplier);

        return domain;
    }
}
