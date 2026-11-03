package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_document_finances")
@TypeAlias("DocumentFinanceCollectionEntityData")
public class CollectionEntity extends DocumentFinanceEntity implements Serializable {

    // Cobranza

    private String nroInvoice;

    private String nroInvoicePrefix;

    // Boleta
    private String nroTickets;

    private String nroTicketsPrefix;

    private @DocumentReference(lazy = true)
    InvoiceEntity invoice;

    // Boleta
    private @DocumentReference(lazy = true)
    TicketEntity ticket;
}
