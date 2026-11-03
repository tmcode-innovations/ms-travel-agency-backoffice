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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking_register_booking_item_lodging_feedings")
public class RegisterBookingItemLodgingFeedingEntity extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateFeeding;
    private LocalDateTime dateTimeFeeding;
    private Integer cant;

    private String typeFeedingBooking;
    private String typePassengerBooking;

    private String typeBooking;

    // Booing Lodging

    private @DocumentReference(lazy = true)
    RegisterBookingLodgingEntity registerBookingLodging;

    private @DocumentReference(lazy = true)
    RegisterBookingItemLodgingEntity registerBookingItemLodging;

    // Booing Lodge

    private @DocumentReference(lazy = true)
    RegisterBookingLodgerEntity registerBookingLodger;

    private @DocumentReference(lazy = true)
    RegisterBookingItemLodgerLodgingEntity registerBookingItemLodgerLodging;

    // Booing Lodging

    private @DocumentReference(lazy = true)
    RegisterBookingGlampingEntity registerBookingGlamping;

    private @DocumentReference(lazy = true)
    RegisterBookingItemGlampingEntity registerBookingItemGlamping;

    // Register Group

    private @DocumentReference(lazy = true)
    RegisterGroupEntity registerGroup;

}
