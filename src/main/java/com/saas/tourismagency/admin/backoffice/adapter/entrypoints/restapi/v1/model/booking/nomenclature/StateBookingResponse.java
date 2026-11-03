package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateBookingResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static StateBookingResponse from(StateBookingEnum.StateBookingModel domain) {
        StateBookingResponse stateBookingResponse = new StateBookingResponse();
        stateBookingResponse.setId(domain.id());
        stateBookingResponse.setName(domain.name());
        stateBookingResponse.setDescription(domain.description());
        return stateBookingResponse;
    }

    public static StateBookingResponse from(StateBookingEnum.StateBookingModel domain, String generalConfigurationType) {
        StateBookingResponse stateBookingResponse = new StateBookingResponse();
        stateBookingResponse.setId(domain.id());
        stateBookingResponse.setName(domain.name());
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            stateBookingResponse.setDescription(domain.description().toUpperCase());
        }else{
            stateBookingResponse.setDescription(domain.description());
        }
        return stateBookingResponse;
    }
}
