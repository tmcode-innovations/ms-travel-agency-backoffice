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
@TypeAlias("DocumentFinanceTicketEntityData")
public class TicketEntity extends DocumentFinanceEntity implements Serializable {

    private String nliqpyear;

    private String nliqpnumber;

    private String nproformayear;

    private String nproformanumber;

    private @DocumentReference(lazy = true)
    ProformaInvoiceEntity proformaInvoice;

}
