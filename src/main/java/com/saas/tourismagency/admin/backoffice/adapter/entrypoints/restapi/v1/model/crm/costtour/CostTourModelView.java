package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourModelView {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("itineraryDate")
    private ItineraryDateDomain itineraryDate;

    @JsonProperty("tour")
    private TourDomain tour;

    @JsonProperty("numMaxPax")
    private Integer numMaxPax;

    @JsonProperty("subTotalAmount")
    private Double subTotalAmount;

    @JsonProperty("items")
    private List<CostTourItemModelView> items;

    @JsonProperty("additionalItems")
    private List<CostTourItemModelView> additionalItems;

    public static CostTourModelView from(
            String id,
            String name,
            List<CostTourItemModelView> items,
            List<CostTourItemModelView> additionalItems) {
        CostTourModelView costTourModelView = new CostTourModelView();
        costTourModelView.setId(id);
        costTourModelView.setName(name);
        costTourModelView.setItems(items);
        costTourModelView.setAdditionalItems(additionalItems);
        return costTourModelView;
    }
}
