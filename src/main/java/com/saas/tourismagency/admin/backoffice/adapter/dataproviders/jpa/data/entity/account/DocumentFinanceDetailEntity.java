package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.EntryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_document_finance_details")
public class DocumentFinanceDetailEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;

    private Double importUsd;

    private Double importSoles;

    private Double importIgvUsd;

    private Double importIgvSoles;

    private Double importTotalUsd;

    private Double importTotalSoles;

    private Integer cant;

    private String serviceFinance;

    private Integer noItem;

    private @DocumentReference(lazy = true)
    DocumentFinanceEntity documentFinance;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    // Rubro
    private @DocumentReference(lazy = true)
    EntryEntity entry;

    private String affectIgv;
}
