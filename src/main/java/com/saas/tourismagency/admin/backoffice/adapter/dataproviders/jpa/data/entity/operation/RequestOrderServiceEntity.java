package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "operation_request_order_services")
public class RequestOrderServiceEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String requestOrderServiceState;

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity supplierOperator;

    private @DocumentReference(lazy = true)
    RegisterBookingOperatorEntity registerBookingOperator;

    private Boolean activeRequestOrderService;
}
