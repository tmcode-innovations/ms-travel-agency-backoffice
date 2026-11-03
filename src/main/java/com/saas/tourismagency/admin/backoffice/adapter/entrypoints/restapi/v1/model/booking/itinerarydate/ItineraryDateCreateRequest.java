package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDateCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("itineraryDate")
    private LocalDate itineraryDate;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public ItineraryDateDomain to(RegisterGroupDomain registerGroup) {
        ItineraryDateDomain domain = new ItineraryDateDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setItineraryDate(itineraryDate);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }
}
