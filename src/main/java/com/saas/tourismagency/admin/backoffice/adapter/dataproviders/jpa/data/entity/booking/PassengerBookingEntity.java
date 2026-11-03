package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
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
@Document(collection = "booking_passenger_bookings")
public class PassengerBookingEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String typePassengerTcBooking;
    private String typeRoomBooking;

    // Passenger Group

    private @DocumentReference(lazy = true)
    PassengerGroupEntity passengerGroup;

    // Booking

    private @DocumentReference(lazy = true)
    RegisterBookingOperatorEntity registerBookingOperator;

    private @DocumentReference(lazy = true)
    RegisterBookingRestaurantEntity registerBookingRestaurant;

    private @DocumentReference(lazy = true)
    RegisterBookingLodgingEntity registerBookingLodging;

    private @DocumentReference(lazy = true)
    RegisterBookingLodgerEntity registerBookingLodger;

    private @DocumentReference(lazy = true)
    RegisterBookingGlampingEntity registerBookingGlamping;

    private @DocumentReference(lazy = true)
    RegisterBookingInstitutionEntity registerBookingInstitution;

    // Register Group

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

    private @DocumentReference(lazy = true)
    RegisterBookingRoomEntity registerBookingRoom;

}
