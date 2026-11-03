package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "booking_register_booking_bases")
@TypeAlias("RegisterBookingRestaurantEntityData")
public class RegisterBookingRestaurantEntity extends RegisterBookingBaseEntity implements Serializable {

    private Integer numberService;
    private LocalDate dateInitActivity;
    private LocalDate dateEndActivity;

    private LocalDateTime dateTimeInitService;

    private String noCupon;
    private String noCuponPreImpress;
    private Integer cuponTcLocal;
    private Integer cuponTc;
    private Integer cuponCantPaxChildren;
    private Integer cuponCantPaxAdult;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity operatorArrive;

    private @DocumentReference(lazy = true)
    SupplierOperatorEntity operatorDeparture;

    private @DocumentReference(lazy = true)
    SupplierLodgingEntity supplierLodging;

}
