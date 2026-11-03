package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.*;
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
@Document(collection = "account_voucher_egress_details")
public class VoucherEgressDetailEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private Integer year;

    private String valueTypeShowSupplier;
    private String numberDoc;
    private String observation;

    private LocalDate date;

    private Double montSoles;
    private Double montUsd;

    private Double detractionSoles;
    private Double detractionUsd;

    private Double balanceSoles;
    private Double balanceUsd;

    private @DocumentReference(lazy = true)
    VoucherEgressEntity voucherEgress;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    GuideEntity guideDrive;

    private @DocumentReference(lazy = true)
    GuideEntity guideTransfer;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    TouristDestineEntity touristDestine;

    private @DocumentReference(lazy = true)
    DepartmentRegionEntity departmentRegion;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private @DocumentReference(lazy = true)
    DocumentAccountTypeEntity documentAccountType;

    private @DocumentReference(lazy = true)
    DetractionEntity detraction;

    // Using by GURU
    private @DocumentReference(lazy = true)
    ItineraryItemEntity itineraryItem;

    private Integer paxTotal;
}
