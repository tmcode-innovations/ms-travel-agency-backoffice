package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
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
@Document(collection = "account_expense_guides")
public class ExpenseGuideEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateExpenseGuide;
    private Integer nroPax;
    private String lodging;
    private Double importSubTotalUsd;
    private Double importSubTotalSoles;
    private Double importTotalUsd;
    private Double importTotalSoles;
    private Double subTotalPrice;

    private @DocumentReference(lazy = true)
    GuideEntity guideTransfer;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    SupplierLodgingEntity supplierLodging;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private String expenseGuideState;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    DateProcessEntity dateProcess;

}
