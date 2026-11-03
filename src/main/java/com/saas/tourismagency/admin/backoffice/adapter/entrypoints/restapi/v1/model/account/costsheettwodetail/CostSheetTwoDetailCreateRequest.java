package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoDetailCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("netoImporteUsd")
    private Double netoImporteUsd;

    @JsonProperty("netoImporteSoles")
    private Double netoImporteSoles;

    @JsonProperty("totalImporteUsd")
    private Double totalImporteUsd;

    @JsonProperty("totalImporteSoles")
    private Double totalImporteSoles;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("netoGeneralUsd")
    private Double netoGeneralUsd;

    @JsonProperty("igvSfGeneral")
    private Double igvSfGeneral;

    @JsonProperty("igvCosteGeneral")
    private Double igvCosteGeneral;

    @JsonProperty("igvTotalGeneral")
    private Double igvTotalGeneral;

    @JsonProperty("invoiceSaleGeneral")
    private Double invoiceSaleGeneral;

    @JsonProperty("boletaSaleGeneral")
    private Double boletaSaleGeneral;

    @JsonProperty("documentCobranzaGeneral")
    private Double documentCobranzaGeneral;

    @JsonProperty("numberDoc")
    private String numberDoc;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("costSheetTwo")
    private String costSheetTwo;

    @JsonProperty("voucherEgressDetailItem")
    private String voucherEgressDetailItem;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("servicePqtTourist")
    private String servicePqtTourist;

    @JsonProperty("igv")
    private String igv;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("guideDrive")
    private String guideDrive;

    @JsonProperty("guideTransfer")
    private String guideTransfer;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("touristDestine")
    private String touristDestine;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    @JsonProperty("documentAccountType")
    private String documentAccountType;

    @JsonProperty("changeType")
    private String changeType;

    @JsonProperty("link")
    private String link;

    public CostSheetTwoDetailDomain to() {
        CostSheetTwoDetailDomain domain = new CostSheetTwoDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setNetoImporteUsd(netoImporteUsd);
        domain.setNetoImporteSoles(netoImporteSoles);
        domain.setTotalImporteUsd(totalImporteUsd);
        domain.setTotalImporteSoles(totalImporteSoles);
        domain.setObservation(observation);
        domain.setNetoGeneralUsd(netoGeneralUsd);
        domain.setIgvSfGeneral(igvSfGeneral);
        domain.setIgvCosteGeneral(igvCosteGeneral);
        domain.setIgvTotalGeneral(igvTotalGeneral);
        domain.setInvoiceSaleGeneral(invoiceSaleGeneral);
        domain.setBoletaSaleGeneral(boletaSaleGeneral);
        domain.setDocumentCobranzaGeneral(documentCobranzaGeneral);
        domain.setNumberDoc(numberDoc);
        domain.setDate(date);
        domain.setLink(link);
        return domain;
    }

    public CostSheetTwoDetailDomain to(
            CostSheetTwoDomain costSheetTwoDomain,
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain,
            CurrencyDomain currencyDomain,
            ServicePqtTouristDomain servicePqtTouristDomain,
            IgvDomain igvDomain,
            SupplierDomain supplierDomain,
            GuideDomain guideDriveDomain,
            GuideDomain guideTransferDomain,
            SupplierTypeDomain supplierTypeDomain,
            TouristDestineDomain touristDestineDomain,
            DepartmentRegionDomain departmentRegionDomain,
            DocumentAccountTypeDomain documentAccountTypeDomain,
            ChangeTypeDomain changeTypeDomain
    ) {
        CostSheetTwoDetailDomain domain = new CostSheetTwoDetailDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoItem(noItem);
        domain.setNetoImporteUsd(netoImporteUsd);
        domain.setNetoImporteSoles(netoImporteSoles);
        domain.setTotalImporteUsd(totalImporteUsd);
        domain.setTotalImporteSoles(totalImporteSoles);
        domain.setObservation(observation);
        domain.setNetoGeneralUsd(netoGeneralUsd);
        domain.setIgvSfGeneral(igvSfGeneral);
        domain.setIgvCosteGeneral(igvCosteGeneral);
        domain.setIgvTotalGeneral(igvTotalGeneral);
        domain.setInvoiceSaleGeneral(invoiceSaleGeneral);
        domain.setBoletaSaleGeneral(boletaSaleGeneral);
        domain.setDocumentCobranzaGeneral(documentCobranzaGeneral);
        domain.setNumberDoc(numberDoc);
        domain.setDate(date);
        domain.setLink(link);

        if (costSheetTwoDomain != null)
            domain.setCostSheetTwo(costSheetTwoDomain);

        if (voucherEgressDetailItemDomain != null)
            domain.setVoucherEgressDetailItem(voucherEgressDetailItemDomain);

        if (currencyDomain != null)
            domain.setCurrency(currencyDomain);

        if (servicePqtTouristDomain != null)
            domain.setServicePqtTourist(servicePqtTouristDomain);

        if (igvDomain != null)
            domain.setIgv(igvDomain);

        if (supplierDomain != null)
            domain.setSupplier(supplierDomain);

        if (guideDriveDomain != null)
            domain.setGuideDrive(guideDriveDomain);

        if (guideTransferDomain != null)
            domain.setGuideTransfer(guideTransferDomain);

        if (supplierTypeDomain != null)
            domain.setSupplierType(supplierTypeDomain);

        if (touristDestine != null)
            domain.setTouristDestine(touristDestineDomain);

        if (departmentRegionDomain != null)
            domain.setDepartmentRegion(departmentRegionDomain);

        if (documentAccountTypeDomain != null)
            domain.setDocumentAccountType(documentAccountTypeDomain);

        if (changeTypeDomain != null)
            domain.setChangeType(changeTypeDomain);

        return domain;
    }
}
