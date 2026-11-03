package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("cantDay")
    private Integer cantDay;

    @JsonProperty("withoutCost")
    private Boolean withoutCost;

    @JsonProperty("uniqueDay")
    private Boolean uniqueDay;


    public TourDomain to() {
        TourDomain tourDomain = new TourDomain();
        tourDomain.setName(name);
        tourDomain.setDescription(description);
        tourDomain.setIdentify(identify);
        tourDomain.setCantDay(cantDay);
        tourDomain.setWithoutCost(withoutCost);
        tourDomain.setUniqueDay(uniqueDay);
        return tourDomain;
    }
}
