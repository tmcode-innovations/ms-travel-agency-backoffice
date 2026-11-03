package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account_expense_guide_details")
public class ExpenseGuideDetailEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceUnit;
    private Double importUsd;
    private Double importSoles;
    private Integer cant;
    private LocalDateTime dateTimeService;

    private @DocumentReference(lazy = true)
    ExpenseGuideEntity expenseGuide;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

    private @DocumentReference(lazy = true)
    OrderServiceItemEntity orderServiceItem;

    private @DocumentReference(lazy = true)
    GuideEntity guideDrive;

}
