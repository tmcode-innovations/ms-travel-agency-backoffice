package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceCreateRequest {

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

    @JsonProperty("orderServiceType")
    private String orderServiceType;

    @JsonProperty("dateOrderService")
    private LocalDate dateOrderService;

    @JsonProperty("dateService")
    private LocalDate dateService;

    @JsonProperty("dateTimeService")
    private LocalDateTime dateTimeService;

    @JsonProperty("activeOrderService")
    private Boolean activeOrderService;

    @JsonProperty("requestOrderService")
    private String requestOrderService;

    @JsonProperty("registerBookingOperator")
    private String registerBookingOperator;

    @JsonProperty("supplierOperator")
    private String supplierOperator;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("supplierGuide")
    private String supplierGuide;

    @JsonProperty("supplierTransport")
    private String supplierTransport;

    @JsonProperty("supplierLodging")
    private String supplierLodging;

    @JsonProperty("supplierLodger")
    private String supplierLodger;

    @JsonProperty("supplierGlamping")
    private String supplierGlamping;

    @JsonProperty("responsible")
    private String responsible;

    @JsonProperty("drive")
    private String drive;

    @JsonProperty("guideTransfer")
    private String guideTransfer;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("supplierType")
    private String supplierType;

    public OrderServiceDomain to(
            RequestOrderServiceDomain requestOrderService,
            RegisterBookingOperatorDomain registerBookingOperator,
            SupplierOperatorDomain supplierOperator,
            RegisterGroupDomain registerGroup,
            SupplierGuideDomain supplierGuide,
            SupplierTransportDomain supplierTransport,
            SupplierLodgingDomain supplierLodging,
            SupplierLodgerDomain supplierLodger,
            SupplierGlampingDomain supplierGlamping,
            EmployeeDomain responsible,
            GuideDomain drive,
            GuideDomain guideTransfer,
            SupplierDomain supplier,
            SupplierTypeDomain supplierType
    ) {
        OrderServiceDomain domain = new OrderServiceDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setArriveAir(arriveAir);
        domain.setDepartureAir(departureAir);
        domain.setLodging(lodging);
        domain.setCantPax(cantPax);
        domain.setObservation(observation);
        domain.setDateOrderService(dateOrderService);
        domain.setDateService(dateService);
        domain.setDateTimeService(dateTimeService);
        domain.setActiveOrderService(activeOrderService);
        domain.setOrderServiceType(orderServiceType);

        if (requestOrderService != null)
            domain.setRequestOrderService(requestOrderService);

        if (registerBookingOperator != null)
            domain.setRegisterBookingOperator(registerBookingOperator);

        if (supplierOperator != null)
            domain.setSupplierOperator(supplierOperator);

        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);

        if (supplierGuide != null)
            domain.setSupplierGuide(supplierGuide);

        if (supplierTransport != null)
            domain.setSupplierTransport(supplierTransport);

        if (supplierLodging != null)
            domain.setSupplierLodging(supplierLodging);

        if (supplierLodger != null)
            domain.setSupplierLodger(supplierLodger);

        if (supplierGlamping != null)
            domain.setSupplierGlamping(supplierGlamping);

        if (responsible != null)
            domain.setResponsible(responsible);

        if (drive != null)
            domain.setDrive(drive);

        if (guideTransfer != null)
            domain.setGuideTransfer(guideTransfer);

        if (supplier != null)
            domain.setSupplier(supplier);

        if (supplierType != null)
            domain.setSupplierType(supplierType);

        return domain;
    }
}
