package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CreditNoteCommissionCreateRequest extends DocumentFinanceCreateRequest {

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("identifyDocumentFinance")
    private String identifyDocumentFinance;

    @JsonProperty("documentAccountTypeAssociate")
    private String documentAccountTypeAssociate;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("documentFinanceAssociate")
    private String documentFinanceAssociate;

    public CreditNoteCommissionDomain to() {
        CreditNoteCommissionDomain domain = new CreditNoteCommissionDomain();
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

        domain.setReference(reference);
        domain.setIdentifyDocumentFinance(identifyDocumentFinance);
        domain.setDocumentAccountTypeAssociate(documentAccountTypeAssociate);

        return domain;
    }

    public CreditNoteCommissionDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain,
            SupplierDomain supplierDomain,
            DocumentFinanceDomain documentFinanceDomain
    ) {
        CreditNoteCommissionDomain domain = new CreditNoteCommissionDomain();
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

        domain.setReference(reference);
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

        if (supplierDomain != null)
            domain.setSupplier(supplierDomain);

        if (documentFinanceDomain != null)
            domain.setDocumentFinanceAssociate(documentFinanceDomain);

        return domain;
    }
}
