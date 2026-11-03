package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.ItineraryItemResponse;
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

    @JsonProperty("price")
    private Double price;

    @JsonProperty("paxAdultTotal")
    private Integer paxAdultTotal;

    @JsonProperty("priceAdult")
    private Double priceAdult;

    @JsonProperty("paxChildrenTotal")
    private Integer paxChildrenTotal;

    @JsonProperty("priceChildren")
    private Double priceChildren;

    @JsonProperty("paxTcTotal")
    private Integer paxTcTotal;

    @JsonProperty("priceTc")
    private Double priceTc;

    @JsonProperty("paxTcLocalTotal")
    private Integer paxTcLocalTotal;

    @JsonProperty("priceTcLocal")
    private Double priceTcLocal;

    @JsonProperty("paxGuideTotal")
    private Integer paxGuideTotal;

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
            Double price,
            Integer paxAdultTotal,
            Double priceAdult,
            Integer paxChildrenTotal,
            Double priceChildren,
            Integer paxTcTotal,
            Double priceTc,
            Integer paxTcLocalTotal,
            Double priceTcLocal,
            Integer paxGuideTotal,
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
        itineraryModelViewResponse.setPrice(price);
        itineraryModelViewResponse.setPaxAdultTotal(paxAdultTotal);
        itineraryModelViewResponse.setPriceAdult(priceAdult);
        itineraryModelViewResponse.setPaxChildrenTotal(paxChildrenTotal);
        itineraryModelViewResponse.setPriceChildren(priceChildren);
        itineraryModelViewResponse.setPaxTcTotal(paxTcTotal);
        itineraryModelViewResponse.setPriceTc(priceTc);
        itineraryModelViewResponse.setPaxTcLocalTotal(paxTcLocalTotal);
        itineraryModelViewResponse.setPriceTcLocal(priceTcLocal);
        itineraryModelViewResponse.setPaxGuideTotal(paxGuideTotal);
        itineraryModelViewResponse.setPriceGuide(priceGuide);
        itineraryModelViewResponse.setObservation(observation);
        itineraryModelViewResponse.setParentItineraryDate(parentItineraryDate);
        itineraryModelViewResponse.setSubRows(subRows);
        return itineraryModelViewResponse;
    }
}
