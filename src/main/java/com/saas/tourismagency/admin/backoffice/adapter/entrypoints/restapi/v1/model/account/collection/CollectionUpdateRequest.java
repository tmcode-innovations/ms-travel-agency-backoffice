package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection;

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
public class CollectionUpdateRequest extends DocumentFinanceUpdateRequest {

    @JsonProperty("invoice")
    protected String invoice;

    @JsonProperty("invoicePrefix")
    protected String invoicePrefix;

    @JsonProperty("ticket")
    protected String ticket;

    @JsonProperty("ticketPrefix")
    protected String ticketPrefix;

    public CollectionDomain to() {
        CollectionDomain domain = new CollectionDomain();
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

        domain.setNroInvoice(invoice);
        domain.setNroInvoicePrefix(invoicePrefix);

        domain.setNroTickets(ticket);
        domain.setNroTicketsPrefix(ticketPrefix);

        return domain;
    }

    public CollectionDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain
    ) {
        CollectionDomain domain = new CollectionDomain();
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

        domain.setNroInvoice(invoice);
        domain.setNroInvoicePrefix(invoicePrefix);

        domain.setNroTickets(ticket);
        domain.setNroTicketsPrefix(ticketPrefix);

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

    public CollectionDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain,
            InvoiceDomain invoiceDomain,
            TicketDomain ticketDomain
    ) {
        CollectionDomain domain = new CollectionDomain();
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

        domain.setNroInvoice(invoice);
        domain.setNroInvoicePrefix(invoicePrefix);

        domain.setNroTickets(ticket);
        domain.setNroTicketsPrefix(ticketPrefix);

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

        if (invoiceDomain != null)
            domain.setInvoice(invoiceDomain);

        if (ticketDomain != null)
            domain.setTicket(ticketDomain);

        return domain;
    }

}
