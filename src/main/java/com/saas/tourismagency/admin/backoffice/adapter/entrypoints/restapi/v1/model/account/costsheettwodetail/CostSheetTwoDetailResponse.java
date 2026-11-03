package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo.CostSheetTwoResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.IgvResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostSheetTwoDetailResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noItem")
    private Integer noItem;

    @JsonProperty("strNoItemFormat")
    private String strNoItemFormat;

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

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("costSheetTwo")
    private CostSheetTwoResponse costSheetTwo;

    @JsonProperty("voucherEgressDetailItem")
    private VoucherEgressDetailItemResponse voucherEgressDetailItem;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("servicePqtTourist")
    private ServicePqtTouristResponse servicePqtTourist;

    @JsonProperty("igv")
    private IgvResponse igv;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("guideDrive")
    private GuideResponse guideDrive;

    @JsonProperty("guideTransfer")
    private GuideResponse guideTransfer;

    @JsonProperty("supplierType")
    private SupplierTypeResponse supplierType;

    @JsonProperty("touristDestine")
    private TouristDestineResponse touristDestine;

    @JsonProperty("departmentRegion")
    private DepartmentRegionResponse departmentRegion;

    @JsonProperty("documentAccountType")
    private DocAccountTypeResponse documentAccountType;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("strOperator")
    private String strOperator;

    @JsonProperty("netoImporteUsdFormat")
    private String netoImporteUsdFormat;

    @JsonProperty("netoImporteSolesFormat")
    private String netoImporteSolesFormat;

    @JsonProperty("totalImporteUsdFormat")
    private String totalImporteUsdFormat;

    @JsonProperty("totalImporteSolesFormat")
    private String totalImporteSolesFormat;

    @JsonProperty("netoGeneralUsdFormat")
    private String netoGeneralUsdFormat;

    @JsonProperty("igvSfGeneralFormat")
    private String igvSfGeneralFormat;

    @JsonProperty("igvCosteGeneralFormat")
    private String igvCosteGeneralFormat;

    @JsonProperty("igvTotalGeneralFormat")
    private String igvTotalGeneralFormat;

    @JsonProperty("invoiceSaleGeneralFormat")
    private String invoiceSaleGeneralFormat;

    @JsonProperty("boletaSaleGeneralFormat")
    private String boletaSaleGeneralFormat;

    @JsonProperty("documentCobranzaGeneralFormat")
    private String documentCobranzaGeneralFormat;

    @JsonProperty("haveVoucherEgressDetailItem")
    private Boolean haveVoucherEgressDetailItem;

    @JsonProperty("link")
    private String link;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;


    public static CostSheetTwoDetailResponse from(CostSheetTwoDetailDomain domain) {
        CostSheetTwoDetailResponse response = new CostSheetTwoDetailResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setNoItem(domain.getNoItem());
        response.setStrNoItemFormat(domain.getStrNoItemFormat());
        response.setNetoImporteUsd(domain.getNetoImporteUsd());
        response.setNetoImporteSoles(domain.getNetoImporteSoles());
        response.setTotalImporteUsd(domain.getTotalImporteUsd());
        response.setTotalImporteSoles(domain.getTotalImporteSoles());
        response.setObservation(domain.getObservation());
        response.setNetoGeneralUsd(domain.getNetoGeneralUsd());
        response.setIgvSfGeneral(domain.getIgvSfGeneral());
        response.setIgvCosteGeneral(domain.getIgvCosteGeneral());
        response.setIgvTotalGeneral(domain.getIgvTotalGeneral());
        response.setInvoiceSaleGeneral(domain.getInvoiceSaleGeneral());
        response.setBoletaSaleGeneral(domain.getBoletaSaleGeneral());
        response.setDocumentCobranzaGeneral(domain.getDocumentCobranzaGeneral());
        response.setNumberDoc(domain.getNumberDoc());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setNetoImporteUsdFormat(domain.getNetoImporteUsdFormat());
        response.setNetoImporteSolesFormat(domain.getNetoImporteSolesFormat());
        response.setTotalImporteUsdFormat(domain.getTotalImporteUsdFormat());
        response.setTotalImporteSolesFormat(domain.getTotalImporteSolesFormat());
        response.setNetoGeneralUsdFormat(domain.getNetoGeneralUsdFormat());
        response.setIgvSfGeneralFormat(domain.getIgvSfGeneralFormat());
        response.setIgvCosteGeneralFormat(domain.getIgvCosteGeneralFormat());
        response.setIgvTotalGeneralFormat(domain.getIgvTotalGeneralFormat());
        response.setInvoiceSaleGeneralFormat(domain.getInvoiceSaleGeneralFormat());
        response.setBoletaSaleGeneralFormat(domain.getBoletaSaleGeneralFormat());
        response.setDocumentCobranzaGeneralFormat(domain.getDocumentCobranzaGeneralFormat());

        if (domain.getCostSheetTwo() != null &&
                domain.getCostSheetTwo().getId() != null &&
                !domain.getCostSheetTwo().getId().isEmpty()){
            response.setCostSheetTwo(CostSheetTwoResponse.from(domain.getCostSheetTwo()));
        }

        if (domain.getVoucherEgressDetailItem() != null &&
                domain.getVoucherEgressDetailItem().getId() != null &&
                !domain.getVoucherEgressDetailItem().getId().isEmpty()){
            response.setVoucherEgressDetailItem(VoucherEgressDetailItemResponse.from(domain.getVoucherEgressDetailItem()));
            response.setHaveVoucherEgressDetailItem(true);
        }else{
            response.setHaveVoucherEgressDetailItem(false);
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        if (domain.getServicePqtTourist() != null &&
                domain.getServicePqtTourist().getId() != null &&
                !domain.getServicePqtTourist().getId().isEmpty()){
            response.setServicePqtTourist(ServicePqtTouristResponse.from(domain.getServicePqtTourist()));
        }

        if (domain.getIgv() != null &&
                domain.getIgv().getId() != null &&
                !domain.getIgv().getId().isEmpty()){
            response.setIgv(IgvResponse.from(domain.getIgv()));
        }

        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()){
            response.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }

        if (domain.getGuideDrive() != null &&
                domain.getGuideDrive().getId() != null &&
                !domain.getGuideDrive().getId().isEmpty()){
            response.setGuideDrive(GuideResponse.from(domain.getGuideDrive()));
        }

        if (domain.getGuideTransfer() != null &&
                domain.getGuideTransfer().getId() != null &&
                !domain.getGuideTransfer().getId().isEmpty()){
            response.setGuideTransfer(GuideResponse.from(domain.getGuideTransfer()));
        }

        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            response.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }

        if (domain.getTouristDestine() != null &&
                domain.getTouristDestine().getId() != null &&
                !domain.getTouristDestine().getId().isEmpty()){
            response.setTouristDestine(TouristDestineResponse.from(domain.getTouristDestine()));
        }

        if (domain.getDepartmentRegion() != null &&
                domain.getDepartmentRegion().getId() != null &&
                !domain.getDepartmentRegion().getId().isEmpty()){
            response.setDepartmentRegion(DepartmentRegionResponse.from(domain.getDepartmentRegion()));
        }

        if (domain.getDocumentAccountType() != null &&
                domain.getDocumentAccountType().getId() != null &&
                !domain.getDocumentAccountType().getId().isEmpty()){
            response.setDocumentAccountType(DocAccountTypeResponse.from(domain.getDocumentAccountType()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        response.setStrOperator( domain.getStrOperator() );

        response.setLink(domain.getLink());

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            response.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            response.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            response.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        response.setCreatedAt(domain.getCreatedAt());
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));

        return response;
    }
}
