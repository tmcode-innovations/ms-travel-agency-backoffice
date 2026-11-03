package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerBookingUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("typePassengerTcBooking")
    private String typePassengerTcBooking;

    @JsonProperty("typeRoomBooking")
    private String typeRoomBooking;

    @JsonProperty("passengerGroup")
    private String passengerGroup;

    @JsonProperty("registerBookingOperator")
    private String registerBookingOperator;

    @JsonProperty("registerBookingRestaurant")
    private String registerBookingRestaurant;

    @JsonProperty("registerBookingLodging")
    private String registerBookingLodging;

    @JsonProperty("registerBookingGlamping")
    private String registerBookingGlamping;

    @JsonProperty("registerBookingLodger")
    private String registerBookingLodger;

    @JsonProperty("registerBookingInstitution")
    private String registerBookingInstitution;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("registerBookingRoom")
    private String registerBookingRoom;

    public PassengerBookingDomain to() {
        PassengerBookingDomain domain = new PassengerBookingDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setTypeRoomBooking(typeRoomBooking);
        domain.setTypePassengerTcBooking(typePassengerTcBooking);
        return domain;
    }

    public PassengerBookingDomain to(
            PassengerGroupDomain passengerGroup,
            RegisterBookingOperatorDomain registerBookingOperator,
            RegisterBookingRestaurantDomain registerBookingRestaurant,
            RegisterBookingLodgingDomain registerBookingLodging,
            RegisterBookingLodgerDomain registerBookingLodger,
            RegisterBookingGlampingDomain registerBookingGlamping,
            RegisterBookingInstitutionDomain registerBookingInstitution,
            RegisterGroupDomain registerGroup,
            RegisterBookingRoomDomain registerBookingRoom
    ) {
        PassengerBookingDomain domain = new PassengerBookingDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setTypeRoomBooking(typeRoomBooking);
        domain.setTypePassengerTcBooking(typePassengerTcBooking);
        if (passengerGroup != null)
            domain.setPassengerGroup(passengerGroup);
        if (registerBookingOperator != null)
            domain.setRegisterBookingOperator(registerBookingOperator);
        if (registerBookingRestaurant != null)
            domain.setRegisterBookingRestaurant(registerBookingRestaurant);
        if (registerBookingLodging != null)
            domain.setRegisterBookingLodging(registerBookingLodging);
        if (registerBookingLodger != null)
            domain.setRegisterBookingLodger(registerBookingLodger);
        if (registerBookingGlamping != null)
            domain.setRegisterBookingGlamping(registerBookingGlamping);
        if (registerBookingInstitution != null)
            domain.setRegisterBookingInstitution(registerBookingInstitution);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        if (registerBookingRoom != null)
            domain.setRegisterBookingRoom(registerBookingRoom);
        return domain;
    }

}
