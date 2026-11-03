package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerLodgingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemLodgerLodgingResponse {

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

    @JsonProperty("dateFromStr")
    private String dateFromStr;

    @JsonProperty("dateTo")
    private LocalDate dateTo;

    @JsonProperty("dateToStr")
    private String dateToStr;

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
    private LodgingCategoryResponse lodgingCategory;

    @JsonProperty("registerBookingLodger")
    private RegisterBookingLodgerResponse registerBookingLodger;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;


    @JsonProperty("cantBreakfastBuffetTotal")
    private Integer cantBreakfastBuffetTotal;

    @JsonProperty("cantDinnerTotal")
    private Integer cantDinnerTotal;

    @JsonProperty("cantLunchTotal")
    private Integer cantLunchTotal;

    @JsonProperty("cantBoxLunchTotal")
    private Integer cantBoxLunchTotal;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static RegisterBookingItemLodgerLodgingResponse from(RegisterBookingItemLodgerLodgingDomain domain) {
        RegisterBookingItemLodgerLodgingResponse response = new RegisterBookingItemLodgerLodgingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setNoCupon(domain.getNoCupon());
        response.setNoCuponPreImpress(domain.getNoCuponPreImpress());
        response.setDateFrom(domain.getDateFrom());
        response.setDateFromStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateFrom()));
        response.setDateTo(domain.getDateTo());
        response.setDateToStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateTo()));
        response.setNoNight(domain.getNoNight());
        response.setNoNightTcGuide(domain.getNoNightTcGuide());
        response.setNoNightTcLocalGuide(domain.getNoNightTcLocalGuide());
        response.setNoNightGuide(domain.getNoNightGuide());
        response.setCantCb(domain.getCantCb());
        response.setCantAb(domain.getCantAb());
        response.setCantBreakfastBuffet(domain.getCantBreakfastBuffet());
        response.setCantDinner(domain.getCantDinner());
        response.setCantLunch(domain.getCantLunch());
        response.setCantBoxLunch(domain.getCantBoxLunch());
        response.setCantTcGuideBreakfastBuffet(domain.getCantTcGuideBreakfastBuffet());
        response.setCantTcGuideDinner(domain.getCantTcGuideDinner());
        response.setCantTcGuideLunch(domain.getCantTcGuideLunch());
        response.setCantTcGuideBoxLunch(domain.getCantTcGuideBoxLunch());
        response.setCantTcLocalGuideBreakfastBuffet(domain.getCantTcLocalGuideBreakfastBuffet());
        response.setCantTcLocalGuideDinner(domain.getCantTcLocalGuideDinner());
        response.setCantTcLocalGuideLunch(domain.getCantTcLocalGuideLunch());
        response.setCantTcLocalGuideBoxLunch(domain.getCantTcLocalGuideBoxLunch());
        response.setCantGuideBreakfastBuffet(domain.getCantGuideBreakfastBuffet());
        response.setCantGuideDinner(domain.getCantGuideDinner());
        response.setCantGuideLunch(domain.getCantGuideLunch());
        response.setCantGuideBoxLunch(domain.getCantGuideBoxLunch());
        response.setObservation(domain.getObservation());
        response.setCategory(domain.getCategory());
        response.setTcLocal(domain.getTcLocal());
        response.setTc(domain.getTc());
        response.setGuide(domain.getGuide());
        response.setCantPaxChildren(domain.getCantPaxChildren());
        response.setCantPaxAdult(domain.getCantPaxAdult());

        response.setLodgingCategory(LodgingCategoryResponse.from(domain.getLodgingCategory()));
        response.setRegisterBookingLodger(RegisterBookingLodgerResponse.from(domain.getRegisterBookingLodger()));
        response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));

        response.setCantBreakfastBuffetTotal(domain.getCantBreakfastBuffetTotal());
        response.setCantDinnerTotal(domain.getCantDinnerTotal());
        response.setCantLunchTotal(domain.getCantLunchTotal());
        response.setCantBoxLunchTotal(domain.getCantBoxLunchTotal());

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            response.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            response.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            response.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        response.setCreatedAt(domain.getCreatedAt());
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return response;
    }
}
