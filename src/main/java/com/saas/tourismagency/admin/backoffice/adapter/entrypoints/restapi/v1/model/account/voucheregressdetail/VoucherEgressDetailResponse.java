package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress.VoucherEgressResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.DetractionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressDetailResponse {

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

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("valueTypeShowSupplier")
    private String valueTypeShowSupplier;

    @JsonProperty("valueTypeShowSupplierDescription")
    private String valueTypeShowSupplierDescription;

    @JsonProperty("numberDoc")
    private String numberDoc;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("montSoles")
    private Double montSoles;

    @JsonProperty("montSolesFormat")
    private String montSolesFormat;

    @JsonProperty("montUsd")
    private Double montUsd;

    @JsonProperty("montUsdFormat")
    private String montUsdFormat;

    @JsonProperty("detractionSoles")
    private Double detractionSoles;

    @JsonProperty("detractionSolesFormat")
    private String detractionSolesFormat;

    @JsonProperty("detractionUsd")
    private Double detractionUsd;

    @JsonProperty("detractionUsdFormat")
    private String detractionUsdFormat;

    @JsonProperty("balanceSoles")
    private Double balanceSoles;

    @JsonProperty("balanceSolesFormat")
    private String balanceSolesFormat;

    @JsonProperty("balanceUsd")
    private Double balanceUsd;

    @JsonProperty("balanceUsdFormat")
    private String balanceUsdFormat;

    @JsonProperty("voucherEgress")
    private VoucherEgressResponse voucherEgress;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

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

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("documentAccountType")
    private DocAccountTypeResponse documentAccountType;

    @JsonProperty("detraction")
    private DetractionResponse detraction;

    @JsonProperty("itineraryItem")
    private ItineraryItemResponse itineraryItem;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

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

    public static VoucherEgressDetailResponse from(VoucherEgressDetailDomain domain) {
        VoucherEgressDetailResponse response = new VoucherEgressDetailResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setNoItem(domain.getNoItem());
        response.setStrNoItemFormat(domain.getStrNoItemFormat());

        response.setYear(domain.getYear());
        response.setValueTypeShowSupplier(domain.getValueTypeShowSupplier());
        if (domain.getValueTypeShowSupplier() != null && !domain.getValueTypeShowSupplier().isEmpty()){
            ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel valueTypeShowSupplierModelData = EnumMappeableService
                    .map(ValueTypeShowSupplierEnum.class)
                    .stream().filter(
                            valueTypeShowSupplierModel -> valueTypeShowSupplierModel.name().equals(domain.getValueTypeShowSupplier()))
                    .findFirst().get();
            response.setValueTypeShowSupplierDescription(valueTypeShowSupplierModelData.description().toUpperCase());
        }
        response.setNumberDoc(domain.getNumberDoc());
        response.setObservation(domain.getObservation());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setMontSoles(domain.getMontSoles());
        response.setMontSolesFormat(domain.getMontSolesFormat());
        response.setMontUsd(domain.getMontUsd());
        response.setMontUsdFormat(domain.getMontUsdFormat());
        response.setDetractionUsd(domain.getDetractionUsd());
        response.setDetractionUsdFormat(domain.getDetractionUsdFormat());
        response.setDetractionSoles(domain.getDetractionSoles());
        response.setDetractionSolesFormat(domain.getDetractionSolesFormat());
        response.setBalanceUsd(domain.getBalanceUsd());
        response.setBalanceUsdFormat(domain.getBalanceUsdFormat());
        response.setBalanceSoles(domain.getBalanceSoles());
        response.setBalanceSolesFormat(domain.getBalanceSolesFormat());

        response.setPaxTotal(domain.getPaxTotal());

        if (domain.getVoucherEgress() != null &&
                domain.getVoucherEgress().getId() != null &&
                !domain.getVoucherEgress().getId().isEmpty()){
            response.setVoucherEgress(VoucherEgressResponse.from(domain.getVoucherEgress()));
        }
        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
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
            response.setGuideDrive(GuideResponse.from(domain.getGuideTransfer()));
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
        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }
        if (domain.getDocumentAccountType() != null &&
                domain.getDocumentAccountType().getId() != null &&
                !domain.getDocumentAccountType().getId().isEmpty()){
            response.setDocumentAccountType(DocAccountTypeResponse.from(domain.getDocumentAccountType()));
        }
        if (domain.getDetraction() != null &&
                domain.getDetraction().getId() != null &&
                !domain.getDetraction().getId().isEmpty()){
            response.setDetraction(DetractionResponse.from(domain.getDetraction()));
        }
        if (domain.getItineraryItem() != null &&
                domain.getItineraryItem().getId() != null &&
                !domain.getItineraryItem().getId().isEmpty()){
            response.setItineraryItem(ItineraryItemResponse.from(domain.getItineraryItem()));
        }

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
