package com.saas.tourismagency.admin.backoffice.domain.operation;

import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RequestOrderServiceDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String requestOrderServiceState;

    private RegisterBookingOperatorDomain registerBookingOperator;

    private RegisterGroupDomain registerGroup;

    private SupplierOperatorDomain supplierOperator;

    private Boolean activeRequestOrderService;
}
