package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePassengerTcBookingResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static TypePassengerTcBookingResponse from(TypePassengerTcBookingEnum.TypePassengerTcBookingModel domain) {
        TypePassengerTcBookingResponse typePassengerTcBookingResponse = new TypePassengerTcBookingResponse();
        typePassengerTcBookingResponse.setId(domain.id());
        typePassengerTcBookingResponse.setName(domain.name());
        typePassengerTcBookingResponse.setDescription(domain.description());
        return typePassengerTcBookingResponse;
    }
}
