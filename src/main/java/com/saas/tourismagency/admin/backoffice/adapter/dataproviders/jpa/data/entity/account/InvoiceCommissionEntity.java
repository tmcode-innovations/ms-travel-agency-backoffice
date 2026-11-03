package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
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
@TypeAlias("DocumentFinanceInvoiceCommissionEntityData")
public class InvoiceCommissionEntity extends DocumentFinanceEntity implements Serializable {

    private String reference;

    private @DocumentReference(lazy = true)
    IgvEntity igv;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private String nproformayear;

    private String nproformanumber;

    private @DocumentReference(lazy = true)
    ProformaInvoiceEntity proformaInvoice;

    private String nproformancred;

    private String nproformancrednumber;

    private @DocumentReference(lazy = true)
    ProformaCreditNoteEntity proformaCreditNote;


}
