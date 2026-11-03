package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseCreateRequestById;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerServiceAdvancedCreateRequest {

    @JsonProperty("service")
    private BaseCreateRequestById service;

    @JsonProperty("itineraryDate")
    private BaseCreateRequestById itineraryDate;

    @JsonProperty("paxAdultTotal")
    private Integer paxAdultTotal;

    @JsonProperty("paxChildrenTotal")
    private Integer paxChildrenTotal;

    @JsonProperty("paxTcTotal")
    private Integer paxTcTotal;

    @JsonProperty("paxTcLocalTotal")
    private Integer paxTcLocalTotal;

    @JsonProperty("paxGuideTotal")
    private Integer paxGuideTotal;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

    @JsonProperty("serviceGroup")
    private String serviceGroup;

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

    // Supplier Type Lodging

    @JsonProperty("lodgingCantSwb")
    private Integer lodgingCantSwb;

    @JsonProperty("lodgingCantMat")
    private Integer lodgingCantMat;

    @JsonProperty("lodgingCantTwin")
    private Integer lodgingCantTwin;

    @JsonProperty("lodgingCantTrp")
    private Integer lodgingCantTrp;

    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    @JsonProperty("lodgingCategoryStr")
    private String lodgingCategoryStr;

    @JsonProperty("lodgingCantBreakfast")
    private Integer lodgingCantBreakfast;

    @JsonProperty("lodgingCantLunch")
    private Integer lodgingCantLunch;

    @JsonProperty("lodgingCantDinner")
    private Integer lodgingCantDinner;

    @JsonProperty("lodgingCantBoxLunch")
    private Integer lodgingCantBoxLunch;

    @JsonProperty("lodgingCantBreakfastDateTime")
    private LocalDateTime lodgingCantBreakfastDateTime;

    @JsonProperty("lodgingCantLunchDateTime")
    private LocalDateTime lodgingCantLunchDateTime;

    @JsonProperty("lodgingCantDinnerDateTime")
    private LocalDateTime lodgingCantDinnerDateTime;

    @JsonProperty("lodgingCantBoxLunchDateTime")
    private LocalDateTime lodgingCantBoxLunchDateTime;

    @JsonProperty("lodgingCantBreakfastDate")
    private LocalDate lodgingCantBreakfastDate;

    @JsonProperty("lodgingCantLunchDate")
    private LocalDate lodgingCantLunchDate;

    @JsonProperty("lodgingCantDinnerDate")
    private LocalDate lodgingCantDinnerDate;

    @JsonProperty("lodgingCantBoxLunchDate")
    private LocalDate lodgingCantBoxLunchDate;

    @JsonProperty("lodgingCantBreakfastItineraryDate")
    private String lodgingCantBreakfastItineraryDate;

    @JsonProperty("lodgingCantLunchItineraryDate")
    private String lodgingCantLunchItineraryDate;

    @JsonProperty("lodgingCantDinnerItineraryDate")
    private String lodgingCantDinnerItineraryDate;

    @JsonProperty("lodgingCantBoxLunchItineraryDate")
    private String lodgingCantBoxLunchItineraryDate;

    @JsonProperty("lodgingCantNight")
    private Integer lodgingCantNight;

    @JsonProperty("lodgingCantBreakfastTcGuide")
    private Integer lodgingCantBreakfastTcGuide;

    @JsonProperty("lodgingCantLunchTcGuide")
    private Integer lodgingCantLunchTcGuide;

    @JsonProperty("lodgingCantDinnerTcGuide")
    private Integer lodgingCantDinnerTcGuide;

    @JsonProperty("lodgingCantBoxLunchTcGuide")
    private Integer lodgingCantBoxLunchTcGuide;

    @JsonProperty("lodgingCantBreakfastTcGuideDateTime")
    private LocalDateTime lodgingCantBreakfastTcGuideDateTime;

    @JsonProperty("lodgingCantLunchTcGuideDateTime")
    private LocalDateTime lodgingCantLunchTcGuideDateTime;

    @JsonProperty("lodgingCantDinnerTcGuideDateTime")
    private LocalDateTime lodgingCantDinnerTcGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchTcGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchTcGuideDateTime;

    @JsonProperty("lodgingCantBreakfastTcGuideDate")
    private LocalDate lodgingCantBreakfastTcGuideDate;

    @JsonProperty("lodgingCantLunchTcGuideDate")
    private LocalDate lodgingCantLunchTcGuideDate;

    @JsonProperty("lodgingCantDinnerTcGuideDate")
    private LocalDate lodgingCantDinnerTcGuideDate;

    @JsonProperty("lodgingCantBoxLunchTcGuideDate")
    private LocalDate lodgingCantBoxLunchTcGuideDate;

    @JsonProperty("lodgingCantBreakfastTcGuideItineraryDate")
    private String lodgingCantBreakfastTcGuideItineraryDate;

    @JsonProperty("lodgingCantLunchTcGuideItineraryDate")
    private String lodgingCantLunchTcGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerTcGuideItineraryDate")
    private String lodgingCantDinnerTcGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchTcGuideItineraryDate")
    private String lodgingCantBoxLunchTcGuideItineraryDate;

    @JsonProperty("lodgingCantSwbTcGuide")
    private Integer lodgingCantSwbTcGuide;

    @JsonProperty("lodgingCantTwinTcGuide")
    private Integer lodgingCantTwinTcGuide;

    @JsonProperty("lodgingTcCantNight")
    private Integer lodgingTcCantNight;

    @JsonProperty("lodgingCantBreakfastTcLocalGuide")
    private Integer lodgingCantBreakfastTcLocalGuide;

    @JsonProperty("lodgingCantLunchTcLocalGuide")
    private Integer lodgingCantLunchTcLocalGuide;

    @JsonProperty("lodgingCantDinnerTcLocalGuide")
    private Integer lodgingCantDinnerTcLocalGuide;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuide")
    private Integer lodgingCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideDateTime")
    private LocalDateTime lodgingCantBreakfastTcLocalGuideDateTime;

    @JsonProperty("lodgingCantLunchTcLocalGuideDateTime")
    private LocalDateTime lodgingCantLunchTcLocalGuideDateTime;

    @JsonProperty("lodgingCantDinnerTcLocalGuideDateTime")
    private LocalDateTime lodgingCantDinnerTcLocalGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchTcLocalGuideDateTime;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideDate")
    private LocalDate lodgingCantBreakfastTcLocalGuideDate;

    @JsonProperty("lodgingCantLunchTcLocalGuideDate")
    private LocalDate lodgingCantLunchTcLocalGuideDate;

    @JsonProperty("lodgingCantDinnerTcLocalGuideDate")
    private LocalDate lodgingCantDinnerTcLocalGuideDate;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideDate")
    private LocalDate lodgingCantBoxLunchTcLocalGuideDate;

    @JsonProperty("lodgingCantBreakfastTcLocalGuideItineraryDate")
    private String lodgingCantBreakfastTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantLunchTcLocalGuideItineraryDate")
    private String lodgingCantLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerTcLocalGuideItineraryDate")
    private String lodgingCantDinnerTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuideItineraryDate")
    private String lodgingCantBoxLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgingCantSwbTcLocalGuide")
    private Integer lodgingCantSwbTcLocalGuide;

    @JsonProperty("lodgingCantTwinTcLocalGuide")
    private Integer lodgingCantTwinTcLocalGuide;

    @JsonProperty("lodgingTcLocalCantNight")
    private Integer lodgingTcLocalCantNight;

    @JsonProperty("lodgingCantBreakfastGuide")
    private Integer lodgingCantBreakfastGuide;

    @JsonProperty("lodgingCantLunchGuide")
    private Integer lodgingCantLunchGuide;

    @JsonProperty("lodgingCantDinnerGuide")
    private Integer lodgingCantDinnerGuide;

    @JsonProperty("lodgingCantBoxLunchGuide")
    private Integer lodgingCantBoxLunchGuide;

    @JsonProperty("lodgingCantBreakfastGuideDateTime")
    private LocalDateTime lodgingCantBreakfastGuideDateTime;

    @JsonProperty("lodgingCantLunchGuideDateTime")
    private LocalDateTime lodgingCantLunchGuideDateTime;

    @JsonProperty("lodgingCantDinnerGuideDateTime")
    private LocalDateTime lodgingCantDinnerGuideDateTime;

    @JsonProperty("lodgingCantBoxLunchGuideDateTime")
    private LocalDateTime lodgingCantBoxLunchGuideDateTime;

    @JsonProperty("lodgingCantBreakfastGuideDate")
    private LocalDate lodgingCantBreakfastGuideDate;

    @JsonProperty("lodgingCantLunchGuideDate")
    private LocalDate lodgingCantLunchGuideDate;

    @JsonProperty("lodgingCantDinnerGuideDate")
    private LocalDate lodgingCantDinnerGuideDate;

    @JsonProperty("lodgingCantBoxLunchGuideDate")
    private LocalDate lodgingCantBoxLunchGuideDate;

    @JsonProperty("lodgingCantBreakfastGuideItineraryDate")
    private String lodgingCantBreakfastGuideItineraryDate;

    @JsonProperty("lodgingCantLunchGuideItineraryDate")
    private String lodgingCantLunchGuideItineraryDate;

    @JsonProperty("lodgingCantDinnerGuideItineraryDate")
    private String lodgingCantDinnerGuideItineraryDate;

    @JsonProperty("lodgingCantBoxLunchGuideItineraryDate")
    private String lodgingCantBoxLunchGuideItineraryDate;

    @JsonProperty("lodgingCantSwbGuide")
    private Integer lodgingCantSwbGuide;

    @JsonProperty("lodgingCantTwinGuide")
    private Integer lodgingCantTwinGuide;

    @JsonProperty("lodgingGuideCantNight")
    private Integer lodgingGuideCantNight;

    // Supplier Type Lodge

    @JsonProperty("lodgeCantSwb")
    private Integer lodgeCantSwb;

    @JsonProperty("lodgeCantMat")
    private Integer lodgeCantMat;

    @JsonProperty("lodgeCantTwin")
    private Integer lodgeCantTwin;

    @JsonProperty("lodgeCantTrp")
    private Integer lodgeCantTrp;

    @JsonProperty("lodgeCategory")
    private String lodgeCategory;

    @JsonProperty("lodgeCategoryStr")
    private String lodgeCategoryStr;

    @JsonProperty("lodgeCantBreakfast")
    private Integer lodgeCantBreakfast;

    @JsonProperty("lodgeCantLunch")
    private Integer lodgeCantLunch;

    @JsonProperty("lodgeCantDinner")
    private Integer lodgeCantDinner;

    @JsonProperty("lodgeCantBoxLunch")
    private Integer lodgeCantBoxLunch;

    @JsonProperty("lodgeCantBreakfastDateTime")
    private LocalDateTime lodgeCantBreakfastDateTime;

    @JsonProperty("lodgeCantLunchDateTime")
    private LocalDateTime lodgeCantLunchDateTime;

    @JsonProperty("lodgeCantDinnerDateTime")
    private LocalDateTime lodgeCantDinnerDateTime;

    @JsonProperty("lodgeCantBoxLunchDateTime")
    private LocalDateTime lodgeCantBoxLunchDateTime;

    @JsonProperty("lodgeCantBreakfastDate")
    private LocalDate lodgeCantBreakfastDate;

    @JsonProperty("lodgeCantLunchDate")
    private LocalDate lodgeCantLunchDate;

    @JsonProperty("lodgeCantDinnerDate")
    private LocalDate lodgeCantDinnerDate;

    @JsonProperty("lodgeCantBoxLunchDate")
    private LocalDate lodgeCantBoxLunchDate;

    @JsonProperty("lodgeCantBreakfastItineraryDate")
    private String lodgeCantBreakfastItineraryDate;

    @JsonProperty("lodgeCantLunchItineraryDate")
    private String lodgeCantLunchItineraryDate;

    @JsonProperty("lodgeCantDinnerItineraryDate")
    private String lodgeCantDinnerItineraryDate;

    @JsonProperty("lodgeCantBoxLunchItineraryDate")
    private String lodgeCantBoxLunchItineraryDate;

    @JsonProperty("lodgeCantNight")
    private Integer lodgeCantNight;

    @JsonProperty("lodgeCantBreakfastTcGuide")
    private Integer lodgeCantBreakfastTcGuide;

    @JsonProperty("lodgeCantLunchTcGuide")
    private Integer lodgeCantLunchTcGuide;

    @JsonProperty("lodgeCantDinnerTcGuide")
    private Integer lodgeCantDinnerTcGuide;

    @JsonProperty("lodgeCantBoxLunchTcGuide")
    private Integer lodgeCantBoxLunchTcGuide;

    @JsonProperty("lodgeCantBreakfastTcGuideDateTime")
    private LocalDateTime lodgeCantBreakfastTcGuideDateTime;

    @JsonProperty("lodgeCantLunchTcGuideDateTime")
    private LocalDateTime lodgeCantLunchTcGuideDateTime;

    @JsonProperty("lodgeCantDinnerTcGuideDateTime")
    private LocalDateTime lodgeCantDinnerTcGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchTcGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchTcGuideDateTime;

    @JsonProperty("lodgeCantBreakfastTcGuideDate")
    private LocalDate lodgeCantBreakfastTcGuideDate;

    @JsonProperty("lodgeCantLunchTcGuideDate")
    private LocalDate lodgeCantLunchTcGuideDate;

    @JsonProperty("lodgeCantDinnerTcGuideDate")
    private LocalDate lodgeCantDinnerTcGuideDate;

    @JsonProperty("lodgeCantBoxLunchTcGuideDate")
    private LocalDate lodgeCantBoxLunchTcGuideDate;

    @JsonProperty("lodgeCantBreakfastTcGuideItineraryDate")
    private String lodgeCantBreakfastTcGuideItineraryDate;

    @JsonProperty("lodgeCantLunchTcGuideItineraryDate")
    private String lodgeCantLunchTcGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerTcGuideItineraryDate")
    private String lodgeCantDinnerTcGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchTcGuideItineraryDate")
    private String lodgeCantBoxLunchTcGuideItineraryDate;

    @JsonProperty("lodgeCantSwbTcGuide")
    private Integer lodgeCantSwbTcGuide;

    @JsonProperty("lodgeCantTwinTcGuide")
    private Integer lodgeCantTwinTcGuide;

    @JsonProperty("lodgeTcCantNight")
    private Integer lodgeTcCantNight;

    @JsonProperty("lodgeCantBreakfastTcLocalGuide")
    private Integer lodgeCantBreakfastTcLocalGuide;

    @JsonProperty("lodgeCantLunchTcLocalGuide")
    private Integer lodgeCantLunchTcLocalGuide;

    @JsonProperty("lodgeCantDinnerTcLocalGuide")
    private Integer lodgeCantDinnerTcLocalGuide;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuide")
    private Integer lodgeCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideDateTime")
    private LocalDateTime lodgeCantBreakfastTcLocalGuideDateTime;

    @JsonProperty("lodgeCantLunchTcLocalGuideDateTime")
    private LocalDateTime lodgeCantLunchTcLocalGuideDateTime;

    @JsonProperty("lodgeCantDinnerTcLocalGuideDateTime")
    private LocalDateTime lodgeCantDinnerTcLocalGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchTcLocalGuideDateTime;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideDate")
    private LocalDate lodgeCantBreakfastTcLocalGuideDate;

    @JsonProperty("lodgeCantLunchTcLocalGuideDate")
    private LocalDate lodgeCantLunchTcLocalGuideDate;

    @JsonProperty("lodgeCantDinnerTcLocalGuideDate")
    private LocalDate lodgeCantDinnerTcLocalGuideDate;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideDate")
    private LocalDate lodgeCantBoxLunchTcLocalGuideDate;

    @JsonProperty("lodgeCantBreakfastTcLocalGuideItineraryDate")
    private String lodgeCantBreakfastTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantLunchTcLocalGuideItineraryDate")
    private String lodgeCantLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerTcLocalGuideItineraryDate")
    private String lodgeCantDinnerTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuideItineraryDate")
    private String lodgeCantBoxLunchTcLocalGuideItineraryDate;

    @JsonProperty("lodgeCantSwbTcLocalGuide")
    private Integer lodgeCantSwbTcLocalGuide;

    @JsonProperty("lodgeCantTwinTcLocalGuide")
    private Integer lodgeCantTwinTcLocalGuide;

    @JsonProperty("lodgeTcLocalCantNight")
    private Integer lodgeTcLocalCantNight;

    @JsonProperty("lodgeCantBreakfastGuide")
    private Integer lodgeCantBreakfastGuide;

    @JsonProperty("lodgeCantLunchGuide")
    private Integer lodgeCantLunchGuide;

    @JsonProperty("lodgeCantDinnerGuide")
    private Integer lodgeCantDinnerGuide;

    @JsonProperty("lodgeCantBoxLunchGuide")
    private Integer lodgeCantBoxLunchGuide;

    @JsonProperty("lodgeCantBreakfastGuideDateTime")
    private LocalDateTime lodgeCantBreakfastGuideDateTime;

    @JsonProperty("lodgeCantLunchGuideDateTime")
    private LocalDateTime lodgeCantLunchGuideDateTime;

    @JsonProperty("lodgeCantDinnerGuideDateTime")
    private LocalDateTime lodgeCantDinnerGuideDateTime;

    @JsonProperty("lodgeCantBoxLunchGuideDateTime")
    private LocalDateTime lodgeCantBoxLunchGuideDateTime;

    @JsonProperty("lodgeCantBreakfastGuideDate")
    private LocalDate lodgeCantBreakfastGuideDate;

    @JsonProperty("lodgeCantLunchGuideDate")
    private LocalDate lodgeCantLunchGuideDate;

    @JsonProperty("lodgeCantDinnerGuideDate")
    private LocalDate lodgeCantDinnerGuideDate;

    @JsonProperty("lodgeCantBoxLunchGuideDate")
    private LocalDate lodgeCantBoxLunchGuideDate;

    @JsonProperty("lodgeCantBreakfastGuideItineraryDate")
    private String lodgeCantBreakfastGuideItineraryDate;

    @JsonProperty("lodgeCantLunchGuideItineraryDate")
    private String lodgeCantLunchGuideItineraryDate;

    @JsonProperty("lodgeCantDinnerGuideItineraryDate")
    private String lodgeCantDinnerGuideItineraryDate;

    @JsonProperty("lodgeCantBoxLunchGuideItineraryDate")
    private String lodgeCantBoxLunchGuideItineraryDate;

    @JsonProperty("lodgeCantSwbGuide")
    private Integer lodgeCantSwbGuide;

    @JsonProperty("lodgeCantTwinGuide")
    private Integer lodgeCantTwinGuide;

    @JsonProperty("lodgeGuideCantNight")
    private Integer lodgeGuideCantNight;

    @JsonProperty("lodgePvt")
    private Boolean lodgePvt;

    @JsonProperty("lodgeSic")
    private Boolean lodgeSic;

    // Supplier Type Restaurant

    @JsonProperty("restaurantCantBreakfast")
    private Integer restaurantCantBreakfast;

    @JsonProperty("restaurantCantLunch")
    private Integer restaurantCantLunch;

    @JsonProperty("restaurantCantDinner")
    private Integer restaurantCantDinner;

    @JsonProperty("restaurantCantBoxLunch")
    private Integer restaurantCantBoxLunch;

    @JsonProperty("restaurantCantBreakfastTcGuide")
    private Integer restaurantCantBreakfastTcGuide;

    @JsonProperty("restaurantCantLunchTcGuide")
    private Integer restaurantCantLunchTcGuide;

    @JsonProperty("restaurantCantDinnerTcGuide")
    private Integer restaurantCantDinnerTcGuide;

    @JsonProperty("restaurantCantBoxLunchTcGuide")
    private Integer restaurantCantBoxLunchTcGuide;

    @JsonProperty("restaurantCantBreakfastTcLocalGuide")
    private Integer restaurantCantBreakfastTcLocalGuide;

    @JsonProperty("restaurantCantLunchTcLocalGuide")
    private Integer restaurantCantLunchTcLocalGuide;

    @JsonProperty("restaurantCantDinnerTcLocalGuide")
    private Integer restaurantCantDinnerTcLocalGuide;

    @JsonProperty("restaurantCantBoxLunchTcLocalGuide")
    private Integer restaurantCantBoxLunchTcLocalGuide;


    @JsonProperty("restaurantCantBreakfastGuide")
    private Integer restaurantCantBreakfastGuide;

    @JsonProperty("restaurantCantLunchGuide")
    private Integer restaurantCantLunchGuide;

    @JsonProperty("restaurantCantDinnerGuide")
    private Integer restaurantCantDinnerGuide;

    @JsonProperty("restaurantCantBoxLunchGuide")
    private Integer restaurantCantBoxLunchGuide;


    @JsonProperty("restaurantCantBreakfastDrive")
    private Integer restaurantCantBreakfastDrive;

    @JsonProperty("restaurantCantLunchDrive")
    private Integer restaurantCantLunchDrive;

    @JsonProperty("restaurantCantDinnerDrive")
    private Integer restaurantCantDinnerDrive;

    @JsonProperty("restaurantCantBoxLunchDrive")
    private Integer restaurantCantBoxLunchDrive;

    // Supplier Type Operator

    @JsonProperty("operatorPvt")
    private Boolean operatorPvt;

    @JsonProperty("operatorSic")
    private Boolean operatorSic;

}
