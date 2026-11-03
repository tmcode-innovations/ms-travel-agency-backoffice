package com.saas.tourismagency.admin.backoffice.domain.operation;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OrderServiceItemDomain extends BaseDomain implements Serializable {

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

    private OrderServiceDomain orderService;

    private RequestOrderServiceDomain requestOrderService;

    private RegisterBookingOperatorDomain registerBookingOperator;

    private RegisterBookingItemOperatorDomain registerBookingItemOperator;

    private ServiceDomain service;

    private SupplierOperatorDomain supplierOperator;

    private RegisterGroupDomain registerGroup;

    private SupplierGuideDomain supplierGuide;

    private SupplierTransportDomain supplierTransport;

    private SupplierLodgingDomain supplierLodging;

    private SupplierLodgerDomain supplierLodger;

    private SupplierGlampingDomain supplierGlamping;

    private GuideDomain drive;

    private GuideDomain guideTransfer;

    private SupplierDomain supplier;

    private SupplierTypeDomain supplierType;

    private ItineraryDateDomain itineraryDate;

    @Getter(AccessLevel.NONE)
    private String strDateService;

    @Getter(AccessLevel.NONE)
    private String strHourService;

    public String getStrDateService(){
        return LocalDateTimeUtil.toLocalDateFormatShort(dateService);
    }

    public String getStrHourService(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(dateTimeService);
    }

    @Getter(AccessLevel.NONE)
    private String strDateTimeRecj;

    public String getStrDateTimeRecj(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(dateTimeRecj);
    }

    @Getter(AccessLevel.NONE)
    private String strDateTimePax;

    public String getStrDateTimePax(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(dateTimePax);
    }

}
