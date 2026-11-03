package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaCreateRequest;
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
public class ProformaInvoiceCreateRequest extends ProformaCreateRequest {

    public ProformaInvoiceDomain to() {
        ProformaInvoiceDomain domain = new ProformaInvoiceDomain();
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

    public ProformaInvoiceDomain to(
            DateProcessDomain dateProcessDomain,
            CountryDomain countryDomain,
            CurrencyDomain currencyDomain,
            ChangeTypeDomain changeTypeDomain,
            CustomerDomain customerDomain,
            RegisterGroupDomain registerGroupDomain,
            EmployeeDomain employeeDomain
    ) {
        ProformaInvoiceDomain domain = new ProformaInvoiceDomain();
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
