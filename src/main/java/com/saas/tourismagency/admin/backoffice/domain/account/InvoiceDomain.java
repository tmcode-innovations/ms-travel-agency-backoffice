package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class InvoiceDomain extends DocumentFinanceDomain implements Serializable {

    private String nliqpyear;

    private String nliqpnumber;

    private String nproformayear;

    private String nproformanumber;

    private String nproformancred;

    private String nproformancrednumber;

    private IgvDomain igv;

    private ProformaInvoiceDomain proformaInvoice;

    private ProformaCreditNoteDomain proformaCreditNote;

}
