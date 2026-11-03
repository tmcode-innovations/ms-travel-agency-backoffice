package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class CostSheetTwoDetailDomain extends BaseDomain implements Serializable , Comparable<CostSheetTwoDetailDomain>{

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

    private CostSheetTwoDomain costSheetTwo;

    private VoucherEgressDetailItemDomain voucherEgressDetailItem;

    private CurrencyDomain currency;

    private ServicePqtTouristDomain servicePqtTourist;

    private IgvDomain igv;

    private SupplierDomain supplier;

    private GuideDomain guideDrive;

    private GuideDomain guideTransfer;

    private SupplierTypeDomain supplierType;

    private TouristDestineDomain touristDestine;

    private DepartmentRegionDomain departmentRegion;

    private DocumentAccountTypeDomain documentAccountType;

    private ChangeTypeDomain changeType;

    private String link;

    @Getter(AccessLevel.NONE)
    private String netoImporteUsdFormat;

    public String getNetoImporteUsdFormat() {
        return NumberUtil.getImportFormat(netoImporteUsd);
    }

    @Getter(AccessLevel.NONE)
    private String netoImporteSolesFormat;

    public String getNetoImporteSolesFormat() {
        return NumberUtil.getImportFormat(netoImporteSoles);
    }

    @Getter(AccessLevel.NONE)
    private String totalImporteUsdFormat;

    public String getTotalImporteUsdFormat() {
        return NumberUtil.getImportFormat(totalImporteUsd);
    }

    @Getter(AccessLevel.NONE)
    private String totalImporteSolesFormat;

    public String getTotalImporteSolesFormat() {
        return NumberUtil.getImportFormat(totalImporteSoles);
    }

    @Getter(AccessLevel.NONE)
    private String netoGeneralUsdFormat;

    public String getNetoGeneralUsdFormat() {
        return NumberUtil.getImportFormat(netoGeneralUsd);
    }

    @Getter(AccessLevel.NONE)
    private String igvSfGeneralFormat;

    public String getIgvSfGeneralFormat() {
        return NumberUtil.getImportFormat(igvSfGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String igvCosteGeneralFormat;

    public String getIgvCosteGeneralFormat() {
        return NumberUtil.getImportFormat(igvCosteGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String igvTotalGeneralFormat;

    public String getIgvTotalGeneralFormat() {
        return NumberUtil.getImportFormat(igvTotalGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String invoiceSaleGeneralFormat;

    public String getInvoiceSaleGeneralFormat() {
        return NumberUtil.getImportFormat(invoiceSaleGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String boletaSaleGeneralFormat;

    public String getBoletaSaleGeneralFormat() {
        return NumberUtil.getImportFormat(boletaSaleGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String documentCobranzaGeneralFormat;

    public String getDocumentCobranzaGeneralFormat() {
        return NumberUtil.getImportFormat(documentCobranzaGeneral);
    }

    @Getter(AccessLevel.NONE)
    private String strNoItemFormat;

    public String getStrNoItemFormat() {
        String result = "";
        if (noItem != null){
            String numberItem = noItem.toString();
            if (numberItem.length() == 1){
                numberItem = "00" + numberItem;
            }else if (numberItem.length() == 2){
                numberItem = "0" + numberItem;
            }
            result = numberItem;
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    private String strOperator;

    public String getStrOperator() {
        StringBuffer result = new StringBuffer();
        if (supplier != null &&
                supplier.getId() != null &&
                !supplier.getId().isEmpty()){
            result.append(supplier.getName());
        }else if ( guideDrive != null &&
                guideDrive.getId() != null &&
                !guideDrive.getId().isEmpty() ){
            result.append(guideDrive.getPerson().getFullName());
        }else if ( guideTransfer != null &&
                guideTransfer.getId() != null &&
                !guideTransfer.getId().isEmpty() ){
            result.append(guideTransfer.getPerson().getFullName());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CostSheetTwoDetailDomain)) return false;
        CostSheetTwoDetailDomain that = (CostSheetTwoDetailDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(CostSheetTwoDetailDomain other) {
        return Integer.compare(this.noItem, other.noItem);
    }

}
