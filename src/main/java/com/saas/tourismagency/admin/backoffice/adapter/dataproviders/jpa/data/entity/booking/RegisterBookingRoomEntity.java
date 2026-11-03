package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_booking_rooms")
public class RegisterBookingRoomEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private String typeRoomBooking;

    // Booing Lodging

    private @DocumentReference(lazy = true)
    RegisterBookingLodgingEntity registerBookingLodging;

    // Booing Lodge

    private @DocumentReference(lazy = true)
    RegisterBookingLodgerEntity registerBookingLodger;

    // Booing Glamping

    private @DocumentReference(lazy = true)
    RegisterBookingGlampingEntity registerBookingGlamping;

    // Register Group

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

}
