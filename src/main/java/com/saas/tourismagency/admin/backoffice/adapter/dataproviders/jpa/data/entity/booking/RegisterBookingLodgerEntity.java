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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "booking_register_booking_bases")
@TypeAlias("RegisterBookingLodgerEntityData")
public class RegisterBookingLodgerEntity extends RegisterBookingBaseEntity implements Serializable {

    // Data Operator

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

    // Data Lodging

    private LocalDate initTour;
    private LocalDate endTour;

    private LocalDateTime initHourTour;
    private LocalDateTime endHourTour;

    private Integer cantSwb;
    private Integer cantSwbTcGuide;
    private Integer cantSwbTcLocalGuide;
    private Integer cantSwbGuide;
    private Integer cantTrp;
    private Integer cantCdp;
    private Integer cantQtp;
    private Integer cantSxtp;
    private Integer cantTwin;
    private Integer cantTwinTcGuide;
    private Integer cantTwinTcLocalGuide;
    private Integer cantTwinGuide;
    private Integer cantMat;
    private Integer numberIngress;

}
