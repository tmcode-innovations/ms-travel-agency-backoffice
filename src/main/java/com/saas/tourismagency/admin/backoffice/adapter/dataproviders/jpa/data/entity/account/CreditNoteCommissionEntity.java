package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
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
@TypeAlias("DocumentFinanceCreditNoteCommissionEntityData")
public class CreditNoteCommissionEntity extends DocumentFinanceEntity implements Serializable {

    private String reference;

    private String identifyDocumentFinance;

    private String documentAccountTypeAssociate;

    private @DocumentReference(lazy = true)
    DocumentFinanceEntity documentFinanceAssociate;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;
}
