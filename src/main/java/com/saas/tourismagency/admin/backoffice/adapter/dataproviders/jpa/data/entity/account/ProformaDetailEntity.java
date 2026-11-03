package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_proforma_details")
public class ProformaDetailEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;
    private Double importUsd;
    private Double importSoles;
    private Integer cant;
    private String service;
    private Integer noItem;
    private LocalDateTime dateTime;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    ProformaEntity proformaEntity;


}
