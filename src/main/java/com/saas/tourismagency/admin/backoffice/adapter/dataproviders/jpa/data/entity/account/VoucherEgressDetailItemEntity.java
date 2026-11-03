package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.IgvEntity;
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
@Document(collection = "account_voucher_egress_detail_items")
public class VoucherEgressDetailItemEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private String observation;

    private Double netImportUsd;
    private Double netImportSoles;
    private Double totalImportUsd;
    private Double totalImportSoles;

    private Double netGeneralUsd;
    private Double igvSfGeneral;
    private Double igvCostGeneral;
    private Double igvTotalGeneral;
    private Double invoiceSaleGeneral;
    private Double ticketSaleGeneral;
    private Double documentCollectionGeneral;

    private @DocumentReference(lazy = true)
    VoucherEgressEntity voucherEgress;

    private @DocumentReference(lazy = true)
    VoucherEgressDetailEntity voucherEgressDetail;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    ServicePqtTouristEntity servicePqtTourist;

    private @DocumentReference(lazy = true)
    IgvEntity igv;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    DetractionEntity detraction;

    private Integer paxTotal;

    private Double netImportUsdTotal;
    private Double netImportSolesTotal;
    private Double totalImportUsdTotal;
    private Double totalImportSolesTotal;
}
