package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour;

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

    @JsonProperty("service")
    private String service;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("pricePax")
    private Double pricePax;

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

    public static CostTourItemModelView from(
            String id,
            String passengerType,
            Double amount,
            String currency,
            String service
    ) {
        CostTourItemModelView costTourItemModelView = new CostTourItemModelView();
        costTourItemModelView.setId(id);
        costTourItemModelView.setPassengerType(passengerType);
        costTourItemModelView.setAmount(amount);
        costTourItemModelView.setCurrency(currency);
        costTourItemModelView.setService(service);
        return costTourItemModelView;
    }

    public static CostTourItemModelView from(
            String id,
            String passengerType,
            Double amount,
            String currency,
            String service,
            Integer cantPax,
            Double pricePax
    ) {
        CostTourItemModelView costTourItemModelView = new CostTourItemModelView();
        costTourItemModelView.setId(id);
        costTourItemModelView.setPassengerType(passengerType);
        costTourItemModelView.setAmount(amount);
        costTourItemModelView.setCurrency(currency);
        costTourItemModelView.setService(service);
        costTourItemModelView.setCantPax(cantPax);
        costTourItemModelView.setPricePax(pricePax);
        return costTourItemModelView;
    }
}
