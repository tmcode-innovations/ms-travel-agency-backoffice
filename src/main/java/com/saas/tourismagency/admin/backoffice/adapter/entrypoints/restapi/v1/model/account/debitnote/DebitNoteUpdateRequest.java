package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DebitNoteUpdateRequest extends DocumentFinanceUpdateRequest {

    @JsonProperty("identifyDocumentFinance")
    private String identifyDocumentFinance;

    @JsonProperty("documentAccountTypeAssociate")
    private String documentAccountTypeAssociate;

    @JsonProperty("documentFinanceAssociate")
    private String documentFinanceAssociate;

    public DebitNoteDomain to() {
        DebitNoteDomain domain = new DebitNoteDomain();
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

        domain.setIdentifyDocumentFinance(identifyDocumentFinance);
        domain.setDocumentAccountTypeAssociate(documentAccountTypeAssociate);

        return domain;
    }

    public DebitNoteDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain,
            DocumentFinanceDomain documentFinanceDomain
    ) {
        DebitNoteDomain domain = new DebitNoteDomain();
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

        domain.setIdentifyDocumentFinance(identifyDocumentFinance);
        domain.setDocumentAccountTypeAssociate(documentAccountTypeAssociate);

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

        if (documentFinanceDomain != null)
            domain.setDocumentFinanceAssociate(documentFinanceDomain);

        return domain;
    }

}
