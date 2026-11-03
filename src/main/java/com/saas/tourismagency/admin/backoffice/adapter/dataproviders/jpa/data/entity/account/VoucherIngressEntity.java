package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
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
@Document(collection = "account_voucher_ingress")
public class VoucherIngressEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer year;
    private String identifyOther;

    private Integer cantRegister;

    private Double importTotalSoles;
    private Double importTotalUsd;

    private LocalDate date;

    private String voucherState;

    private @DocumentReference(lazy = true)
    DateProcessEntity dateProcess;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    EmployeeEntity responsible;
}
