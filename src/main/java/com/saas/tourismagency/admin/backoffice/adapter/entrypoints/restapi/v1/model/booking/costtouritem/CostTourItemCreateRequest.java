package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourItemCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("pricePax")
    private Double pricePax;

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("registerGroup")
    private String registerGroup;

    @JsonProperty("tour")
    private String tour;

    @JsonProperty("costTour")
    private String costTour;

    @JsonProperty("passengerType")
    private String passengerType;

    @JsonProperty("costTourServiceItem")
    private String costTourServiceItem;

    public CostTourItemDomain to(CostTourDomain costTour, RegisterGroupDomain registerGroup, ItineraryDateDomain itineraryDate, TourDomain tour, PassengerTypeDomain passengerType, CostTourServiceItemDomain costTourServiceItem) {
        CostTourItemDomain costTourItemDomain = new CostTourItemDomain();
        costTourItemDomain.setName(name);
        costTourItemDomain.setDescription(description);
        costTourItemDomain.setIdentify(identify);
        costTourItemDomain.setCantPax(cantPax);
        costTourItemDomain.setPricePax(pricePax);
        costTourItemDomain.setItineraryDate(itineraryDate);
        if (costTour != null)
            costTourItemDomain.setCostTour(costTour);
        if (registerGroup != null)
            costTourItemDomain.setRegisterGroup(registerGroup);
        if (itineraryDate != null)
            costTourItemDomain.setItineraryDate(itineraryDate);
        if (tour != null)
            costTourItemDomain.setTour(tour);
        if (passengerType != null)
            costTourItemDomain.setPassengerType(passengerType);
        if (costTourServiceItem != null)
            costTourItemDomain.setCostTourServiceItem(costTourServiceItem);
        return costTourItemDomain;
    }

}
