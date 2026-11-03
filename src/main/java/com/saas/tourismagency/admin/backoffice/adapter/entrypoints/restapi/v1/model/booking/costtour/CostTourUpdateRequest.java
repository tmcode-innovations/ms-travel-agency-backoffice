package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("numMaxPax")
    private Integer numMaxPax;

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("tour")
    private String tour;

    public CostTourDomain to(RegisterGroupDomain registerGroup, ItineraryDateDomain itineraryDate, TourDomain tour) {
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(id);
        costTourDomain.setName(name);
        costTourDomain.setDescription(description);
        costTourDomain.setIdentify(identify);
        costTourDomain.setNumMaxPax(numMaxPax);
        if (registerGroup != null)
            costTourDomain.setRegisterGroup(registerGroup);
        if (itineraryDate != null)
            costTourDomain.setItineraryDate(itineraryDate);
        if (tour != null)
            costTourDomain.setTour(tour);
        return costTourDomain;
    }

}
