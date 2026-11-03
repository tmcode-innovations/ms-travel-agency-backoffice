package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemLodgingFeedingCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateFeeding")
    private LocalDate dateFeeding;

    @JsonProperty("dateTimeFeeding")
    private LocalDateTime dateTimeFeeding;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("typeFeedingBooking")
    private String typeFeedingBooking;

    @JsonProperty("typePassengerBooking")
    private String typePassengerBooking;

    @JsonProperty("typeBooking")
    private String typeBooking;

    // Booking Lodging

    @JsonProperty("registerBookingLodging")
    private String registerBookingLodging;

    @JsonProperty("registerBookingItemLodging")
    private String registerBookingItemLodging;

    // Booking Lodge

    @JsonProperty("registerBookingLodger")
    private String registerBookingLodger;

    @JsonProperty("registerBookingItemLodgerLodging")
    private String registerBookingItemLodgerLodging;

    // Booking Glamping

    @JsonProperty("registerBookingGlamping")
    private String registerBookingGlamping;

    @JsonProperty("registerBookingItemGlamping")
    private String registerBookingItemGlamping;

    // Register Group

    @JsonProperty("registerGroup")
    private String registerGroup;

    public RegisterBookingItemLodgingFeedingDomain to() {
        RegisterBookingItemLodgingFeedingDomain domain = new RegisterBookingItemLodgingFeedingDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateFeeding(dateFeeding);
        domain.setDateTimeFeeding(dateTimeFeeding);
        domain.setCant(cant);
        domain.setTypeFeedingBooking(typeFeedingBooking);
        domain.setTypePassengerBooking(typePassengerBooking);
        domain.setTypeBooking(typeBooking);

        return domain;
    }

    public RegisterBookingItemLodgingFeedingDomain toLodging(RegisterBookingItemLodgingDomain registerBookingItemLodging, RegisterBookingLodgingDomain registerBookingLodging, RegisterGroupDomain registerGroup ) {
        RegisterBookingItemLodgingFeedingDomain domain = new RegisterBookingItemLodgingFeedingDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateFeeding(dateFeeding);
        domain.setDateTimeFeeding(dateTimeFeeding);
        domain.setCant(cant);
        domain.setTypeFeedingBooking(typeFeedingBooking);
        domain.setTypePassengerBooking(typePassengerBooking);
        domain.setTypeBooking(typeBooking);

        if (registerBookingItemLodging != null)
            domain.setRegisterBookingItemLodging(registerBookingItemLodging);
        if (registerBookingLodging != null)
            domain.setRegisterBookingLodging(registerBookingLodging);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

    public RegisterBookingItemLodgingFeedingDomain toLodger(RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodging, RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterGroupDomain registerGroup ) {
        RegisterBookingItemLodgingFeedingDomain domain = new RegisterBookingItemLodgingFeedingDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateFeeding(dateFeeding);
        domain.setDateTimeFeeding(dateTimeFeeding);
        domain.setCant(cant);
        domain.setTypeFeedingBooking(typeFeedingBooking);
        domain.setTypePassengerBooking(typePassengerBooking);
        domain.setTypeBooking(typeBooking);

        if (registerBookingItemLodgerLodging != null)
            domain.setRegisterBookingItemLodgerLodging(registerBookingItemLodgerLodging);
        if (registerBookingLodgerDomain != null)
            domain.setRegisterBookingLodger(registerBookingLodgerDomain);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

    public RegisterBookingItemLodgingFeedingDomain toGlamping(RegisterBookingItemGlampingDomain registerBookingItemGlamping, RegisterBookingGlampingDomain registerBookingGlamping, RegisterGroupDomain registerGroup ) {
        RegisterBookingItemLodgingFeedingDomain domain = new RegisterBookingItemLodgingFeedingDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setDateFeeding(dateFeeding);
        domain.setDateTimeFeeding(dateTimeFeeding);
        domain.setCant(cant);
        domain.setTypeFeedingBooking(typeFeedingBooking);
        domain.setTypePassengerBooking(typePassengerBooking);
        domain.setTypeBooking(typeBooking);

        if (registerBookingItemGlamping != null)
            domain.setRegisterBookingItemGlamping(registerBookingItemGlamping);
        if (registerBookingGlamping != null)
            domain.setRegisterBookingGlamping(registerBookingGlamping);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }
}
