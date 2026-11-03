package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerTypeCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("typePassengerTcBooking")
    private String typePassengerTcBooking;

    public PassengerTypeDomain to() {
        PassengerTypeDomain passengerTypeDomain = new PassengerTypeDomain();
        passengerTypeDomain.setName(name);
        passengerTypeDomain.setDescription(description);
        passengerTypeDomain.setIdentify(identify);
        passengerTypeDomain.setTypePassengerTcBooking(typePassengerTcBooking);
        return passengerTypeDomain;
    }
}
