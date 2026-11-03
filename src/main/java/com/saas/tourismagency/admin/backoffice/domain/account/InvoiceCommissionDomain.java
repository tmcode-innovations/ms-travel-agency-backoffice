package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class InvoiceCommissionDomain extends DocumentFinanceDomain implements Serializable {

    private String reference;

    private IgvDomain igv;

    private SupplierDomain supplier;

    private String nproformayear;

    private String nproformanumber;

    private ProformaInvoiceDomain proformaInvoice;

    private String nproformancred;

    private String nproformancrednumber;

    private ProformaCreditNoteDomain proformaCreditNote;


}
