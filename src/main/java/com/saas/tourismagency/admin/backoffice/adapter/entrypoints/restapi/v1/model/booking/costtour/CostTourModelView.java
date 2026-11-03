package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("items")
    private List<CostTourItemModelView> items;

    public static CostTourModelView from(
            String id,
            String name,
            List<CostTourItemModelView> items) {
        CostTourModelView costTourModelView = new CostTourModelView();
        costTourModelView.setId(id);
        costTourModelView.setName(name);
        costTourModelView.setItems(items);
        return costTourModelView;
    }
}
