package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceItemCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateService")
    private LocalDate dateService;

    @JsonProperty("dateTimeService")
    private LocalDateTime dateTimeService;

    @JsonProperty("dateTimePax")
    private LocalDateTime dateTimePax;

    @JsonProperty("dateTimeRecj")
    private LocalDateTime dateTimeRecj;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("observationAditional")
    private String observationAditional;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("tc")
    private Integer tc;

    @JsonProperty("tcLocal")
    private Integer tcLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("lodgePvt")
    private Boolean lodgePvt;

    @JsonProperty("lodgeSic")
    private Boolean lodgeSic;

    @JsonProperty("operatorPvt")
    private Boolean operatorPvt;

    @JsonProperty("operatorSic")
    private Boolean operatorSic;

    @JsonProperty("orderService")
    private String orderService;

    @JsonProperty("requestOrderService")
    private String requestOrderService;

    @JsonProperty("registerBookingOperator")
    private String registerBookingOperator;

    @JsonProperty("registerBookingItemOperator")
    private String registerBookingItemOperator;

    @JsonProperty("service")
    private String service;

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

    @JsonProperty("drive")
    private String drive;

    @JsonProperty("guideTransfer")
    private String guideTransfer;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("orderServiceType")
    private String orderServiceType;

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    public OrderServiceItemDomain to(
            OrderServiceDomain orderService,
            RequestOrderServiceDomain requestOrderService,
            RegisterBookingOperatorDomain registerBookingOperator,
            RegisterBookingItemOperatorDomain registerBookingItemOperator,
            ServiceDomain service,
            SupplierOperatorDomain supplierOperator,
            RegisterGroupDomain registerGroup,
            SupplierGuideDomain supplierGuide,
            SupplierTransportDomain supplierTransport,
            SupplierLodgingDomain supplierLodging,
            SupplierLodgerDomain supplierLodger,
            SupplierGlampingDomain supplierGlamping,
            GuideDomain drive,
            GuideDomain guideTransfer,
            SupplierDomain supplier,
            SupplierTypeDomain supplierType,
            ItineraryDateDomain itineraryDate
    ) {
        OrderServiceItemDomain domain = new OrderServiceItemDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateService(dateService);
        domain.setDateTimeService(dateTimeService);
        domain.setDateTimePax(dateTimePax);
        domain.setDateTimeRecj(dateTimeRecj);

        domain.setObservation(observation);
        domain.setObservationAditional(observationAditional);

        domain.setCantPax(cantPax);
        domain.setCantPaxAdult(cantPaxAdult);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setTc(tc);
        domain.setTcLocal(tcLocal);
        domain.setGuide(guide);

        domain.setOperatorPvt(operatorPvt);
        domain.setOperatorSic(operatorSic);

        domain.setLodgePvt(lodgePvt);
        domain.setLodgeSic(lodgeSic);

        domain.setOrderServiceType(orderServiceType);

        if (orderService != null)
            domain.setOrderService(orderService);

        if (requestOrderService != null)
            domain.setRequestOrderService(requestOrderService);

        if (registerBookingOperator != null)
            domain.setRegisterBookingOperator(registerBookingOperator);

        if (registerBookingItemOperator != null)
            domain.setRegisterBookingItemOperator(registerBookingItemOperator);

        if (service != null)
            domain.setService(service);

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

        if (drive != null)
            domain.setDrive(drive);

        if (guideTransfer != null)
            domain.setGuideTransfer(guideTransfer);

        if (supplier != null)
            domain.setSupplier(supplier);

        if (supplierType != null)
            domain.setSupplierType(supplierType);

        if (itineraryDate != null)
            domain.setItineraryDate(itineraryDate);

        return domain;
    }
}
