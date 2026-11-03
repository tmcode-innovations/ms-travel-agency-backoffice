package com.saas.tourismagency.admin.backoffice.domain.booking;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PassengerBookingDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String typePassengerTcBooking;
    private String typeRoomBooking;

    // Passenger Group

    private PassengerGroupDomain passengerGroup;

    // Booking

    private RegisterBookingOperatorDomain registerBookingOperator;

    private RegisterBookingRestaurantDomain registerBookingRestaurant;

    private RegisterBookingLodgingDomain registerBookingLodging;

    private RegisterBookingGlampingDomain registerBookingGlamping;

    private RegisterBookingLodgerDomain registerBookingLodger;

    private RegisterBookingInstitutionDomain registerBookingInstitution;

    // Register Group

    private RegisterGroupDomain registerGroup;

    private RegisterBookingRoomDomain registerBookingRoom;
}
