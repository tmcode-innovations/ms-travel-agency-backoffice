package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaUpdateRequest {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("typeInheritanceProforma")
    protected String typeInheritanceProforma;

    @JsonProperty("title")
    protected String title;

    @JsonProperty("date")
    protected LocalDate date;

    @JsonProperty("address")
    protected String address;

    @JsonProperty("reference")
    protected String reference;

    @JsonProperty("dateInit")
    protected LocalDate dateInit;

    @JsonProperty("dateEnd")
    protected LocalDate dateEnd;

    @JsonProperty("noPax")
    protected Integer noPax;

    @JsonProperty("importTotalUsd")
    protected Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    protected Double importTotalSoles;

    @JsonProperty("importAmortizationUsd")
    protected Double importAmortizationUsd;

    @JsonProperty("importAmortizationSoles")
    protected Double importAmortizationSoles;

    @JsonProperty("importSaldxAmortizarUsd")
    protected Double importSaldxAmortizarUsd;

    @JsonProperty("importSaldxAmortizarSoles")
    protected Double importSaldxAmortizarSoles;

    @JsonProperty("priceUnit")
    protected Double priceUnit;

    @JsonProperty("cantRegister")
    protected Integer cantRegister;

    @JsonProperty("dateCancel")
    protected LocalDate dateCancel;

    @JsonProperty("proformaType")
    protected String proformaType;

    @JsonProperty("proformaState")
    protected String proformaState;

    @JsonProperty("dateProcess")
    protected String dateProcess;

    @JsonProperty("country")
    protected String country;

    @JsonProperty("currency")
    protected String currency;

    @JsonProperty("changeType")
    protected String changeType;

    @JsonProperty("customer")
    protected String customer;

    @JsonProperty("registerGroup")
    protected String registerGroup;

    @JsonProperty("employee")
    protected String employee;

    public ProformaDomain to() {
        ProformaDomain domain = new ProformaDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setTypeInheritanceProforma(typeInheritanceProforma);

        domain.setTitle(title);
        domain.setDate(date);
        domain.setAddress(address);
        domain.setReference(reference);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setImportAmortizationUsd(importAmortizationUsd);
        domain.setImportAmortizationSoles(importAmortizationSoles);
        domain.setImportSaldxAmortizarUsd(importSaldxAmortizarUsd);
        domain.setImportSaldxAmortizarSoles(importSaldxAmortizarSoles);
        domain.setPriceUnit(priceUnit);
        domain.setCantRegister(cantRegister);
        domain.setDateCancel(dateCancel);
        domain.setProformaType(proformaType);
        domain.setProformaState(proformaState);

        return domain;
    }

    public ProformaDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain
    ) {
        ProformaDomain domain = new ProformaDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setTypeInheritanceProforma(typeInheritanceProforma);

        domain.setTitle(title);
        domain.setDate(date);
        domain.setAddress(address);
        domain.setReference(reference);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);
        domain.setImportAmortizationUsd(importAmortizationUsd);
        domain.setImportAmortizationSoles(importAmortizationSoles);
        domain.setImportSaldxAmortizarUsd(importSaldxAmortizarUsd);
        domain.setImportSaldxAmortizarSoles(importSaldxAmortizarSoles);
        domain.setPriceUnit(priceUnit);
        domain.setCantRegister(cantRegister);
        domain.setDateCancel(dateCancel);
        domain.setProformaType(proformaType);
        domain.setProformaState(proformaState);

        if (dateProcessDomain != null)
            domain.setDateProcess(dateProcessDomain);

        if (countryDomain != null)
            domain.setCountry(countryDomain);

        if (currencyDomain != null)
            domain.setCurrency(currencyDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        if (customerDomain != null)
            domain.setCustomer(customerDomain);

        if (registerGroupDomain != null)
            domain.setRegisterGroup(registerGroupDomain);

        if (employeeDomain != null)
            domain.setEmployee(employeeDomain);

        return domain;
    }
}
