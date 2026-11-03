package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramItemCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("program")
    private String program;

    @JsonProperty("programDate")
    private String programDate;

    @JsonProperty("programDates")
    private Set<String> programDates;

    @JsonProperty("supplierType")
    private String supplierType;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("service")
    private String service;

    @JsonProperty("programDateTime")
    private LocalDateTime programDateTime;

    @JsonProperty("pvt")
    private Boolean pvt;

    @JsonProperty("sic")
    private Boolean sic;

    @JsonProperty("serviceGroup")
    private String serviceGroup;

    // Supplier Type Lodging

    @JsonProperty("lodgingCantSwb")
    private Integer lodgingCantSwb;

    @JsonProperty("lodgingCantMat")
    private Integer lodgingCantMat;

    @JsonProperty("lodgingCantTwin")
    private Integer lodgingCantTwin;

    @JsonProperty("lodgingCantTrp")
    private Integer lodgingCantTrp;

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

    @JsonProperty("lodgingCantSwbTcGuide")
    private Integer lodgingCantSwbTcGuide;

    @JsonProperty("lodgingCantTwinTcGuide")
    private Integer lodgingCantTwinTcGuide;

    @JsonProperty("lodgingCantBreakfastTcGuide")
    private Integer lodgingCantBreakfastTcGuide;

    @JsonProperty("lodgingCantLunchTcGuide")
    private Integer lodgingCantLunchTcGuide;

    @JsonProperty("lodgingCantDinnerTcGuide")
    private Integer lodgingCantDinnerTcGuide;

    @JsonProperty("lodgingCantBoxLunchTcGuide")
    private Integer lodgingCantBoxLunchTcGuide;

    @JsonProperty("lodgingCantSwbTcLocalGuide")
    private Integer lodgingCantSwbTcLocalGuide;

    @JsonProperty("lodgingCantTwinTcLocalGuide")
    private Integer lodgingCantTwinTcLocalGuide;

    @JsonProperty("lodgingCantBreakfastTcLocalGuide")
    private Integer lodgingCantBreakfastTcLocalGuide;

    @JsonProperty("lodgingCantLunchTcLocalGuide")
    private Integer lodgingCantLunchTcLocalGuide;

    @JsonProperty("lodgingCantDinnerTcLocalGuide")
    private Integer lodgingCantDinnerTcLocalGuide;

    @JsonProperty("lodgingCantBoxLunchTcLocalGuide")
    private Integer lodgingCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgingCantSwbGuide")
    private Integer lodgingCantSwbGuide;

    @JsonProperty("lodgingCantTwinGuide")
    private Integer lodgingCantTwinGuide;

    @JsonProperty("lodgingCantBreakfastGuide")
    private Integer lodgingCantBreakfastGuide;

    @JsonProperty("lodgingCantLunchGuide")
    private Integer lodgingCantLunchGuide;

    @JsonProperty("lodgingCantDinnerGuide")
    private Integer lodgingCantDinnerGuide;

    @JsonProperty("lodgingCantBoxLunchGuide")
    private Integer lodgingCantBoxLunchGuide;

    // Supplier Type Lodge

    @JsonProperty("lodgeCantSwb")
    private Integer lodgeCantSwb;

    @JsonProperty("lodgeCantMat")
    private Integer lodgeCantMat;

    @JsonProperty("lodgeCantTwin")
    private Integer lodgeCantTwin;

    @JsonProperty("lodgeCantTrp")
    private Integer lodgeCantTrp;

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

    @JsonProperty("lodgeCantSwbTcGuide")
    private Integer lodgeCantSwbTcGuide;

    @JsonProperty("lodgeCantTwinTcGuide")
    private Integer lodgeCantTwinTcGuide;

    @JsonProperty("lodgeCantBreakfastTcGuide")
    private Integer lodgeCantBreakfastTcGuide;

    @JsonProperty("lodgeCantLunchTcGuide")
    private Integer lodgeCantLunchTcGuide;

    @JsonProperty("lodgeCantDinnerTcGuide")
    private Integer lodgeCantDinnerTcGuide;

    @JsonProperty("lodgeCantBoxLunchTcGuide")
    private Integer lodgeCantBoxLunchTcGuide;

    @JsonProperty("lodgeCantSwbTcLocalGuide")
    private Integer lodgeCantSwbTcLocalGuide;

    @JsonProperty("lodgeCantTwinTcLocalGuide")
    private Integer lodgeCantTwinTcLocalGuide;

    @JsonProperty("lodgeCantBreakfastTcLocalGuide")
    private Integer lodgeCantBreakfastTcLocalGuide;

    @JsonProperty("lodgeCantLunchTcLocalGuide")
    private Integer lodgeCantLunchTcLocalGuide;

    @JsonProperty("lodgeCantDinnerTcLocalGuide")
    private Integer lodgeCantDinnerTcLocalGuide;

    @JsonProperty("lodgeCantBoxLunchTcLocalGuide")
    private Integer lodgeCantBoxLunchTcLocalGuide;

    @JsonProperty("lodgeCantSwbGuide")
    private Integer lodgeCantSwbGuide;

    @JsonProperty("lodgeCantTwinGuide")
    private Integer lodgeCantTwinGuide;

    @JsonProperty("lodgeCantBreakfastGuide")
    private Integer lodgeCantBreakfastGuide;

    @JsonProperty("lodgeCantLunchGuide")
    private Integer lodgeCantLunchGuide;

    @JsonProperty("lodgeCantDinnerGuide")
    private Integer lodgeCantDinnerGuide;

    @JsonProperty("lodgeCantBoxLunchGuide")
    private Integer lodgeCantBoxLunchGuide;


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


    public ProgramItemDomain to(ProgramDomain program, ProgramDateDomain programDate) {
        ProgramItemDomain programItemDomain = new ProgramItemDomain();
        programItemDomain.setName(name);
        programItemDomain.setDescription(description);
        programItemDomain.setIdentify(identify);
        programItemDomain.setObservation(observation);
        programItemDomain.setProgramDateTime(programDateTime);
        programItemDomain.setPvt(pvt);
        programItemDomain.setSic(sic);
        programItemDomain.setServiceGroup(serviceGroup);

        // Supplier Type Lodging

        programItemDomain.setLodgingCantSwb(lodgingCantSwb);
        programItemDomain.setLodgingCantMat(lodgingCantMat);
        programItemDomain.setLodgingCantTwin(lodgingCantTwin);
        programItemDomain.setLodgingCantTrp(lodgingCantTrp);

        programItemDomain.setLodgingCategoryStr(lodgingCategoryStr);

        programItemDomain.setLodgingCantBreakfast(lodgingCantBreakfast);
        programItemDomain.setLodgingCantLunch(lodgingCantLunch);
        programItemDomain.setLodgingCantDinner(lodgingCantDinner);
        programItemDomain.setLodgingCantBoxLunch(lodgingCantBoxLunch);

        programItemDomain.setLodgingCantSwbTcGuide(lodgingCantSwbTcGuide);
        programItemDomain.setLodgingCantTwinTcGuide(lodgingCantTwinTcGuide);

        programItemDomain.setLodgingCantBreakfastTcGuide(lodgingCantBreakfastTcGuide);
        programItemDomain.setLodgingCantLunchTcGuide(lodgingCantLunchTcGuide);
        programItemDomain.setLodgingCantDinnerTcGuide(lodgingCantDinnerTcGuide);
        programItemDomain.setLodgingCantBoxLunchTcGuide(lodgingCantBoxLunchTcGuide);

        programItemDomain.setLodgingCantSwbTcLocalGuide(lodgingCantSwbTcLocalGuide);
        programItemDomain.setLodgingCantTwinTcLocalGuide(lodgingCantTwinTcLocalGuide);

        programItemDomain.setLodgingCantBreakfastTcLocalGuide(lodgingCantBreakfastTcLocalGuide);
        programItemDomain.setLodgingCantLunchTcLocalGuide(lodgingCantLunchTcLocalGuide);
        programItemDomain.setLodgingCantDinnerTcLocalGuide(lodgingCantDinnerTcLocalGuide);
        programItemDomain.setLodgingCantBoxLunchTcLocalGuide(lodgingCantBoxLunchTcLocalGuide);

        programItemDomain.setLodgingCantSwbGuide(lodgingCantSwbGuide);
        programItemDomain.setLodgingCantTwinGuide(lodgingCantTwinGuide);

        programItemDomain.setLodgingCantBreakfastGuide(lodgingCantBreakfastGuide);
        programItemDomain.setLodgingCantLunchGuide(lodgingCantLunchGuide);
        programItemDomain.setLodgingCantDinnerGuide(lodgingCantDinnerGuide);
        programItemDomain.setLodgingCantBoxLunchGuide(lodgingCantBoxLunchGuide);

        // Supplier Type Lodge

        programItemDomain.setLodgeCantSwb(lodgeCantSwb);
        programItemDomain.setLodgeCantMat(lodgeCantMat);
        programItemDomain.setLodgeCantTwin(lodgeCantTwin);
        programItemDomain.setLodgeCantTrp(lodgeCantTrp);

        programItemDomain.setLodgeCategoryStr(lodgeCategoryStr);

        programItemDomain.setLodgeCantBreakfast(lodgeCantBreakfast);
        programItemDomain.setLodgeCantLunch(lodgeCantLunch);
        programItemDomain.setLodgeCantDinner(lodgeCantDinner);
        programItemDomain.setLodgeCantBoxLunch(lodgeCantBoxLunch);

        programItemDomain.setLodgeCantSwbTcGuide(lodgeCantSwbTcGuide);
        programItemDomain.setLodgeCantTwinTcGuide(lodgeCantTwinTcGuide);

        programItemDomain.setLodgeCantBreakfastTcGuide(lodgeCantBreakfastTcGuide);
        programItemDomain.setLodgeCantLunchTcGuide(lodgeCantLunchTcGuide);
        programItemDomain.setLodgeCantDinnerTcGuide(lodgeCantDinnerTcGuide);
        programItemDomain.setLodgeCantBoxLunchTcGuide(lodgeCantBoxLunchTcGuide);

        programItemDomain.setLodgeCantSwbTcLocalGuide(lodgeCantSwbTcLocalGuide);
        programItemDomain.setLodgeCantTwinTcLocalGuide(lodgeCantTwinTcLocalGuide);

        programItemDomain.setLodgeCantBreakfastTcLocalGuide(lodgeCantBreakfastTcLocalGuide);
        programItemDomain.setLodgeCantLunchTcLocalGuide(lodgeCantLunchTcLocalGuide);
        programItemDomain.setLodgeCantDinnerTcLocalGuide(lodgeCantDinnerTcLocalGuide);
        programItemDomain.setLodgeCantBoxLunchTcLocalGuide(lodgeCantBoxLunchTcLocalGuide);

        programItemDomain.setLodgeCantSwbGuide(lodgeCantSwbGuide);
        programItemDomain.setLodgeCantTwinGuide(lodgeCantTwinGuide);

        programItemDomain.setLodgeCantBreakfastGuide(lodgeCantBreakfastGuide);
        programItemDomain.setLodgeCantLunchGuide(lodgeCantLunchGuide);
        programItemDomain.setLodgeCantDinnerGuide(lodgeCantDinnerGuide);
        programItemDomain.setLodgeCantBoxLunchGuide(lodgeCantBoxLunchGuide);

        // Supplier Type Restaurant

        programItemDomain.setRestaurantCantBreakfast(restaurantCantBreakfast);
        programItemDomain.setRestaurantCantLunch(restaurantCantLunch);
        programItemDomain.setRestaurantCantDinner(restaurantCantDinner);
        programItemDomain.setRestaurantCantBoxLunch(restaurantCantBoxLunch);

        programItemDomain.setRestaurantCantBreakfastTcGuide(restaurantCantBreakfastTcGuide);
        programItemDomain.setRestaurantCantLunchTcGuide(restaurantCantLunchTcGuide);
        programItemDomain.setRestaurantCantDinnerTcGuide(restaurantCantDinnerTcGuide);
        programItemDomain.setRestaurantCantBoxLunchTcGuide(restaurantCantBoxLunchTcGuide);

        programItemDomain.setRestaurantCantBreakfastTcLocalGuide(restaurantCantBreakfastTcLocalGuide);
        programItemDomain.setRestaurantCantLunchTcLocalGuide(restaurantCantLunchTcLocalGuide);
        programItemDomain.setRestaurantCantDinnerTcLocalGuide(restaurantCantDinnerTcLocalGuide);
        programItemDomain.setRestaurantCantBoxLunchTcLocalGuide(restaurantCantBoxLunchTcLocalGuide);

        programItemDomain.setRestaurantCantBreakfastGuide(restaurantCantBreakfastGuide);
        programItemDomain.setRestaurantCantLunchGuide(restaurantCantLunchGuide);
        programItemDomain.setRestaurantCantDinnerGuide(restaurantCantDinnerGuide);
        programItemDomain.setRestaurantCantBoxLunchGuide(restaurantCantBoxLunchGuide);

        if (programDate != null)
            programItemDomain.setProgramDate(programDate);
        if (program != null)
            programItemDomain.setProgram(program);

        return programItemDomain;
    }

    public ProgramItemDomain to(ProgramDomain program, ProgramDateDomain programDate, SupplierTypeDomain supplierType, SupplierDomain supplier, ServiceDomain service) {
        ProgramItemDomain programItemDomain = new ProgramItemDomain();
        programItemDomain.setName(name);
        programItemDomain.setDescription(description);
        programItemDomain.setIdentify(identify);
        programItemDomain.setObservation(observation);
        programItemDomain.setProgramDateTime(programDateTime);
        programItemDomain.setPvt(pvt);
        programItemDomain.setSic(sic);
        programItemDomain.setServiceGroup(serviceGroup);

        // Supplier Type Lodging

        programItemDomain.setLodgingCantSwb(lodgingCantSwb);
        programItemDomain.setLodgingCantMat(lodgingCantMat);
        programItemDomain.setLodgingCantTwin(lodgingCantTwin);
        programItemDomain.setLodgingCantTrp(lodgingCantTrp);

        programItemDomain.setLodgingCategoryStr(lodgingCategoryStr);

        programItemDomain.setLodgingCantBreakfast(lodgingCantBreakfast);
        programItemDomain.setLodgingCantLunch(lodgingCantLunch);
        programItemDomain.setLodgingCantDinner(lodgingCantDinner);
        programItemDomain.setLodgingCantBoxLunch(lodgingCantBoxLunch);

        programItemDomain.setLodgingCantSwbTcGuide(lodgingCantSwbTcGuide);
        programItemDomain.setLodgingCantTwinTcGuide(lodgingCantTwinTcGuide);

        programItemDomain.setLodgingCantBreakfastTcGuide(lodgingCantBreakfastTcGuide);
        programItemDomain.setLodgingCantLunchTcGuide(lodgingCantLunchTcGuide);
        programItemDomain.setLodgingCantDinnerTcGuide(lodgingCantDinnerTcGuide);
        programItemDomain.setLodgingCantBoxLunchTcGuide(lodgingCantBoxLunchTcGuide);

        programItemDomain.setLodgingCantSwbTcLocalGuide(lodgingCantSwbTcLocalGuide);
        programItemDomain.setLodgingCantTwinTcLocalGuide(lodgingCantTwinTcLocalGuide);

        programItemDomain.setLodgingCantBreakfastTcLocalGuide(lodgingCantBreakfastTcLocalGuide);
        programItemDomain.setLodgingCantLunchTcLocalGuide(lodgingCantLunchTcLocalGuide);
        programItemDomain.setLodgingCantDinnerTcLocalGuide(lodgingCantDinnerTcLocalGuide);
        programItemDomain.setLodgingCantBoxLunchTcLocalGuide(lodgingCantBoxLunchTcLocalGuide);

        programItemDomain.setLodgingCantSwbGuide(lodgingCantSwbGuide);
        programItemDomain.setLodgingCantTwinGuide(lodgingCantTwinGuide);

        programItemDomain.setLodgingCantBreakfastGuide(lodgingCantBreakfastGuide);
        programItemDomain.setLodgingCantLunchGuide(lodgingCantLunchGuide);
        programItemDomain.setLodgingCantDinnerGuide(lodgingCantDinnerGuide);
        programItemDomain.setLodgingCantBoxLunchGuide(lodgingCantBoxLunchGuide);

        // Supplier Type Lodge

        programItemDomain.setLodgeCantSwb(lodgeCantSwb);
        programItemDomain.setLodgeCantMat(lodgeCantMat);
        programItemDomain.setLodgeCantTwin(lodgeCantTwin);
        programItemDomain.setLodgeCantTrp(lodgeCantTrp);

        programItemDomain.setLodgeCategoryStr(lodgeCategoryStr);

        programItemDomain.setLodgeCantBreakfast(lodgeCantBreakfast);
        programItemDomain.setLodgeCantLunch(lodgeCantLunch);
        programItemDomain.setLodgeCantDinner(lodgeCantDinner);
        programItemDomain.setLodgeCantBoxLunch(lodgeCantBoxLunch);

        programItemDomain.setLodgeCantSwbTcGuide(lodgeCantSwbTcGuide);
        programItemDomain.setLodgeCantTwinTcGuide(lodgeCantTwinTcGuide);

        programItemDomain.setLodgeCantBreakfastTcGuide(lodgeCantBreakfastTcGuide);
        programItemDomain.setLodgeCantLunchTcGuide(lodgeCantLunchTcGuide);
        programItemDomain.setLodgeCantDinnerTcGuide(lodgeCantDinnerTcGuide);
        programItemDomain.setLodgeCantBoxLunchTcGuide(lodgeCantBoxLunchTcGuide);

        programItemDomain.setLodgeCantSwbTcLocalGuide(lodgeCantSwbTcLocalGuide);
        programItemDomain.setLodgeCantTwinTcLocalGuide(lodgeCantTwinTcLocalGuide);

        programItemDomain.setLodgeCantBreakfastTcLocalGuide(lodgeCantBreakfastTcLocalGuide);
        programItemDomain.setLodgeCantLunchTcLocalGuide(lodgeCantLunchTcLocalGuide);
        programItemDomain.setLodgeCantDinnerTcLocalGuide(lodgeCantDinnerTcLocalGuide);
        programItemDomain.setLodgeCantBoxLunchTcLocalGuide(lodgeCantBoxLunchTcLocalGuide);

        programItemDomain.setLodgeCantSwbGuide(lodgeCantSwbGuide);
        programItemDomain.setLodgeCantTwinGuide(lodgeCantTwinGuide);

        programItemDomain.setLodgeCantBreakfastGuide(lodgeCantBreakfastGuide);
        programItemDomain.setLodgeCantLunchGuide(lodgeCantLunchGuide);
        programItemDomain.setLodgeCantDinnerGuide(lodgeCantDinnerGuide);
        programItemDomain.setLodgeCantBoxLunchGuide(lodgeCantBoxLunchGuide);

        // Supplier Type Restaurant

        programItemDomain.setRestaurantCantBreakfast(restaurantCantBreakfast);
        programItemDomain.setRestaurantCantLunch(restaurantCantLunch);
        programItemDomain.setRestaurantCantDinner(restaurantCantDinner);
        programItemDomain.setRestaurantCantBoxLunch(restaurantCantBoxLunch);

        programItemDomain.setRestaurantCantBreakfastTcGuide(restaurantCantBreakfastTcGuide);
        programItemDomain.setRestaurantCantLunchTcGuide(restaurantCantLunchTcGuide);
        programItemDomain.setRestaurantCantDinnerTcGuide(restaurantCantDinnerTcGuide);
        programItemDomain.setRestaurantCantBoxLunchTcGuide(restaurantCantBoxLunchTcGuide);

        programItemDomain.setRestaurantCantBreakfastTcLocalGuide(restaurantCantBreakfastTcLocalGuide);
        programItemDomain.setRestaurantCantLunchTcLocalGuide(restaurantCantLunchTcLocalGuide);
        programItemDomain.setRestaurantCantDinnerTcLocalGuide(restaurantCantDinnerTcLocalGuide);
        programItemDomain.setRestaurantCantBoxLunchTcLocalGuide(restaurantCantBoxLunchTcLocalGuide);

        programItemDomain.setRestaurantCantBreakfastGuide(restaurantCantBreakfastGuide);
        programItemDomain.setRestaurantCantLunchGuide(restaurantCantLunchGuide);
        programItemDomain.setRestaurantCantDinnerGuide(restaurantCantDinnerGuide);
        programItemDomain.setRestaurantCantBoxLunchGuide(restaurantCantBoxLunchGuide);


        if (programDate != null)
            programItemDomain.setProgramDate(programDate);
        if (program != null)
            programItemDomain.setProgram(program);
        if (supplierType != null)
            programItemDomain.setSupplierType(supplierType);
        if (supplier != null)
            programItemDomain.setSupplier(supplier);
        if (service != null)
            programItemDomain.setService(service);

        return programItemDomain;
    }
}
