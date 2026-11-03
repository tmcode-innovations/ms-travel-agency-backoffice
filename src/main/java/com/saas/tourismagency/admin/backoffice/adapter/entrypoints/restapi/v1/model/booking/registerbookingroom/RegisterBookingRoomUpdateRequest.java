package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingRoomUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("typeRoomBooking")
    private String typeRoomBooking;

    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    @JsonProperty("registerBookingLodging")
    private String registerBookingLodging;

    @JsonProperty("registerBookingGlamping")
    private String registerBookingGlamping;

    @JsonProperty("registerBookingLodger")
    private String registerBookingLodger;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public RegisterBookingRoomDomain to() {
        RegisterBookingRoomDomain domain = new RegisterBookingRoomDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setTypeRoomBooking(typeRoomBooking);

        return domain;
    }

    public RegisterBookingRoomDomain to(RegisterBookingLodgingDomain registerBookingLodging, RegisterBookingLodgerDomain registerBookingLodger, RegisterBookingGlampingDomain registerBookingGlamping, RegisterGroupDomain registerGroup) {
        RegisterBookingRoomDomain domain = new RegisterBookingRoomDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setTypeRoomBooking(typeRoomBooking);

        if (registerBookingLodging != null)
            domain.setRegisterBookingLodging(registerBookingLodging);
        if (registerBookingGlamping != null)
            domain.setRegisterBookingGlamping(registerBookingGlamping);
        if (registerBookingLodger != null)
            domain.setRegisterBookingLodger(registerBookingLodger);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

}
