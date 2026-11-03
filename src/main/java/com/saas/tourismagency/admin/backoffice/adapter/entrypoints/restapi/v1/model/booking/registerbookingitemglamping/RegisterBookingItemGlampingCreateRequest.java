package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemGlampingCreateRequest {

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

    @JsonProperty("cantTcGuideBreakfastBuffet")
    private Integer cantTcGuideBreakfastBuffet;

    @JsonProperty("cantTcGuideDinner")
    private Integer cantTcGuideDinner;

    @JsonProperty("cantTcGuideLunch")
    private Integer cantTcGuideLunch;

    @JsonProperty("cantTcLocalGuideBreakfastBuffet")
    private Integer cantTcLocalGuideBreakfastBuffet;

    @JsonProperty("cantTcLocalGuideDinner")
    private Integer cantTcLocalGuideDinner;

    @JsonProperty("cantTcLocalGuideLunch")
    private Integer cantTcLocalGuideLunch;

    @JsonProperty("cantGuideBreakfastBuffet")
    private Integer cantGuideBreakfastBuffet;

    @JsonProperty("cantGuideDinner")
    private Integer cantGuideDinner;

    @JsonProperty("cantGuideLunch")
    private Integer cantGuideLunch;

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

    @JsonProperty("registerBookingGlamping")
    private String registerBookingGlamping;

    @JsonProperty("registerGroup")
    private String registerGroup;

    public RegisterBookingItemGlampingDomain to() {
        RegisterBookingItemGlampingDomain domain = new RegisterBookingItemGlampingDomain();
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
        domain.setCantCb(cantCb);
        domain.setCantAb(cantAb);
        domain.setCantBreakfastBuffet(cantBreakfastBuffet);
        domain.setCantDinner(cantDinner);
        domain.setCantLunch(cantLunch);
        domain.setCantTcGuideBreakfastBuffet(cantTcGuideBreakfastBuffet);
        domain.setCantTcGuideDinner(cantTcGuideDinner);
        domain.setCantTcGuideLunch(cantTcGuideLunch);
        domain.setCantTcLocalGuideBreakfastBuffet(cantTcLocalGuideBreakfastBuffet);
        domain.setCantTcLocalGuideDinner(cantTcLocalGuideDinner);
        domain.setCantTcLocalGuideLunch(cantTcLocalGuideLunch);
        domain.setCantGuideBreakfastBuffet(cantGuideBreakfastBuffet);
        domain.setCantGuideDinner(cantGuideDinner);
        domain.setCantGuideLunch(cantGuideLunch);
        domain.setObservation(observation);
        domain.setCategory(category);
        domain.setTcLocal(tcLocal);
        domain.setTc(tc);
        domain.setGuide(guide);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setCantPaxAdult(cantPaxAdult);
        return domain;
    }

    public RegisterBookingItemGlampingDomain to(LodgingCategoryDomain lodgingCategory, RegisterBookingGlampingDomain registerBookingGlamping, RegisterGroupDomain registerGroup) {
        RegisterBookingItemGlampingDomain domain = new RegisterBookingItemGlampingDomain();
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
        domain.setCantCb(cantCb);
        domain.setCantAb(cantAb);
        domain.setCantBreakfastBuffet(cantBreakfastBuffet);
        domain.setCantDinner(cantDinner);
        domain.setCantLunch(cantLunch);
        domain.setCantTcGuideBreakfastBuffet(cantTcGuideBreakfastBuffet);
        domain.setCantTcGuideDinner(cantTcGuideDinner);
        domain.setCantTcGuideLunch(cantTcGuideLunch);
        domain.setCantTcLocalGuideBreakfastBuffet(cantTcLocalGuideBreakfastBuffet);
        domain.setCantTcLocalGuideDinner(cantTcLocalGuideDinner);
        domain.setCantTcLocalGuideLunch(cantTcLocalGuideLunch);
        domain.setCantGuideBreakfastBuffet(cantGuideBreakfastBuffet);
        domain.setCantGuideDinner(cantGuideDinner);
        domain.setCantGuideLunch(cantGuideLunch);
        domain.setObservation(observation);
        domain.setCategory(category);
        domain.setTcLocal(tcLocal);
        domain.setTc(tc);
        domain.setGuide(guide);
        domain.setCantPaxChildren(cantPaxChildren);
        domain.setCantPaxAdult(cantPaxAdult);

        if (lodgingCategory != null)
            domain.setLodgingCategory(lodgingCategory);
        if (registerBookingGlamping != null)
            domain.setRegisterBookingGlamping(registerBookingGlamping);
        if (registerGroup != null)
            domain.setRegisterGroup(registerGroup);
        return domain;
    }
}
