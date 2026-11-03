package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.DetractionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherTypeEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEgressResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("cantRegister")
    private Integer cantRegister;

    @JsonProperty("noCheck")
    private String noCheck;

    @JsonProperty("voucherState")
    private String voucherState;

    @JsonProperty("voucherStateDescription")
    private String voucherStateDescription;

    @JsonProperty("voucherType")
    private String voucherType;

    @JsonProperty("voucherTypeDescription")
    private String voucherTypeDescription;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("importTotalSolesFormat")
    private String importTotalSolesFormat;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalUsdFormat")
    private String importTotalUsdFormat;

    @JsonProperty("importSoles")
    private Double importSoles;

    @JsonProperty("importSolesFormat")
    private String importSolesFormat;

    @JsonProperty("importUsd")
    private Double importUsd;

    @JsonProperty("importUsdFormat")
    private String importUsdFormat;

    @JsonProperty("detractionSoles")
    private Double detractionSoles;

    @JsonProperty("detractionSolesFormat")
    private String detractionSolesFormat;

    @JsonProperty("detractionUsd")
    private Double detractionUsd;

    @JsonProperty("detractionUsdFormat")
    private String detractionUsdFormat;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("dateCancel")
    private LocalDate dateCancel;

    @JsonProperty("dateCancelStr")
    private String dateCancelStr;

    @JsonProperty("dateCancelStrDescription")
    private String dateCancelStrDescription;

    @JsonProperty("voucherDetraction")
    private Boolean voucherDetraction;

    @JsonProperty("voucherDetractionDescription")
    private String voucherDetractionDescription;

    @JsonProperty("dateProcess")
    private DateProcessResponse dateProcess;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("responsible")
    private EmployeeResponse responsible;

    @JsonProperty("bank")
    private BankResponse bank;

    @JsonProperty("parent")
    private VoucherEgressResponse parent;

    @JsonProperty("detraction")
    private DetractionResponse detraction;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("supplierType")
    private SupplierTypeResponse supplierType;

    @JsonProperty("departmentRegion")
    private DepartmentRegionResponse departmentRegion;

    @JsonProperty("valueTypeShowSupplier")
    private String valueTypeShowSupplier;

    @JsonProperty("valueTypeShowSupplierDescription")
    private String valueTypeShowSupplierDescription;

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

    public static VoucherEgressResponse from(VoucherEgressDomain domain) {
        VoucherEgressResponse response = new VoucherEgressResponse();
        response.setId(domain.getId());

        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setName(domain.getIdentify() + "- " + domain.getDescription());

        response.setYear(domain.getYear());
        response.setCantRegister(domain.getCantRegister());
        response.setNoCheck(domain.getNoCheck());
        response.setVoucherState(domain.getVoucherState());
        if (domain.getVoucherState() != null && !domain.getVoucherState().isEmpty()){
            VoucherStateEnum.VoucherStateModel voucherStateModelData = EnumMappeableService
                    .map(VoucherStateEnum.class)
                    .stream().filter(
                            voucherStateModel -> voucherStateModel.name().equals(domain.getVoucherState()))
                    .findFirst().get();
            response.setVoucherStateDescription(voucherStateModelData.description());
        }
        response.setVoucherType(domain.getVoucherType());
        if (domain.getVoucherType() != null && !domain.getVoucherType().isEmpty()){
            VoucherTypeEnum.VoucherTypeModel voucherTypeModelData = EnumMappeableService
                    .map(VoucherTypeEnum.class)
                    .stream().filter(
                            voucherTypeModel -> voucherTypeModel.name().equals(domain.getVoucherType()))
                    .findFirst().get();
            response.setVoucherTypeDescription(voucherTypeModelData.description());
        }

        response.setImportUsd(domain.getImportUsd());
        response.setImportUsdFormat(domain.getImportUsdFormat());
        response.setImportSoles(domain.getImportSoles());
        response.setImportSolesFormat(domain.getImportSolesFormat());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());
        response.setDetractionUsd(domain.getDetractionUsd());
        response.setDetractionUsdFormat(domain.getDetractionUsdFormat());
        response.setDetractionSoles(domain.getDetractionSoles());
        response.setDetractionSolesFormat(domain.getDetractionSolesFormat());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setDateCancel(domain.getDateCancel());
        response.setDateCancelStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateCancel()));
        response.setDateCancelStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateCancel()));
        response.setVoucherDetraction(domain.getVoucherDetraction());
        response.setVoucherDetractionDescription( domain.getVoucherDetraction() != null && domain.getVoucherDetraction() ? "SI" : "NO");

        if (domain.getDateProcess() != null &&
                domain.getDateProcess().getId() != null &&
                !domain.getDateProcess().getId().isEmpty()){
            response.setDateProcess(DateProcessResponse.from(domain.getDateProcess()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        if (domain.getResponsible() != null &&
                domain.getResponsible().getId() != null &&
                !domain.getResponsible().getId().isEmpty()){
            response.setResponsible(EmployeeResponse.from(domain.getResponsible()));
        }

        if (domain.getBank() != null &&
                domain.getBank().getId() != null &&
                !domain.getBank().getId().isEmpty()){
            response.setBank(BankResponse.from(domain.getBank()));
        }

        if (domain.getParent() != null &&
                domain.getParent().getId() != null &&
                !domain.getParent().getId().isEmpty()){
            response.setParent(VoucherEgressResponse.from(domain.getParent()));
        }

        if (domain.getDetraction() != null &&
                domain.getDetraction().getId() != null &&
                !domain.getDetraction().getId().isEmpty()){
            response.setDetraction(DetractionResponse.from(domain.getDetraction()));
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
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            response.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getDepartmentRegion() != null &&
                domain.getDepartmentRegion().getId() != null &&
                !domain.getDepartmentRegion().getId().isEmpty()){
            response.setDepartmentRegion(DepartmentRegionResponse.from(domain.getDepartmentRegion()));
        }

        response.setValueTypeShowSupplier(domain.getValueTypeShowSupplier());
        if (domain.getValueTypeShowSupplier() != null && !domain.getValueTypeShowSupplier().isEmpty()){
            ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel valueTypeShowSupplierModelData = EnumMappeableService
                    .map(ValueTypeShowSupplierEnum.class)
                    .stream().filter(
                            valueTypeShowSupplierModel -> valueTypeShowSupplierModel.name().equals(domain.getValueTypeShowSupplier()))
                    .findFirst().get();
            response.setValueTypeShowSupplierDescription(valueTypeShowSupplierModelData.description().toUpperCase());
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

    public static VoucherEgressResponse fromConfiguration(VoucherEgressDomain domain) {
        VoucherEgressResponse response = new VoucherEgressResponse();
        response.setId(domain.getId());

        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setName(domain.getIdentify() + "- " + domain.getDescription());

        response.setYear(domain.getYear());
        response.setCantRegister(domain.getCantRegister());
        response.setNoCheck(domain.getNoCheck());
        response.setVoucherState(domain.getVoucherState());
        if (domain.getVoucherState() != null && !domain.getVoucherState().isEmpty()){
            VoucherStateEnum.VoucherStateModel voucherStateModelData = EnumMappeableService
                    .map(VoucherStateEnum.class)
                    .stream().filter(
                            voucherStateModel -> voucherStateModel.name().equals(domain.getVoucherState()))
                    .findFirst().get();
            if (domain.getVoucherState().equals(VoucherStateEnum.CANCELLED.getName())) {
                response.setVoucherStateDescription("Pagado");
            }else{
                response.setVoucherStateDescription(voucherStateModelData.description());
            }

        }
        response.setVoucherType(domain.getVoucherType());
        if (domain.getVoucherType() != null && !domain.getVoucherType().isEmpty()){
            VoucherTypeEnum.VoucherTypeModel voucherTypeModelData = EnumMappeableService
                    .map(VoucherTypeEnum.class)
                    .stream().filter(
                            voucherTypeModel -> voucherTypeModel.name().equals(domain.getVoucherType()))
                    .findFirst().get();
            response.setVoucherTypeDescription(voucherTypeModelData.description());
        }

        response.setImportUsd(domain.getImportUsd());
        response.setImportUsdFormat(domain.getImportUsdFormat());
        response.setImportSoles(domain.getImportSoles());
        response.setImportSolesFormat(domain.getImportSolesFormat());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());
        response.setDetractionUsd(domain.getDetractionUsd());
        response.setDetractionUsdFormat(domain.getDetractionUsdFormat());
        response.setDetractionSoles(domain.getDetractionSoles());
        response.setDetractionSolesFormat(domain.getDetractionSolesFormat());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setDateCancel(domain.getDateCancel());
        response.setDateCancelStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateCancel()));
        response.setDateCancelStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateCancel()));
        response.setVoucherDetraction(domain.getVoucherDetraction());
        response.setVoucherDetractionDescription( domain.getVoucherDetraction() != null && domain.getVoucherDetraction() ? "SI" : "NO");

        if (domain.getDateProcess() != null &&
                domain.getDateProcess().getId() != null &&
                !domain.getDateProcess().getId().isEmpty()){
            response.setDateProcess(DateProcessResponse.from(domain.getDateProcess()));
        }

        if (domain.getChangeType() != null &&
                domain.getChangeType().getId() != null &&
                !domain.getChangeType().getId().isEmpty()){
            response.setChangeType(ChangeTypeResponse.from(domain.getChangeType()));
        }

        if (domain.getCurrency() != null &&
                domain.getCurrency().getId() != null &&
                !domain.getCurrency().getId().isEmpty()){
            response.setCurrency(CurrencyResponse.from(domain.getCurrency()));
        }

        if (domain.getResponsible() != null &&
                domain.getResponsible().getId() != null &&
                !domain.getResponsible().getId().isEmpty()){
            response.setResponsible(EmployeeResponse.from(domain.getResponsible()));
        }

        if (domain.getBank() != null &&
                domain.getBank().getId() != null &&
                !domain.getBank().getId().isEmpty()){
            response.setBank(BankResponse.from(domain.getBank()));
        }

        if (domain.getParent() != null &&
                domain.getParent().getId() != null &&
                !domain.getParent().getId().isEmpty()){
            response.setParent(VoucherEgressResponse.from(domain.getParent()));
        }

        if (domain.getDetraction() != null &&
                domain.getDetraction().getId() != null &&
                !domain.getDetraction().getId().isEmpty()){
            response.setDetraction(DetractionResponse.from(domain.getDetraction()));
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
        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            response.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getDepartmentRegion() != null &&
                domain.getDepartmentRegion().getId() != null &&
                !domain.getDepartmentRegion().getId().isEmpty()){
            response.setDepartmentRegion(DepartmentRegionResponse.from(domain.getDepartmentRegion()));
        }

        response.setValueTypeShowSupplier(domain.getValueTypeShowSupplier());
        if (domain.getValueTypeShowSupplier() != null && !domain.getValueTypeShowSupplier().isEmpty()){
            ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel valueTypeShowSupplierModelData = EnumMappeableService
                    .map(ValueTypeShowSupplierEnum.class)
                    .stream().filter(
                            valueTypeShowSupplierModel -> valueTypeShowSupplierModel.name().equals(domain.getValueTypeShowSupplier()))
                    .findFirst().get();
            if (domain.getValueTypeShowSupplier().equals(ValueTypeShowSupplierEnum.BOOKIG.getName())) {
                response.setValueTypeShowSupplierDescription("RESERVAS");
            }else{
                response.setValueTypeShowSupplierDescription(valueTypeShowSupplierModelData.description());
            }
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
