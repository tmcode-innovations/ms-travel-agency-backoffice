package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
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
@Document(collection = "account_voucher_egress")
public class VoucherEgressEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer year;
    private Integer cantRegister;
    private String noCheck;

    private String voucherState;
    private String voucherType;

    private Double importTotalSoles;
    private Double importTotalUsd;

    private Double importSoles;
    private Double importUsd;

    private Double detractionSoles;
    private Double detractionUsd;

    private LocalDate date;
    private LocalDate dateCancel;

    private Boolean voucherDetraction;

    private @DocumentReference(lazy = true)
    DateProcessEntity dateProcess;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    EmployeeEntity responsible;

    private @DocumentReference(lazy = true)
    BankEntity bank;

    private @DocumentReference(lazy = true)
    VoucherEgressEntity parent;

    private @DocumentReference(lazy = true)
    DetractionEntity detraction;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    DepartmentRegionEntity departmentRegion;

    private String valueTypeShowSupplier;
}
