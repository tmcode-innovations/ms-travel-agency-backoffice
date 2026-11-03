package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "booking_register_booking_bases")
@TypeAlias("RegisterBookingLodgingEntityData")
public class RegisterBookingLodgingEntity extends RegisterBookingBaseEntity implements Serializable {

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
