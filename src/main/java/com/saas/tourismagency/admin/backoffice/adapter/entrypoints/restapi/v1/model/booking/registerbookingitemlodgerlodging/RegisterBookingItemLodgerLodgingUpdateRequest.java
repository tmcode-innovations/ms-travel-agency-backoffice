package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemLodgerLodgingUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("noCupon")
    private String noCupon;

    @JsonProperty("noCuponPreImpress")
    private String noCuponPreImpress;

    @JsonProperty("dateFrom")
    private LocalDate dateFrom;

    @JsonProperty("dateTo")
    private LocalDate dateTo;

    @JsonProperty("noNight")
    private Integer noNight;

    @JsonProperty("noNightTcGuide")
    private Integer noNightTcGuide;

    @JsonProperty("noNightTcLocalGuide")
    private Integer noNightTcLocalGuide;

    @JsonProperty("noNightGuide")
    private Integer noNightGuide;

    @JsonProperty("cantCb")
    private Integer cantCb;

    @JsonProperty("cantAb")
    private Integer cantAb;

    @JsonProperty("cantBreakfastBuffet")
    private Integer cantBreakfastBuffet;

    @JsonProperty("cantDinner")
    private Integer cantDinner;

    @JsonProperty("cantLunch")
    private Integer cantLunch;

    @JsonProperty("cantBoxLunch")
    private Integer cantBoxLunch;

    @JsonProperty("cantTcGuideBreakfastBuffet")
    private Integer cantTcGuideBreakfastBuffet;

    @JsonProperty("cantTcGuideDinner")
    private Integer cantTcGuideDinner;

    @JsonProperty("cantTcGuideLunch")
    private Integer cantTcGuideLunch;

    @JsonProperty("cantTcGuideBoxLunch")
    private Integer cantTcGuideBoxLunch;

    @JsonProperty("cantTcLocalGuideBreakfastBuffet")
    private Integer cantTcLocalGuideBreakfastBuffet;

    @JsonProperty("cantTcLocalGuideDinner")
    private Integer cantTcLocalGuideDinner;

    @JsonProperty("cantTcLocalGuideLunch")
    private Integer cantTcLocalGuideLunch;

    @JsonProperty("cantTcLocalGuideBoxLunch")
    private Integer cantTcLocalGuideBoxLunch;

    @JsonProperty("cantGuideBreakfastBuffet")
    private Integer cantGuideBreakfastBuffet;

    @JsonProperty("cantGuideDinner")
    private Integer cantGuideDinner;

    @JsonProperty("cantGuideLunch")
    private Integer cantGuideLunch;

    @JsonProperty("cantGuideBoxLunch")
    private Integer cantGuideBoxLunch;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("category")
    private String category;

    @JsonProperty("tcLocal")
    private Integer tcLocal;

    @JsonProperty("tc")
    private Integer tc;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;



    @JsonProperty("lodgingCategory")
    private String lodgingCategory;

    @JsonProperty("registerBookingLodger")
    private String registerBookingLodger;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public RegisterBookingItemLodgerLodgingDomain to() {
        RegisterBookingItemLodgerLodgingDomain domain = new RegisterBookingItemLodgerLodgingDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoCupon(noCupon);
        domain.setNoCuponPreImpress(noCuponPreImpress);
        domain.setDateFrom(dateFrom);
        domain.setDateTo(dateTo);
        domain.setNoNight(noNight);
        domain.setNoNightTcGuide(noNightTcGuide);
        domain.setNoNightTcLocalGuide(noNightTcLocalGuide);
        domain.setNoNightGuide(noNightTcLocalGuide);
        domain.setCantCb(cantCb);
        domain.setCantAb(cantAb);
        domain.setCantBreakfastBuffet(cantBreakfastBuffet);
        domain.setCantDinner(cantDinner);
        domain.setCantLunch(cantLunch);
        domain.setCantBoxLunch(cantBoxLunch);
        domain.setCantTcGuideBreakfastBuffet(cantTcGuideBreakfastBuffet);
        domain.setCantTcGuideDinner(cantTcGuideDinner);
        domain.setCantTcGuideLunch(cantTcGuideLunch);
        domain.setCantTcGuideBoxLunch(cantTcGuideBoxLunch);
        domain.setCantTcLocalGuideBreakfastBuffet(cantTcLocalGuideBreakfastBuffet);
        domain.setCantTcLocalGuideDinner(cantTcLocalGuideDinner);
        domain.setCantTcLocalGuideLunch(cantTcLocalGuideLunch);
        domain.setCantTcLocalGuideBoxLunch(cantTcLocalGuideBoxLunch);
        domain.setCantGuideBreakfastBuffet(cantGuideBreakfastBuffet);
        domain.setCantGuideDinner(cantGuideDinner);
        domain.setCantGuideLunch(cantGuideLunch);
        domain.setCantGuideBoxLunch(cantGuideBoxLunch);
        domain.setObservation(observation);
        domain.setCategory(category);
        domain.setTcLocal(tcLocal);
        domain.setTc(tc);
        domain.setGuide(guide);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setCantPaxAdult(cantPaxAdult);
        return domain;
    }

    public RegisterBookingItemLodgerLodgingDomain to(LodgingCategoryDomain lodgingCategory, RegisterBookingLodgerDomain registerBookingLodger, RegisterGroupDomain registerGroup) {
        RegisterBookingItemLodgerLodgingDomain domain = new RegisterBookingItemLodgerLodgingDomain();
        domain.setId(id);
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);

        domain.setNoCupon(noCupon);
        domain.setNoCuponPreImpress(noCuponPreImpress);
        domain.setDateFrom(dateFrom);
        domain.setDateTo(dateTo);
        domain.setNoNight(noNight);
        domain.setNoNightTcGuide(noNightTcGuide);
        domain.setNoNightTcLocalGuide(noNightTcLocalGuide);
        domain.setNoNightGuide(noNightGuide);
        domain.setCantCb(cantCb);
        domain.setCantAb(cantAb);
        domain.setCantBreakfastBuffet(cantBreakfastBuffet);
        domain.setCantDinner(cantDinner);
        domain.setCantLunch(cantLunch);
        domain.setCantBoxLunch(cantBoxLunch);
        domain.setCantTcGuideBreakfastBuffet(cantTcGuideBreakfastBuffet);
        domain.setCantTcGuideDinner(cantTcGuideDinner);
        domain.setCantTcGuideLunch(cantTcGuideLunch);
        domain.setCantTcGuideBoxLunch(cantTcGuideBoxLunch);
        domain.setCantTcLocalGuideBreakfastBuffet(cantTcLocalGuideBreakfastBuffet);
        domain.setCantTcLocalGuideDinner(cantTcLocalGuideDinner);
        domain.setCantTcLocalGuideLunch(cantTcLocalGuideLunch);
        domain.setCantTcLocalGuideBoxLunch(cantTcLocalGuideBoxLunch);
        domain.setCantGuideBreakfastBuffet(cantGuideBreakfastBuffet);
        domain.setCantGuideDinner(cantGuideDinner);
        domain.setCantGuideLunch(cantGuideLunch);
        domain.setCantGuideBoxLunch(cantGuideBoxLunch);
        domain.setObservation(observation);
        domain.setCategory(category);
        domain.setTcLocal(tcLocal);
        domain.setTc(tc);
        domain.setGuide(tc);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setCantPaxAdult(cantPaxAdult);

        if (lodgingCategory != null)
            domain.setLodgingCategory(lodgingCategory);
        if (registerBookingLodger != null)
            domain.setRegisterBookingLodger(registerBookingLodger);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }

}
