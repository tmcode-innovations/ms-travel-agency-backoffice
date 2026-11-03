package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceCreateRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InvoiceCommissionCreateRequest extends DocumentFinanceCreateRequest {

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("igv")
    private String igv;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("nproformayear")
    private String nproformayear;

    @JsonProperty("nproformanumber")
    private String nproformanumber;

    @JsonProperty("nproformancred")
    private String nproformancred;

    @JsonProperty("nproformancrednumber")
    private String nproformancrednumber;

    public InvoiceCommissionDomain to() {
        InvoiceCommissionDomain domain = new InvoiceCommissionDomain();
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

        domain.setNproformayear(nproformayear);
        domain.setNproformanumber(nproformanumber);

        domain.setNproformancred(nproformancred);
        domain.setNproformancrednumber(nproformancrednumber);

        return domain;
    }

    public InvoiceCommissionDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain,
            IgvDomain igvDomain,
            SupplierDomain supplierDomain,
            ProformaInvoiceDomain proformaInvoiceDomain,
            ProformaCreditNoteDomain proformaCreditNoteDomain
    ) {
        InvoiceCommissionDomain domain = new InvoiceCommissionDomain();
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

        domain.setNproformayear(nproformayear);
        domain.setNproformanumber(nproformanumber);

        domain.setNproformancred(nproformancred);
        domain.setNproformancrednumber(nproformancrednumber);

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

        if (igvDomain != null)
            domain.setIgv(igvDomain);

        if (supplierDomain != null)
            domain.setSupplier(supplierDomain);

        if (proformaInvoiceDomain != null)
            domain.setProformaInvoice(proformaInvoiceDomain);

        if (proformaCreditNoteDomain != null)
            domain.setProformaCreditNote(proformaCreditNoteDomain);

        return domain;
    }
}
