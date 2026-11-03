package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryModelViewResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("hasTours")
    private Boolean hasTours;

    @JsonProperty("itemItinerary")
    private String itemItinerary;

    @JsonProperty("itineraryDate")
    private LocalDate itineraryDate;

    @JsonProperty("itineraryDateStr")
    private String itineraryDateStr;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

    @JsonProperty("itineraryDateTimeStr")
    private String itineraryDateTimeStr;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("paxChildrenTotal")
    private Integer paxChildrenTotal;

    @JsonProperty("paxAdultTotal")
    private Integer paxAdultTotal;

    @JsonProperty("paxTcTotal")
    private Integer paxTcTotal;

    @JsonProperty("paxTcLocalTotal")
    private Integer paxTcLocalTotal;

    @JsonProperty("paxGuideTotal")
    private Integer paxGuideTotal;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("priceAdult")
    private Double priceAdult;

    @JsonProperty("priceChildren")
    private Double priceChildren;

    @JsonProperty("priceTc")
    private Double priceTc;

    @JsonProperty("priceTcLocal")
    private Double priceTcLocal;

    @JsonProperty("priceGuide")
    private Double priceGuide;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("parentItineraryDate")
    private Boolean parentItineraryDate;

    @JsonProperty("subRows")
    private List<ItineraryModelViewResponse> subRows;

    public static ItineraryModelViewResponse from(
            String id,
            String name,
            Boolean hasTours,
            String itemItinerary,
            LocalDate itineraryDate,
            String itineraryDateStr,
            LocalDateTime itineraryDateTime,
            String itineraryDateTimeStr,
            SupplierTypeResponse supplierType,
            SupplierResponse supplier,
            ServiceResponse service,
            Integer paxTotal,
            Integer paxAdultTotal,
            Integer paxChildrenTotal,
            Integer paxTcTotal,
            Integer paxTcLocalTotal,
            Integer paxGuideTotal,
            Double price,
            Double priceAdult,
            Double priceChildren,
            Double priceTc,
            Double priceTcLocal,
            Double priceGuide,
            String observation,
            Boolean parentItineraryDate,
            List<ItineraryModelViewResponse> subRows) {
        ItineraryModelViewResponse itineraryModelViewResponse = new ItineraryModelViewResponse();
        itineraryModelViewResponse.setId(id);
        itineraryModelViewResponse.setName(name);
        itineraryModelViewResponse.setHasTours(hasTours);
        itineraryModelViewResponse.setItemItinerary(itemItinerary);
        itineraryModelViewResponse.setItineraryDate(itineraryDate);
        itineraryModelViewResponse.setItineraryDateStr(itineraryDateStr);
        itineraryModelViewResponse.setItineraryDateTime(itineraryDateTime);
        itineraryModelViewResponse.setItineraryDateTimeStr(itineraryDateTimeStr);
        itineraryModelViewResponse.setSupplierType(supplierType);
        itineraryModelViewResponse.setSupplier(supplier);
        itineraryModelViewResponse.setService(service);
        itineraryModelViewResponse.setPaxTotal(paxTotal);
        itineraryModelViewResponse.setPaxAdultTotal(paxAdultTotal);
        itineraryModelViewResponse.setPaxChildrenTotal(paxChildrenTotal);
        itineraryModelViewResponse.setPaxTcTotal(paxTcTotal);
        itineraryModelViewResponse.setPaxTcLocalTotal(paxTcLocalTotal);
        itineraryModelViewResponse.setPaxGuideTotal(paxGuideTotal);
        itineraryModelViewResponse.setPrice(price);
        itineraryModelViewResponse.setPriceAdult(priceAdult);
        itineraryModelViewResponse.setPriceChildren(priceChildren);
        itineraryModelViewResponse.setPriceTc(priceTc);
        itineraryModelViewResponse.setPriceTcLocal(priceTcLocal);
        itineraryModelViewResponse.setPriceGuide(priceGuide);
        itineraryModelViewResponse.setObservation(observation);
        itineraryModelViewResponse.setParentItineraryDate(parentItineraryDate);
        itineraryModelViewResponse.setSubRows(subRows);
        return itineraryModelViewResponse;
    }
}
