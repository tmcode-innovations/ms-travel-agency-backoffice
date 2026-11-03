package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
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
public class DocumentFinanceUpdateRequest {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("identify")
    protected String identify;

    @JsonProperty("identifyPrefix")
    protected String identifyPrefix;

    @JsonProperty("identifyConsecutive")
    protected String identifyConsecutive;

    @JsonProperty("date")
    protected LocalDate date;

    @JsonProperty("dateCancel")
    protected LocalDate dateCancel;

    @JsonProperty("dateAnnulled")
    protected LocalDate dateAnnulled;

    @JsonProperty("address")
    protected String address;

    @JsonProperty("referenceDocument")
    protected String referenceDocument;

    @JsonProperty("dateInit")
    protected LocalDate dateInit;

    @JsonProperty("dateEnd")
    protected LocalDate dateEnd;

    @JsonProperty("noPax")
    protected Integer noPax;

    @JsonProperty("subTotalPrice")
    protected Double subTotalPrice;

    @JsonProperty("importSubTotalUsd")
    protected Double importSubTotalUsd;

    @JsonProperty("importSubTotalSoles")
    protected Double importSubTotalSoles;

    @JsonProperty("importIgvUsd")
    protected Double importIgvUsd;

    @JsonProperty("importIgvSoles")
    protected Double importIgvSoles;

    @JsonProperty("importTotalUsd")
    protected Double importTotalUsd;

    @JsonProperty("importTotalSoles")
    protected Double importTotalSoles;

    @JsonProperty("documentAccountType")
    protected String documentAccountType;

    @JsonProperty("documentAccountState")
    protected String documentAccountState;

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

    @JsonProperty("responsible")
    protected String responsible;

    public DocumentFinanceDomain to() {
        DocumentFinanceDomain domain = new DocumentFinanceDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identifyPrefix + identifyConsecutive);

        domain.setIdentifyPrefix(identifyPrefix);
        domain.setIdentifyConsecutive(identifyConsecutive);
 
        domain.setDate(date);
        domain.setDateCancel(dateCancel);
        domain.setDateAnnulled(dateAnnulled);
        domain.setAddress(address);
        domain.setReferenceDocument(referenceDocument);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);
        domain.setSubTotalPrice(subTotalPrice);
        domain.setImportSubTotalUsd(importSubTotalUsd);
        domain.setImportSubTotalSoles(importSubTotalSoles);
        domain.setImportIgvUsd(importIgvUsd);
        domain.setImportIgvSoles(importIgvSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);

        domain.setDocumentAccountType(documentAccountType);
        domain.setDocumentAccountState(documentAccountState);

        return domain;
    }

    public DocumentFinanceDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain
    ) {
        DocumentFinanceDomain domain = new DocumentFinanceDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identifyPrefix + identifyConsecutive);

        domain.setIdentifyPrefix(identifyPrefix);
        domain.setIdentifyConsecutive(identifyConsecutive);

        domain.setDate(date);
        domain.setDateCancel(dateCancel);
        domain.setDateAnnulled(dateAnnulled);
        domain.setAddress(address);
        domain.setReferenceDocument(referenceDocument);
        domain.setDateInit(dateInit);
        domain.setDateEnd(dateEnd);
        domain.setNoPax(noPax);
        domain.setSubTotalPrice(subTotalPrice);
        domain.setImportSubTotalUsd(importSubTotalUsd);
        domain.setImportSubTotalSoles(importSubTotalSoles);
        domain.setImportIgvUsd(importIgvUsd);
        domain.setImportIgvSoles(importIgvSoles);
        domain.setImportTotalUsd(importTotalUsd);
        domain.setImportTotalSoles(importTotalSoles);

        domain.setDocumentAccountType(documentAccountType);
        domain.setDocumentAccountState(documentAccountState);

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
            domain.setResponsible(employeeDomain);

        return domain;
    }

}
