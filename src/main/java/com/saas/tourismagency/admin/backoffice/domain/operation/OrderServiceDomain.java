package com.saas.tourismagency.admin.backoffice.domain.operation;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
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
public class OrderServiceDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String arriveAir;
    private String departureAir;
    private String lodging;

    private Integer cantPax;
    private String observation;
    private LocalDate dateOrderService;
    private LocalDate dateService;
    private LocalDateTime dateTimeService;

    private Boolean activeOrderService;

    private String orderServiceState;

    private String orderServiceType;

    private RequestOrderServiceDomain requestOrderService;

    private RegisterBookingOperatorDomain registerBookingOperator;

    private SupplierOperatorDomain supplierOperator;

    private RegisterGroupDomain registerGroup;

    private SupplierGuideDomain supplierGuide;

    private SupplierTransportDomain supplierTransport;

    private SupplierLodgingDomain supplierLodging;

    private SupplierLodgerDomain supplierLodger;

    private SupplierGlampingDomain supplierGlamping;

    private EmployeeDomain responsible;

    private GuideDomain drive;

    private GuideDomain guideTransfer;

    private SupplierDomain supplier;

    private SupplierTypeDomain supplierType;

    @Getter(AccessLevel.NONE)
    private String strDateOrderService;

    @Getter(AccessLevel.NONE)
    private String strDateService;

    @Getter(AccessLevel.NONE)
    private String strHourService;

    public String getStrDateOrderService(){
        return LocalDateTimeUtil.toLocalDateFormatShort(dateOrderService);
    }

    public String getStrDateService(){
        return LocalDateTimeUtil.toLocalDateFormatShort(dateService);
    }

    public String getStrHourService(){
        return LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(dateTimeService);
    }

}
