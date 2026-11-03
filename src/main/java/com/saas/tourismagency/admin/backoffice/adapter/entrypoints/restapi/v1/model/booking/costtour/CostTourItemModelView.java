package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTourItemModelView {

    @JsonProperty("id")
    private String id;

    @JsonProperty("passengerType")
    private String passengerType;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    public static CostTourItemModelView from(
            String id,
            String passengerType,
            Double amount,
            String currency
            ) {
        CostTourItemModelView costTourItemModelView = new CostTourItemModelView();
        costTourItemModelView.setId(id);
        costTourItemModelView.setPassengerType(passengerType);
        costTourItemModelView.setAmount(amount);
        costTourItemModelView.setCurrency(currency);
        return costTourItemModelView;
    }
}
