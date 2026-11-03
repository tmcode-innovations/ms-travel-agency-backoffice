package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_account_payments")
public class AccountPaymentEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private LocalDate paymentDate;
    private String nroDocument;
    private String observation;
    private Double importMontSoles;
    private Double importMontUsd;

    private String paymentType;

    private @DocumentReference(lazy = true)
    ProformaEntity proforma;

    private @DocumentReference(lazy = true)
    BankEntity bank;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;
}
