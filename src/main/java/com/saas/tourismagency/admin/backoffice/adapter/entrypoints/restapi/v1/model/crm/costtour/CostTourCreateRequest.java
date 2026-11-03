package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourCreateRequest {

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

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("tour")
    private String tour;

    public CostTourDomain to(QuotationDomain quotation, ItineraryDateDomain itineraryDate, TourDomain tour) {
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setName(name);
        costTourDomain.setDescription(description);
        costTourDomain.setIdentify(identify);
        costTourDomain.setNumMaxPax(numMaxPax);
        if (quotation != null)
            costTourDomain.setQuotation(quotation);
        if (itineraryDate != null)
            costTourDomain.setItineraryDate(itineraryDate);
        if (tour != null)
            costTourDomain.setTour(tour);
        return costTourDomain;
    }

}
