package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "operation_order_service_items")
public class OrderServiceItemEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateService;
    private LocalDateTime dateTimeService;
    private LocalDateTime dateTimePax;
    private LocalDateTime dateTimeRecj;

    private String observation;
    private String observationAditional;

    private Integer cantPax;
    private Integer cantPaxAdult;
    private Integer cantPaxChildren;
    private Integer tc;
    private Integer tcLocal;
    private Integer guide;

    private Boolean lodgePvt;
    private Boolean lodgeSic;

    private Boolean operatorPvt;
    private Boolean operatorSic;

    private String orderServiceType;

    private @DocumentReference(lazy = true)
    OrderServiceEntity orderService;

    private @DocumentReference(lazy = true)
    RequestOrderServiceEntity requestOrderService;

    private @DocumentReference(lazy = true)
    RegisterBookingOperatorEntity registerBookingOperator;

    private @DocumentReference(lazy = true)
    RegisterBookingItemOperatorEntity registerBookingItemOperator;

    private @DocumentReference(lazy = true)
    ServiceEntity service;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity supplierOperator;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    SupplierGuideEntity supplierGuide;

    private @DocumentReference(lazy = true)
    SupplierTransportEntity supplierTransport;

    private @DocumentReference(lazy = true)
    SupplierLodgingEntity supplierLodging;

    private @DocumentReference(lazy = true)
    SupplierLodgerEntity supplierLodger;

    private @DocumentReference(lazy = true)
    SupplierGlampingEntity supplierGlamping;

    private @DocumentReference(lazy = true)
    GuideEntity drive;

    private @DocumentReference(lazy = true)
    GuideEntity guideTransfer;

    private @DocumentReference(lazy = true)
    SupplierEntity supplier;

    private @DocumentReference(lazy = true)
    SupplierTypeEntity supplierType;

    private @DocumentReference(lazy = true)
    ItineraryDateEntity itineraryDate;
}
