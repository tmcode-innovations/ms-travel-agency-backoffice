package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
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
@TypeAlias("DocumentFinanceInvoiceEntityData")
public class InvoiceEntity extends DocumentFinanceEntity implements Serializable {

    private String nliqpyear;

    private String nliqpnumber;

    private String nproformayear;

    private String nproformanumber;

    private String nproformancred;

    private String nproformancrednumber;

    private @DocumentReference(lazy = true)
    IgvEntity igv;

    private @DocumentReference(lazy = true)
    ProformaInvoiceEntity proformaInvoice;

    private @DocumentReference(lazy = true)
    ProformaCreditNoteEntity proformaCreditNote;

}
