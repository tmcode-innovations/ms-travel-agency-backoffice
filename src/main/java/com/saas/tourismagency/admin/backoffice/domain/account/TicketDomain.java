package com.saas.tourismagency.admin.backoffice.domain.account;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TicketDomain extends DocumentFinanceDomain implements Serializable {

    // Boleta

    private String nliqpyear;

    private String nliqpnumber;

    private String nproformayear;

    private String nproformanumber;

    private ProformaInvoiceDomain proformaInvoice;

}
