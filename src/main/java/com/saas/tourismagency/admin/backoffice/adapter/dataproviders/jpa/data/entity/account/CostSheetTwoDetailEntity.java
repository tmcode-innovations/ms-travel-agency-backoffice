package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
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
@Document(collection = "account_cost_sheet_two_details")
public class CostSheetTwoDetailEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer noItem;
    private Double netoImporteUsd;
    private Double netoImporteSoles;
    private Double totalImporteUsd;
    private Double totalImporteSoles;
    private String observation;
    private Double netoGeneralUsd;
    private Double igvSfGeneral;
    private Double igvCosteGeneral;
    private Double igvTotalGeneral;
    private Double invoiceSaleGeneral;
    private Double boletaSaleGeneral;
    private Double documentCobranzaGeneral;
    private String numberDoc;
    private LocalDate date;

    private @DocumentReference(lazy = true)
    CostSheetTwoEntity costSheetTwo;

    private @DocumentReference(lazy = true)
    VoucherEgressDetailItemEntity voucherEgressDetailItem;

    private @DocumentReference(lazy = true)
    CurrencyEntity currency;

    private @DocumentReference(lazy = true)
    ServicePqtTouristEntity servicePqtTourist;

    private @DocumentReference(lazy = true)
    IgvEntity igv;

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
    DocumentAccountTypeEntity documentAccountType;

    private @DocumentReference(lazy = true)
    ChangeTypeEntity changeType;

    private String link;
}
