package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerBookingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePassengerBookingResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static TypePassengerBookingResponse from(TypePassengerBookingEnum.TypePassengerBookingModel domain) {
        TypePassengerBookingResponse typePassengerBookingResponse = new TypePassengerBookingResponse();
        typePassengerBookingResponse.setId(domain.id());
        typePassengerBookingResponse.setName(domain.name());
        typePassengerBookingResponse.setDescription(domain.description());
        return typePassengerBookingResponse;
    }
}
