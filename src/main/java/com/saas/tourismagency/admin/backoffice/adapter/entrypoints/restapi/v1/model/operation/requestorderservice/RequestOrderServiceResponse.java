package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderServiceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("requestOrderServiceState")
    private String requestOrderServiceState;

    @JsonProperty("requestOrderServiceStateDescription")
    protected String requestOrderServiceStateDescription;

    @JsonProperty("registerBookingOperator")
    private RegisterBookingOperatorResponse registerBookingOperator;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("supplierOperator")
    private SupplierOperatorResponse supplierOperator;

    @JsonProperty("activeRequestOrderService")
    private Boolean activeRequestOrderService;

    @JsonProperty("activeRequestOrderServiceDescription")
    private String activeRequestOrderServiceDescription;

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

    @JsonProperty("createdAtStrDescription")
    private String createdAtStrDescription;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static RequestOrderServiceResponse from(RequestOrderServiceDomain domain) {
        RequestOrderServiceResponse response = new RequestOrderServiceResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setActiveRequestOrderService(domain.getActiveRequestOrderService());
        response.setActiveRequestOrderServiceDescription(domain.getActiveRequestOrderService() != null ? domain.getActiveRequestOrderService() ? "SI" : "NO" : "");

        if (domain.getRequestOrderServiceState() != null && !domain.getRequestOrderServiceState().isEmpty()){
            response.setRequestOrderServiceState(domain.getRequestOrderServiceState());
            RequestOrderServiceStateEnum.RequestOrderServiceStateModel requestOrderServiceStateModelData = EnumMappeableService
                    .map(RequestOrderServiceStateEnum.class)
                    .stream().filter(
                            requestOrderServiceStateModel -> requestOrderServiceStateModel.name().equals(domain.getRequestOrderServiceState()))
                    .findFirst().get();
            response.setRequestOrderServiceStateDescription(requestOrderServiceStateModelData.description());
        }

        if (domain.getRegisterBookingOperator() != null &&
                domain.getRegisterBookingOperator().getId() != null &&
                !domain.getRegisterBookingOperator().getId().isEmpty()){
            response.setRegisterBookingOperator(RegisterBookingOperatorResponse.from(domain.getRegisterBookingOperator()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getSupplierOperator() != null &&
                domain.getSupplierOperator().getId() != null &&
                !domain.getSupplierOperator().getId().isEmpty()){
            response.setSupplierOperator(SupplierOperatorResponse.from(domain.getSupplierOperator()));
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

        response.setCreatedAtStrDescription(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(domain.getCreatedAt()));

        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));

        return response;
    }
}
