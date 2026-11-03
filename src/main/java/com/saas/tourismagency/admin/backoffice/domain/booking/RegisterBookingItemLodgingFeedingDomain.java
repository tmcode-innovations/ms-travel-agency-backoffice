package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingItemLodgingFeedingDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateFeeding;
    private LocalDateTime dateTimeFeeding;
    private Integer cant;

    private String typeFeedingBooking;
    private String typePassengerBooking;

    private String typeBooking;

    // Booking Lodging

    private RegisterBookingLodgingDomain registerBookingLodging;

    private RegisterBookingItemLodgingDomain registerBookingItemLodging;

    // Booking Lodge

    private RegisterBookingLodgerDomain registerBookingLodger;

    private RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodging;

    // Booking Lodging

    private RegisterBookingGlampingDomain registerBookingGlamping;

    private RegisterBookingItemGlampingDomain registerBookingItemGlamping;

    // Register Group

    private RegisterGroupDomain registerGroup;
}
