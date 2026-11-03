package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.PartnerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.CustomerEntity;
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
@Document(collection = "account_document_finances")
public class DocumentFinanceEntity extends BaseEntity implements Serializable {

    protected String name;
    protected String description;
    protected String identify;

    protected String identifyPrefix;
    protected String identifyConsecutive;

    protected String typeInheritanceDocumentFinance;

    protected LocalDate date;
    protected LocalDate dateCancel;
    protected LocalDate dateAnnulled;
    protected String address;
    protected String referenceDocument;
    protected LocalDate dateInit;
    protected LocalDate dateEnd;
    protected Integer noPax;
    protected Double subTotalPrice;
    protected Double importSubTotalUsd;
    protected Double importSubTotalSoles;
    protected Double importIgvUsd;
    protected Double importIgvSoles;
    protected Double importTotalUsd;
    protected Double importTotalSoles;

    protected String documentAccountType;
    protected String documentAccountState;

    protected @DocumentReference(lazy = true)
    DateProcessEntity dateProcess;

    protected @DocumentReference(lazy = true)
    CountryEntity country;

    protected @DocumentReference(lazy = true)
    CurrencyEntity currency;

    protected @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    protected @DocumentReference(lazy = true)
    CustomerEntity customer;

    protected @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    protected @DocumentReference(lazy = true)
    EmployeeEntity responsible;

}
