package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDateCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("tour")
    private String tour;

    public TourDateDomain to(TourDomain tour) {
        TourDateDomain tourDateDomain = new TourDateDomain();
        tourDateDomain.setName(name);
        tourDateDomain.setDescription(description);
        tourDateDomain.setIdentify(identify);
        tourDateDomain.setDay(day);
        if (tour != null)
            tourDateDomain.setTour(tour);
        return tourDateDomain;
    }

}
