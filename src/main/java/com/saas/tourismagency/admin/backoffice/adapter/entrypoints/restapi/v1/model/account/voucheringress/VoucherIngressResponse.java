package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.ChangeTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherIngressResponse {

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

    @JsonProperty("identifyOther")
    private String identifyOther;

    @JsonProperty("cantRegister")
    private Integer cantRegister;

    @JsonProperty("importTotalSoles")
    private Double importTotalSoles;

    @JsonProperty("importTotalSolesFormat")
    private String importTotalSolesFormat;

    @JsonProperty("importTotalUsd")
    private Double importTotalUsd;

    @JsonProperty("importTotalUsdFormat")
    private String importTotalUsdFormat;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("voucherState")
    private String voucherState;

    @JsonProperty("voucherStateDescription")
    private String voucherStateDescription;

    @JsonProperty("dateProcess")
    private DateProcessResponse dateProcess;

    @JsonProperty("changeType")
    private ChangeTypeResponse changeType;

    @JsonProperty("currency")
    private CurrencyResponse currency;

    @JsonProperty("responsible")
    private EmployeeResponse responsible;

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

    public static VoucherIngressResponse from(VoucherIngressDomain domain) {
        VoucherIngressResponse response = new VoucherIngressResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setYear(domain.getYear());
        response.setIdentifyOther(domain.getIdentifyOther());
        response.setCantRegister(domain.getCantRegister());
        response.setImportTotalSoles(domain.getImportTotalSoles());
        response.setImportTotalSolesFormat(domain.getImportTotalSolesFormat());
        response.setImportTotalUsd(domain.getImportTotalUsd());
        response.setImportTotalUsdFormat(domain.getImportTotalUsdFormat());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));
        response.setVoucherState(domain.getVoucherState());
        if (domain.getVoucherState() != null && !domain.getVoucherState().isEmpty()){
            VoucherStateEnum.VoucherStateModel voucherStateModelData = EnumMappeableService
                    .map(VoucherStateEnum.class)
                    .stream().filter(
                            voucherStateModel -> voucherStateModel.name().equals(domain.getVoucherState()))
                    .findFirst().get();
            response.setVoucherStateDescription(voucherStateModelData.description());
        }
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
