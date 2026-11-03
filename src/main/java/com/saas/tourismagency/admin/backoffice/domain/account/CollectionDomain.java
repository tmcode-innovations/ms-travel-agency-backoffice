package com.saas.tourismagency.admin.backoffice.domain.account;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CollectionDomain extends DocumentFinanceDomain implements Serializable {

    // Cobranza

    private String nroInvoice;

    private String nroInvoicePrefix;

    // Boleta
    private String nroTickets;

    private String nroTicketsPrefix;

    private InvoiceDomain invoice;

    // Boleta
    private TicketDomain ticket;
}
