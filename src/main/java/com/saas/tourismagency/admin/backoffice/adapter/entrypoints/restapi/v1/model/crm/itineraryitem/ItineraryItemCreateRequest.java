package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItemCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("serviceGroup")
    private String serviceGroup;

    @JsonProperty("quotation")
    private String quotation;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

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

    @JsonProperty("itineraryDate")
    private String itineraryDate;

    @JsonProperty("itineraryDates")
    private Set<String> itineraryDates;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("service")
    private String service;

    @JsonProperty("departmentRegion")
    private String departmentRegion;

    @JsonProperty("tour")
    private String tour;

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

    @JsonProperty("lodgingCantBreakfast")
    private Integer lodgingCantBreakfast;

    @JsonProperty("lodgingCantLunch")
    private Integer lodgingCantLunch;

    @JsonProperty("lodgingCantDinner")
    private Integer lodgingCantDinner;

    @JsonProperty("lodgingCantBoxLunch")
    private Integer lodgingCantBoxLunch;

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

    @JsonProperty("lodgeCantBreakfast")
    private Integer lodgeCantBreakfast;

    @JsonProperty("lodgeCantLunch")
    private Integer lodgeCantLunch;

    @JsonProperty("lodgeCantDinner")
    private Integer lodgeCantDinner;

    @JsonProperty("lodgeCantBoxLunch")
    private Integer lodgeCantBoxLunch;

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

    @JsonProperty("lodgingForOperator")
    private String lodgingForOperator;

    @JsonProperty("lodgingForOperatorStr")
    private String lodgingForOperatorStr;

    public ItineraryItemDomain to(QuotationDomain quotation, ItineraryDateDomain itineraryDate) {
        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
        itineraryItemDomain.setName(name);
        itineraryItemDomain.setDescription(description);
        itineraryItemDomain.setIdentify(identify);
        itineraryItemDomain.setObservation(observation);
        itineraryItemDomain.setServiceGroup(serviceGroup);
        itineraryItemDomain.setItineraryDateTime(itineraryDateTime);
        itineraryItemDomain.setPaxTotal(paxTotal);
        itineraryItemDomain.setPrice(price);
        itineraryItemDomain.setPaxAdultTotal(paxAdultTotal);
        itineraryItemDomain.setPriceAdult(priceAdult);
        itineraryItemDomain.setPaxChildrenTotal(paxChildrenTotal);
        itineraryItemDomain.setPriceChildren(priceChildren);
        itineraryItemDomain.setPaxTcTotal(paxTcTotal);
        itineraryItemDomain.setPriceTc(priceTc);
        itineraryItemDomain.setPaxTcLocalTotal(paxTcLocalTotal);
        itineraryItemDomain.setPriceTcLocal(priceTcLocal);
        itineraryItemDomain.setPaxGuideTotal(paxGuideTotal);
        itineraryItemDomain.setPriceGuide(priceGuide);
        if (itineraryDate != null)
            itineraryItemDomain.setItineraryDate(itineraryDate);
        if (quotation != null)
            itineraryItemDomain.setQuotation(quotation);

        // Supplier Type Lodging

        itineraryItemDomain.setLodgingCantSwb(lodgingCantSwb);
        itineraryItemDomain.setLodgingCantMat(lodgingCantMat);
        itineraryItemDomain.setLodgingCantTwin(lodgingCantTwin);
        itineraryItemDomain.setLodgingCantTrp(lodgingCantTrp);

        itineraryItemDomain.setLodgingCantBreakfast(lodgingCantBreakfast);
        itineraryItemDomain.setLodgingCantLunch(lodgingCantLunch);
        itineraryItemDomain.setLodgingCantDinner(lodgingCantDinner);
        itineraryItemDomain.setLodgingCantBoxLunch(lodgingCantBoxLunch);

        itineraryItemDomain.setLodgingCantNight(lodgingCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcGuide(lodgingCantBreakfastTcGuide);
        itineraryItemDomain.setLodgingCantLunchTcGuide(lodgingCantLunchTcGuide);
        itineraryItemDomain.setLodgingCantDinnerTcGuide(lodgingCantDinnerTcGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcGuide(lodgingCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgingCantSwbTcGuide(lodgingCantSwbTcGuide);
        itineraryItemDomain.setLodgingCantTwinTcGuide(lodgingCantTwinTcGuide);

        itineraryItemDomain.setLodgingTcCantNight(lodgingTcCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide(lodgingCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgingCantLunchTcLocalGuide(lodgingCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuide(lodgingCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide(lodgingCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgingCantSwbTcLocalGuide(lodgingCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgingCantTwinTcLocalGuide(lodgingCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgingTcLocalCantNight(lodgingTcLocalCantNight);

        itineraryItemDomain.setLodgingCantBreakfastGuide(lodgingCantBreakfastGuide);
        itineraryItemDomain.setLodgingCantLunchGuide(lodgingCantLunchGuide);
        itineraryItemDomain.setLodgingCantDinnerGuide(lodgingCantDinnerGuide);
        itineraryItemDomain.setLodgingCantBoxLunchGuide(lodgingCantBoxLunchGuide);

        itineraryItemDomain.setLodgingCantSwbGuide(lodgingCantSwbGuide);
        itineraryItemDomain.setLodgingCantTwinGuide(lodgingCantTwinGuide);

        itineraryItemDomain.setLodgingCantNight(lodgingGuideCantNight);

        // Supplier Type Lodge

        itineraryItemDomain.setLodgeCantSwb(lodgeCantSwb);
        itineraryItemDomain.setLodgeCantMat(lodgeCantMat);
        itineraryItemDomain.setLodgeCantTwin(lodgeCantTwin);
        itineraryItemDomain.setLodgeCantTrp(lodgeCantTrp);

        itineraryItemDomain.setLodgeCantBreakfast(lodgeCantBreakfast);
        itineraryItemDomain.setLodgeCantLunch(lodgeCantLunch);
        itineraryItemDomain.setLodgeCantDinner(lodgeCantDinner);
        itineraryItemDomain.setLodgeCantBoxLunch(lodgeCantBoxLunch);

        itineraryItemDomain.setLodgeCantNight(lodgeCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcGuide(lodgeCantBreakfastTcGuide);
        itineraryItemDomain.setLodgeCantLunchTcGuide(lodgeCantLunchTcGuide);
        itineraryItemDomain.setLodgeCantDinnerTcGuide(lodgeCantDinnerTcGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcGuide(lodgeCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgeCantSwbTcGuide(lodgeCantSwbTcGuide);
        itineraryItemDomain.setLodgeCantTwinTcGuide(lodgeCantTwinTcGuide);

        itineraryItemDomain.setLodgeTcCantNight(lodgeTcCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide(lodgeCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgeCantLunchTcLocalGuide(lodgeCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuide(lodgeCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide(lodgeCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgeCantSwbTcLocalGuide(lodgeCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgeCantTwinTcLocalGuide(lodgeCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgeTcLocalCantNight(lodgeTcLocalCantNight);

        itineraryItemDomain.setLodgeCantBreakfastGuide(lodgeCantBreakfastGuide);
        itineraryItemDomain.setLodgeCantLunchGuide(lodgeCantLunchGuide);
        itineraryItemDomain.setLodgeCantDinnerGuide(lodgeCantDinnerGuide);
        itineraryItemDomain.setLodgeCantBoxLunchGuide(lodgeCantBoxLunchGuide);

        itineraryItemDomain.setLodgeCantSwbGuide(lodgeCantSwbGuide);
        itineraryItemDomain.setLodgeCantTwinGuide(lodgeCantTwinGuide);

        itineraryItemDomain.setLodgeGuideCantNight(lodgeGuideCantNight);

        itineraryItemDomain.setLodgePvt(lodgePvt);
        itineraryItemDomain.setLodgeSic(lodgeSic);

        // Supplier Type Restaurant

        itineraryItemDomain.setRestaurantCantBreakfast(restaurantCantBreakfast);
        itineraryItemDomain.setRestaurantCantLunch(restaurantCantLunch);
        itineraryItemDomain.setRestaurantCantDinner(restaurantCantDinner);
        itineraryItemDomain.setRestaurantCantBoxLunch(restaurantCantBoxLunch);

        itineraryItemDomain.setRestaurantCantBreakfastTcGuide(restaurantCantBreakfastTcGuide);
        itineraryItemDomain.setRestaurantCantLunchTcGuide(restaurantCantLunchTcGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcGuide(restaurantCantDinnerTcGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(restaurantCantBoxLunchTcGuide);

        itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(restaurantCantBreakfastTcLocalGuide);
        itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(restaurantCantLunchTcLocalGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(restaurantCantDinnerTcLocalGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(restaurantCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setRestaurantCantBreakfastGuide(restaurantCantBreakfastGuide);
        itineraryItemDomain.setRestaurantCantLunchGuide(restaurantCantLunchGuide);
        itineraryItemDomain.setRestaurantCantDinnerGuide(restaurantCantDinnerGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchGuide(restaurantCantBoxLunchGuide);


        itineraryItemDomain.setRestaurantCantBreakfastDrive(restaurantCantBreakfastDrive);
        itineraryItemDomain.setRestaurantCantLunchDrive(restaurantCantLunchDrive);
        itineraryItemDomain.setRestaurantCantDinnerDrive(restaurantCantDinnerDrive);
        itineraryItemDomain.setRestaurantCantBoxLunchDrive(restaurantCantBoxLunchDrive);

        // Supplier Type Operator

        itineraryItemDomain.setOperatorPvt(operatorPvt);
        itineraryItemDomain.setOperatorSic(operatorSic);

        itineraryItemDomain.setLodgingForOperatorStr(lodgingForOperatorStr);

        return itineraryItemDomain;
    }

    public ItineraryItemDomain to(QuotationDomain quotation, ItineraryDateDomain itineraryDate, SupplierTypeDomain supplierType, SupplierDomain supplier, ServiceDomain service, DepartmentRegionDomain departmentRegion, SupplierLodgingDomain lodgingForOperator) {
        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
        itineraryItemDomain.setName(name);
        itineraryItemDomain.setDescription(description);
        itineraryItemDomain.setIdentify(identify);
        itineraryItemDomain.setObservation(observation);
        itineraryItemDomain.setServiceGroup(serviceGroup);
        itineraryItemDomain.setItineraryDateTime(itineraryDateTime);
        itineraryItemDomain.setPaxTotal(paxTotal);
        itineraryItemDomain.setPrice(price);
        itineraryItemDomain.setPaxAdultTotal(paxAdultTotal);
        itineraryItemDomain.setPriceAdult(priceAdult);
        itineraryItemDomain.setPaxTcTotal(paxTcTotal);
        itineraryItemDomain.setPaxChildrenTotal(paxChildrenTotal);
        itineraryItemDomain.setPriceChildren(priceChildren);
        itineraryItemDomain.setPriceTc(priceTc);
        itineraryItemDomain.setPaxTcLocalTotal(paxTcLocalTotal);
        itineraryItemDomain.setPriceTcLocal(priceTcLocal);
        itineraryItemDomain.setPaxGuideTotal(paxGuideTotal);
        itineraryItemDomain.setPriceGuide(priceGuide);
        if (itineraryDate != null)
            itineraryItemDomain.setItineraryDate(itineraryDate);
        if (quotation != null)
            itineraryItemDomain.setQuotation(quotation);
        if (supplierType != null)
            itineraryItemDomain.setSupplierType(supplierType);
        if (supplier != null)
            itineraryItemDomain.setSupplier(supplier);
        if (service != null)
            itineraryItemDomain.setService(service);
        if (departmentRegion != null)
            itineraryItemDomain.setDepartmentRegion(departmentRegion);

        // Supplier Type Lodging

        itineraryItemDomain.setLodgingCantSwb(lodgingCantSwb);
        itineraryItemDomain.setLodgingCantMat(lodgingCantMat);
        itineraryItemDomain.setLodgingCantTwin(lodgingCantTwin);
        itineraryItemDomain.setLodgingCantTrp(lodgingCantTrp);

        itineraryItemDomain.setLodgingCantBreakfast(lodgingCantBreakfast);
        itineraryItemDomain.setLodgingCantLunch(lodgingCantLunch);
        itineraryItemDomain.setLodgingCantDinner(lodgingCantDinner);
        itineraryItemDomain.setLodgingCantBoxLunch(lodgingCantBoxLunch);

        itineraryItemDomain.setLodgingCantNight(lodgingCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcGuide(lodgingCantBreakfastTcGuide);
        itineraryItemDomain.setLodgingCantLunchTcGuide(lodgingCantLunchTcGuide);
        itineraryItemDomain.setLodgingCantDinnerTcGuide(lodgingCantDinnerTcGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcGuide(lodgingCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgingCantSwbTcGuide(lodgingCantSwbTcGuide);
        itineraryItemDomain.setLodgingCantTwinTcGuide(lodgingCantTwinTcGuide);

        itineraryItemDomain.setLodgingTcCantNight(lodgingTcCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide(lodgingCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgingCantLunchTcLocalGuide(lodgingCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuide(lodgingCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide(lodgingCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgingCantSwbTcLocalGuide(lodgingCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgingCantTwinTcLocalGuide(lodgingCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgingTcLocalCantNight(lodgingTcLocalCantNight);

        itineraryItemDomain.setLodgingCantBreakfastGuide(lodgingCantBreakfastGuide);
        itineraryItemDomain.setLodgingCantLunchGuide(lodgingCantLunchGuide);
        itineraryItemDomain.setLodgingCantDinnerGuide(lodgingCantDinnerGuide);
        itineraryItemDomain.setLodgingCantBoxLunchGuide(lodgingCantBoxLunchGuide);

        itineraryItemDomain.setLodgingCantSwbGuide(lodgingCantSwbGuide);
        itineraryItemDomain.setLodgingCantTwinGuide(lodgingCantTwinGuide);

        itineraryItemDomain.setLodgingCantNight(lodgingGuideCantNight);

        // Supplier Type Lodge

        itineraryItemDomain.setLodgeCantSwb(lodgeCantSwb);
        itineraryItemDomain.setLodgeCantMat(lodgeCantMat);
        itineraryItemDomain.setLodgeCantTwin(lodgeCantTwin);
        itineraryItemDomain.setLodgeCantTrp(lodgeCantTrp);

        itineraryItemDomain.setLodgeCantBreakfast(lodgeCantBreakfast);
        itineraryItemDomain.setLodgeCantLunch(lodgeCantLunch);
        itineraryItemDomain.setLodgeCantDinner(lodgeCantDinner);
        itineraryItemDomain.setLodgeCantBoxLunch(lodgeCantBoxLunch);

        itineraryItemDomain.setLodgeCantNight(lodgeCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcGuide(lodgeCantBreakfastTcGuide);
        itineraryItemDomain.setLodgeCantLunchTcGuide(lodgeCantLunchTcGuide);
        itineraryItemDomain.setLodgeCantDinnerTcGuide(lodgeCantDinnerTcGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcGuide(lodgeCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgeCantSwbTcGuide(lodgeCantSwbTcGuide);
        itineraryItemDomain.setLodgeCantTwinTcGuide(lodgeCantTwinTcGuide);

        itineraryItemDomain.setLodgeTcCantNight(lodgeTcCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide(lodgeCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgeCantLunchTcLocalGuide(lodgeCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuide(lodgeCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide(lodgeCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgeCantSwbTcLocalGuide(lodgeCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgeCantTwinTcLocalGuide(lodgeCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgeTcLocalCantNight(lodgeTcLocalCantNight);

        itineraryItemDomain.setLodgeCantBreakfastGuide(lodgeCantBreakfastGuide);
        itineraryItemDomain.setLodgeCantLunchGuide(lodgeCantLunchGuide);
        itineraryItemDomain.setLodgeCantDinnerGuide(lodgeCantDinnerGuide);
        itineraryItemDomain.setLodgeCantBoxLunchGuide(lodgeCantBoxLunchGuide);

        itineraryItemDomain.setLodgeCantSwbGuide(lodgeCantSwbGuide);
        itineraryItemDomain.setLodgeCantTwinGuide(lodgeCantTwinGuide);

        itineraryItemDomain.setLodgeGuideCantNight(lodgeGuideCantNight);

        itineraryItemDomain.setLodgePvt(lodgePvt);
        itineraryItemDomain.setLodgeSic(lodgeSic);

        // Supplier Type Restaurant

        itineraryItemDomain.setRestaurantCantBreakfast(restaurantCantBreakfast);
        itineraryItemDomain.setRestaurantCantLunch(restaurantCantLunch);
        itineraryItemDomain.setRestaurantCantDinner(restaurantCantDinner);
        itineraryItemDomain.setRestaurantCantBoxLunch(restaurantCantBoxLunch);

        itineraryItemDomain.setRestaurantCantBreakfastTcGuide(restaurantCantBreakfastTcGuide);
        itineraryItemDomain.setRestaurantCantLunchTcGuide(restaurantCantLunchTcGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcGuide(restaurantCantDinnerTcGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(restaurantCantBoxLunchTcGuide);

        itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(restaurantCantBreakfastTcLocalGuide);
        itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(restaurantCantLunchTcLocalGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(restaurantCantDinnerTcLocalGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(restaurantCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setRestaurantCantBreakfastGuide(restaurantCantBreakfastGuide);
        itineraryItemDomain.setRestaurantCantLunchGuide(restaurantCantLunchGuide);
        itineraryItemDomain.setRestaurantCantDinnerGuide(restaurantCantDinnerGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchGuide(restaurantCantBoxLunchGuide);

        itineraryItemDomain.setRestaurantCantBreakfastDrive(restaurantCantBreakfastDrive);
        itineraryItemDomain.setRestaurantCantLunchDrive(restaurantCantLunchDrive);
        itineraryItemDomain.setRestaurantCantDinnerDrive(restaurantCantDinnerDrive);
        itineraryItemDomain.setRestaurantCantBoxLunchDrive(restaurantCantBoxLunchDrive);

        // Supplier Type Operator

        itineraryItemDomain.setOperatorPvt(operatorPvt);
        itineraryItemDomain.setOperatorSic(operatorSic);

        itineraryItemDomain.setLodgingForOperatorStr(lodgingForOperatorStr);

        if (lodgingForOperator != null)
            itineraryItemDomain.setLodgingForOperator(lodgingForOperator);

        return itineraryItemDomain;
    }

    public ItineraryItemDomain to(QuotationDomain quotation, ItineraryDateDomain itineraryDate, SupplierTypeDomain supplierType, SupplierDomain supplier, ServiceDomain service, DepartmentRegionDomain departmentRegionDomain, LodgingCategoryDomain lodgingCategory, LodgingCategoryDomain lodgeCategory, SupplierLodgingDomain lodgingForOperator) {
        ItineraryItemDomain itineraryItemDomain = new ItineraryItemDomain();
        itineraryItemDomain.setName(name);
        itineraryItemDomain.setDescription(description);
        itineraryItemDomain.setIdentify(identify);
        itineraryItemDomain.setObservation(observation);
        itineraryItemDomain.setServiceGroup(serviceGroup);
        itineraryItemDomain.setItineraryDateTime(itineraryDateTime);
        itineraryItemDomain.setPaxTotal(paxTotal);
        itineraryItemDomain.setPrice(price);
        itineraryItemDomain.setPaxAdultTotal(paxAdultTotal);
        itineraryItemDomain.setPriceAdult(priceAdult);
        itineraryItemDomain.setPaxChildrenTotal(paxChildrenTotal);
        itineraryItemDomain.setPriceChildren(priceChildren);
        itineraryItemDomain.setPaxTcTotal(paxTcTotal);
        itineraryItemDomain.setPriceTc(priceTc);
        itineraryItemDomain.setPaxTcLocalTotal(paxTcLocalTotal);
        itineraryItemDomain.setPriceTcLocal(priceTcLocal);
        itineraryItemDomain.setPaxGuideTotal(paxGuideTotal);
        itineraryItemDomain.setPriceGuide(priceGuide);
        if (itineraryDate != null)
            itineraryItemDomain.setItineraryDate(itineraryDate);
        if (quotation != null)
            itineraryItemDomain.setQuotation(quotation);
        if (supplierType != null)
            itineraryItemDomain.setSupplierType(supplierType);
        if (supplier != null)
            itineraryItemDomain.setSupplier(supplier);
        if (service != null)
            itineraryItemDomain.setService(service);
        if (departmentRegionDomain != null)
            itineraryItemDomain.setDepartmentRegion(departmentRegionDomain);

        // Supplier Type Lodging

        itineraryItemDomain.setLodgingCantSwb(lodgingCantSwb);
        itineraryItemDomain.setLodgingCantMat(lodgingCantMat);
        itineraryItemDomain.setLodgingCantTwin(lodgingCantTwin);
        itineraryItemDomain.setLodgingCantTrp(lodgingCantTrp);

        itineraryItemDomain.setLodgingCantBreakfast(lodgingCantBreakfast);
        itineraryItemDomain.setLodgingCantLunch(lodgingCantLunch);
        itineraryItemDomain.setLodgingCantDinner(lodgingCantDinner);
        itineraryItemDomain.setLodgingCantBoxLunch(lodgingCantBoxLunch);

        if (lodgingCategory != null)
            itineraryItemDomain.setLodgingCategory(lodgingCategory);

        itineraryItemDomain.setLodgingCantNight(lodgingCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcGuide(lodgingCantBreakfastTcGuide);
        itineraryItemDomain.setLodgingCantLunchTcGuide(lodgingCantLunchTcGuide);
        itineraryItemDomain.setLodgingCantDinnerTcGuide(lodgingCantDinnerTcGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcGuide(lodgingCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgingCantSwbTcGuide(lodgingCantSwbTcGuide);
        itineraryItemDomain.setLodgingCantTwinTcGuide(lodgingCantTwinTcGuide);

        itineraryItemDomain.setLodgingTcCantNight(lodgingTcCantNight);

        itineraryItemDomain.setLodgingCantBreakfastTcLocalGuide(lodgingCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgingCantLunchTcLocalGuide(lodgingCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgingCantDinnerTcLocalGuide(lodgingCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuide(lodgingCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgingCantSwbTcLocalGuide(lodgingCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgingCantTwinTcLocalGuide(lodgingCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgingTcLocalCantNight(lodgingTcLocalCantNight);

        itineraryItemDomain.setLodgingCantBreakfastGuide(lodgingCantBreakfastGuide);
        itineraryItemDomain.setLodgingCantLunchGuide(lodgingCantLunchGuide);
        itineraryItemDomain.setLodgingCantDinnerGuide(lodgingCantDinnerGuide);
        itineraryItemDomain.setLodgingCantBoxLunchGuide(lodgingCantBoxLunchGuide);

        itineraryItemDomain.setLodgingCantSwbGuide(lodgingCantSwbGuide);
        itineraryItemDomain.setLodgingCantTwinGuide(lodgingCantTwinGuide);

        itineraryItemDomain.setLodgingCantNight(lodgingGuideCantNight);

        // Supplier Type Lodge

        itineraryItemDomain.setLodgeCantSwb(lodgeCantSwb);
        itineraryItemDomain.setLodgeCantMat(lodgeCantMat);
        itineraryItemDomain.setLodgeCantTwin(lodgeCantTwin);
        itineraryItemDomain.setLodgeCantTrp(lodgeCantTrp);

        itineraryItemDomain.setLodgeCantBreakfast(lodgeCantBreakfast);
        itineraryItemDomain.setLodgeCantLunch(lodgeCantLunch);
        itineraryItemDomain.setLodgeCantDinner(lodgeCantDinner);
        itineraryItemDomain.setLodgeCantBoxLunch(lodgeCantBoxLunch);

        if (lodgeCategory != null)
            itineraryItemDomain.setLodgeCategory(lodgeCategory);

        itineraryItemDomain.setLodgeCantNight(lodgeCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcGuide(lodgeCantBreakfastTcGuide);
        itineraryItemDomain.setLodgeCantLunchTcGuide(lodgeCantLunchTcGuide);
        itineraryItemDomain.setLodgeCantDinnerTcGuide(lodgeCantDinnerTcGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcGuide(lodgeCantBoxLunchTcGuide);

        itineraryItemDomain.setLodgeCantSwbTcGuide(lodgeCantSwbTcGuide);
        itineraryItemDomain.setLodgeCantTwinTcGuide(lodgeCantTwinTcGuide);

        itineraryItemDomain.setLodgeTcCantNight(lodgeTcCantNight);

        itineraryItemDomain.setLodgeCantBreakfastTcLocalGuide(lodgeCantBreakfastTcLocalGuide);
        itineraryItemDomain.setLodgeCantLunchTcLocalGuide(lodgeCantLunchTcLocalGuide);
        itineraryItemDomain.setLodgeCantDinnerTcLocalGuide(lodgeCantDinnerTcLocalGuide);
        itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuide(lodgeCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setLodgeCantSwbTcLocalGuide(lodgeCantSwbTcLocalGuide);
        itineraryItemDomain.setLodgeCantTwinTcLocalGuide(lodgeCantTwinTcLocalGuide);

        itineraryItemDomain.setLodgeTcLocalCantNight(lodgeTcLocalCantNight);

        itineraryItemDomain.setLodgeCantBreakfastGuide(lodgeCantBreakfastGuide);
        itineraryItemDomain.setLodgeCantLunchGuide(lodgeCantLunchGuide);
        itineraryItemDomain.setLodgeCantDinnerGuide(lodgeCantDinnerGuide);
        itineraryItemDomain.setLodgeCantBoxLunchGuide(lodgeCantBoxLunchGuide);

        itineraryItemDomain.setLodgeCantSwbGuide(lodgeCantSwbGuide);
        itineraryItemDomain.setLodgeCantTwinGuide(lodgeCantTwinGuide);

        itineraryItemDomain.setLodgeGuideCantNight(lodgeGuideCantNight);

        itineraryItemDomain.setLodgePvt(lodgePvt);
        itineraryItemDomain.setLodgeSic(lodgeSic);

        // Supplier Type Restaurant

        itineraryItemDomain.setRestaurantCantBreakfast(restaurantCantBreakfast);
        itineraryItemDomain.setRestaurantCantLunch(restaurantCantLunch);
        itineraryItemDomain.setRestaurantCantDinner(restaurantCantDinner);
        itineraryItemDomain.setRestaurantCantBoxLunch(restaurantCantBoxLunch);

        itineraryItemDomain.setRestaurantCantBreakfastTcGuide(restaurantCantBreakfastTcGuide);
        itineraryItemDomain.setRestaurantCantLunchTcGuide(restaurantCantLunchTcGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcGuide(restaurantCantDinnerTcGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcGuide(restaurantCantBoxLunchTcGuide);


        itineraryItemDomain.setRestaurantCantBreakfastTcLocalGuide(restaurantCantBreakfastTcLocalGuide);
        itineraryItemDomain.setRestaurantCantLunchTcLocalGuide(restaurantCantLunchTcLocalGuide);
        itineraryItemDomain.setRestaurantCantDinnerTcLocalGuide(restaurantCantDinnerTcLocalGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchTcLocalGuide(restaurantCantBoxLunchTcLocalGuide);

        itineraryItemDomain.setRestaurantCantBreakfastGuide(restaurantCantBreakfastGuide);
        itineraryItemDomain.setRestaurantCantLunchGuide(restaurantCantLunchGuide);
        itineraryItemDomain.setRestaurantCantDinnerGuide(restaurantCantDinnerGuide);
        itineraryItemDomain.setRestaurantCantBoxLunchGuide(restaurantCantBoxLunchGuide);

        itineraryItemDomain.setRestaurantCantBreakfastDrive(restaurantCantBreakfastDrive);
        itineraryItemDomain.setRestaurantCantLunchDrive(restaurantCantLunchDrive);
        itineraryItemDomain.setRestaurantCantDinnerDrive(restaurantCantDinnerDrive);
        itineraryItemDomain.setRestaurantCantBoxLunchDrive(restaurantCantBoxLunchDrive);

        // Supplier Type Operator

        itineraryItemDomain.setOperatorPvt(operatorPvt);
        itineraryItemDomain.setOperatorSic(operatorSic);

        itineraryItemDomain.setLodgingForOperatorStr(lodgingForOperatorStr);

        if (lodgingForOperator != null)
            itineraryItemDomain.setLodgingForOperator(lodgingForOperator);

        return itineraryItemDomain;
    }
}
