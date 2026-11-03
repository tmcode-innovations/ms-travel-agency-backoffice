package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.RequestOrderServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("arriveAir")
    private String arriveAir;

    @JsonProperty("departureAir")
    private String departureAir;

    @JsonProperty("lodging")
    private String lodging;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("dateOrderService")
    private LocalDate dateOrderService;

    @JsonProperty("dateOrderServiceStr")
    private String dateOrderServiceStr;

    @JsonProperty("dateOrderServiceStrDescription")
    private String dateOrderServiceStrDescription;

    @JsonProperty("dateService")
    private LocalDate dateService;

    @JsonProperty("dateServiceStr")
    private String dateServiceStr;

    @JsonProperty("dateServiceStrDescription")
    private String dateServiceStrDescription;

    @JsonProperty("dateTimeService")
    private LocalDateTime dateTimeService;

    @JsonProperty("dateTimeServiceStr")
    private String dateTimeServiceStr;

    @JsonProperty("activeOrderService")
    private Boolean activeOrderService;

    @JsonProperty("activeOrderServiceDescription")
    private String activeOrderServiceDescription;

    @JsonProperty("orderServiceState")
    private String orderServiceState;

    @JsonProperty("orderServiceStateDescription")
    private String orderServiceStateDescription;

    @JsonProperty("orderServiceType")
    private String orderServiceType;

    @JsonProperty("orderServiceTypeDescription")
    private String orderServiceTypeDescription;

    @JsonProperty("requestOrderService")
    private RequestOrderServiceResponse requestOrderService;

    @JsonProperty("registerBookingOperator")
    private RegisterBookingOperatorResponse registerBookingOperator;

    @JsonProperty("supplierOperator")
    private SupplierOperatorResponse supplierOperator;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("supplierGuide")
    private SupplierGuideResponse supplierGuide;

    @JsonProperty("supplierTransport")
    private SupplierTransportResponse supplierTransport;

    @JsonProperty("supplierLodging")
    private SupplierLodgingResponse supplierLodging;

    @JsonProperty("supplierLodger")
    private SupplierLodgerResponse supplierLodger;

    @JsonProperty("supplierGlamping")
    private SupplierGlampingResponse supplierGlamping;

    @JsonProperty("responsible")
    private EmployeeResponse responsible;

    @JsonProperty("drive")
    private GuideResponse drive;

    @JsonProperty("guideTransfer")
    private GuideResponse guideTransfer;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("supplierType")
    private SupplierTypeResponse supplierType;

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

    public static OrderServiceResponse from(OrderServiceDomain domain) {
        OrderServiceResponse response = new OrderServiceResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setArriveAir(domain.getArriveAir());
        response.setDepartureAir(domain.getDepartureAir());
        response.setLodging(domain.getLodging());
        response.setCantPax(domain.getCantPax());
        response.setObservation(domain.getObservation());
        response.setDateOrderService(domain.getDateOrderService());
        response.setDateOrderServiceStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateOrderService()));
        response.setDateOrderServiceStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateOrderService()));

        response.setDateService(domain.getDateService());
        response.setDateServiceStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateService()));
        response.setDateServiceStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateService()));

        response.setDateTimeService(domain.getDateTimeService());
        response.setDateTimeServiceStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getDateTimeService()));

        response.setActiveOrderService(domain.getActiveOrderService());
        response.setActiveOrderServiceDescription(domain.getActiveOrderService() != null ? domain.getActiveOrderService() ? "SI" : "NO" : "");

        if (domain.getOrderServiceState() != null && !domain.getOrderServiceState().isEmpty()){
            response.setOrderServiceState(domain.getOrderServiceState());
            OrderServiceStateEnum.OrderServiceStateModel orderServiceStateModelData = EnumMappeableService
                    .map(OrderServiceStateEnum.class)
                    .stream().filter(
                            orderServiceStateModel -> orderServiceStateModel.name().equals(domain.getOrderServiceState()))
                    .findFirst().get();
            response.setOrderServiceStateDescription(orderServiceStateModelData.description());
        }

        if (domain.getOrderServiceType() != null && !domain.getOrderServiceType().isEmpty()){
            response.setOrderServiceType(domain.getOrderServiceType());
            OrderServiceTypeEnum.OrderServiceTypeModel orderServiceTypeModelData = EnumMappeableService
                    .map(OrderServiceTypeEnum.class)
                    .stream().filter(
                            orderServiceTypeModel -> orderServiceTypeModel.name().equals(domain.getOrderServiceType()))
                    .findFirst().get();
            response.setOrderServiceTypeDescription(orderServiceTypeModelData.description());
        }

        if (domain.getRequestOrderService() != null &&
                domain.getRequestOrderService().getId() != null &&
                !domain.getRequestOrderService().getId().isEmpty()){
            response.setRequestOrderService(RequestOrderServiceResponse.from(domain.getRequestOrderService()));
        }

        if (domain.getRegisterBookingOperator() != null &&
                domain.getRegisterBookingOperator().getId() != null &&
                !domain.getRegisterBookingOperator().getId().isEmpty()){
            response.setRegisterBookingOperator(RegisterBookingOperatorResponse.from(domain.getRegisterBookingOperator()));
        }

        if (domain.getSupplierOperator() != null &&
                domain.getSupplierOperator().getId() != null &&
                !domain.getSupplierOperator().getId().isEmpty()){
            response.setSupplierOperator(SupplierOperatorResponse.from(domain.getSupplierOperator()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getSupplierGuide() != null &&
                domain.getSupplierGuide().getId() != null &&
                !domain.getSupplierGuide().getId().isEmpty()){
            response.setSupplierGuide(SupplierGuideResponse.from(domain.getSupplierGuide()));
        }

        if (domain.getSupplierTransport() != null &&
                domain.getSupplierTransport().getId() != null &&
                !domain.getSupplierTransport().getId().isEmpty()){
            response.setSupplierTransport(SupplierTransportResponse.from(domain.getSupplierTransport()));
        }

        if (domain.getSupplierLodging() != null &&
                domain.getSupplierLodging().getId() != null &&
                !domain.getSupplierLodging().getId().isEmpty()){
            response.setSupplierLodging(SupplierLodgingResponse.from(domain.getSupplierLodging()));
        }

        if (domain.getSupplierLodger() != null &&
                domain.getSupplierLodger().getId() != null &&
                !domain.getSupplierLodger().getId().isEmpty()){
            response.setSupplierLodger(SupplierLodgerResponse.from(domain.getSupplierLodger()));
        }

        if (domain.getSupplierGlamping() != null &&
                domain.getSupplierGlamping().getId() != null &&
                !domain.getSupplierGlamping().getId().isEmpty()){
            response.setSupplierGlamping(SupplierGlampingResponse.from(domain.getSupplierGlamping()));
        }

        if (domain.getResponsible() != null &&
                domain.getResponsible().getId() != null &&
                !domain.getResponsible().getId().isEmpty()){
            response.setResponsible(EmployeeResponse.from(domain.getResponsible()));
        }

        if (domain.getDrive() != null &&
                domain.getDrive().getId() != null &&
                !domain.getDrive().getId().isEmpty()){
            response.setDrive(GuideResponse.from(domain.getDrive()));
        }

        if (domain.getGuideTransfer() != null &&
                domain.getGuideTransfer().getId() != null &&
                !domain.getGuideTransfer().getId().isEmpty()){
            response.setGuideTransfer(GuideResponse.from(domain.getGuideTransfer()));
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
