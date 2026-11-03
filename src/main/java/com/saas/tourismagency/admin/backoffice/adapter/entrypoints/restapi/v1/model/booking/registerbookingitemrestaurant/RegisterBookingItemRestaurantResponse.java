package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemRestaurantDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemRestaurantResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateService")
    private LocalDate dateService;

    @JsonProperty("cantPax")
    private Integer cantPax;

    @JsonProperty("cantPaxAdult")
    private Integer cantPaxAdult;

    @JsonProperty("cantPaxChildren")
    private Integer cantPaxChildren;

    @JsonProperty("tc")
    private Integer tc;

    @JsonProperty("tcLocal")
    private Integer tcLocal;

    @JsonProperty("guide")
    private Integer guide;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("otherService")
    private String otherService;

    @JsonProperty("pvt")
    private Boolean pvt;

    @JsonProperty("sic")
    private Boolean sic;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("dateStr")
    private String dateStr;

    @JsonProperty("dateStrDescription")
    private String dateStrDescription;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("dateTimeStr")
    private String dateTimeStr;

    @JsonProperty("idItineraryItemIdentify")
    private String idItineraryItemIdentify;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("cantGuideTc")
    private Integer cantGuideTc;

    @JsonProperty("service")
    private ServiceResponse service;

    @JsonProperty("registerBookingRestaurant")
    private RegisterBookingRestaurantResponse registerBookingRestaurant;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

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

    public static RegisterBookingItemRestaurantResponse from(RegisterBookingItemRestaurantDomain domain) {
        RegisterBookingItemRestaurantResponse response = new RegisterBookingItemRestaurantResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());

        response.setIdentify(domain.getIdentify());
        response.setDateService(domain.getDateService());
        response.setCantPax(domain.getCantPax());
        response.setCantPaxAdult(domain.getCantPaxAdult());
        response.setCantPaxChildren(domain.getCantPaxChildren());
        response.setTc(domain.getTc());
        response.setTcLocal(domain.getTcLocal());
        response.setGuide(domain.getGuide());
        response.setObservation(domain.getObservation());
        response.setOtherService(domain.getOtherService());
        response.setPvt(domain.getPvt());
        response.setSic(domain.getSic());
        response.setDate(domain.getDate());
        response.setDateStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDate()));
        response.setDateStrDescription(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDate()));

        response.setDateTime(domain.getDateTime());
        response.setDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getDateTime()));

        response.setIdItineraryItemIdentify(domain.getIdItineraryItemIdentify());
        response.setCant(domain.getCant());
        response.setCantGuideTc(domain.getCantGuideTc());

        if (domain.getService() != null &&
                domain.getService().getId() != null &&
                !domain.getService().getId().isEmpty()){
            response.setService(ServiceResponse.from(domain.getService()));
        }else{
            response.setService( null );
        }

        if (domain.getRegisterBookingRestaurant() != null &&
                domain.getRegisterBookingRestaurant().getId() != null &&
                !domain.getRegisterBookingRestaurant().getId().isEmpty()){
            response.setRegisterBookingRestaurant(RegisterBookingRestaurantResponse.from(domain.getRegisterBookingRestaurant()));
        }else{
            response.setRegisterBookingRestaurant( null );
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }else{
            response.setRegisterGroup( null );
        }

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
