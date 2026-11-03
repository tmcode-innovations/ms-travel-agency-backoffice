package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingItemLodgingFeedingResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("dateFeeding")
    private LocalDate dateFeeding;

    @JsonProperty("dateTimeFeeding")
    private LocalDateTime dateTimeFeeding;

    @JsonProperty("cant")
    private Integer cant;

    @JsonProperty("typeFeedingBooking")
    private String typeFeedingBooking;

    @JsonProperty("typePassengerBooking")
    private String typePassengerBooking;

    @JsonProperty("typeBooking")
    private String typeBooking;

    // Booking Lodging

    @JsonProperty("registerBookingLodging")
    private RegisterBookingLodgingResponse registerBookingLodging;

    @JsonProperty("registerBookingItemLodging")
    private RegisterBookingItemLodgingResponse registerBookingItemLodging;

    // Booking Glamping

    @JsonProperty("registerBookingGlamping")
    private RegisterBookingGlampingResponse registerBookingGlamping;

    @JsonProperty("registerBookingItemGlamping")
    private RegisterBookingItemGlampingResponse registerBookingItemGlamping;

    // Booking Lodge

    @JsonProperty("registerBookingLodger")
    private RegisterBookingLodgerResponse registerBookingLodger;

    @JsonProperty("registerBookingItemLodgerLodging")
    private RegisterBookingItemLodgerLodgingResponse registerBookingItemLodgerLodging;

    // Register Group

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

    public static RegisterBookingItemLodgingFeedingResponse from(RegisterBookingItemLodgingFeedingDomain domain) {
        RegisterBookingItemLodgingFeedingResponse response = new RegisterBookingItemLodgingFeedingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        domain.setDateFeeding(domain.getDateFeeding());
        domain.setDateTimeFeeding(domain.getDateTimeFeeding());
        domain.setCant(domain.getCant());
        domain.setTypeFeedingBooking(domain.getTypeFeedingBooking());
        domain.setTypePassengerBooking(domain.getTypePassengerBooking());
        domain.setTypeBooking(domain.getTypeBooking());
        return response;
    }

    public static RegisterBookingItemLodgingFeedingResponse fromLodging(RegisterBookingItemLodgingFeedingDomain domain) {
        RegisterBookingItemLodgingFeedingResponse response = new RegisterBookingItemLodgingFeedingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        domain.setDateFeeding(domain.getDateFeeding());
        domain.setDateTimeFeeding(domain.getDateTimeFeeding());
        domain.setCant(domain.getCant());
        domain.setTypeFeedingBooking(domain.getTypeFeedingBooking());
        domain.setTypePassengerBooking(domain.getTypePassengerBooking());
        domain.setTypeBooking(domain.getTypeBooking());

        response.setRegisterBookingLodging(RegisterBookingLodgingResponse.from(domain.getRegisterBookingLodging()));
        response.setRegisterBookingItemLodging(RegisterBookingItemLodgingResponse.from(domain.getRegisterBookingItemLodging()));
        response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));

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

    public static RegisterBookingItemLodgingFeedingResponse fromLodger(RegisterBookingItemLodgingFeedingDomain domain) {
        RegisterBookingItemLodgingFeedingResponse response = new RegisterBookingItemLodgingFeedingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        domain.setDateFeeding(domain.getDateFeeding());
        domain.setDateTimeFeeding(domain.getDateTimeFeeding());
        domain.setCant(domain.getCant());
        domain.setTypeFeedingBooking(domain.getTypeFeedingBooking());
        domain.setTypePassengerBooking(domain.getTypePassengerBooking());
        domain.setTypeBooking(domain.getTypeBooking());

        response.setRegisterBookingLodger(RegisterBookingLodgerResponse.from(domain.getRegisterBookingLodger()));
        response.setRegisterBookingItemLodgerLodging(RegisterBookingItemLodgerLodgingResponse.from(domain.getRegisterBookingItemLodgerLodging()));
        response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));

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

    public static RegisterBookingItemLodgingFeedingResponse fromGlamping(RegisterBookingItemLodgingFeedingDomain domain) {
        RegisterBookingItemLodgingFeedingResponse response = new RegisterBookingItemLodgingFeedingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        domain.setDateFeeding(domain.getDateFeeding());
        domain.setDateTimeFeeding(domain.getDateTimeFeeding());
        domain.setCant(domain.getCant());
        domain.setTypeFeedingBooking(domain.getTypeFeedingBooking());
        domain.setTypePassengerBooking(domain.getTypePassengerBooking());
        domain.setTypeBooking(domain.getTypeBooking());

        response.setRegisterBookingGlamping(RegisterBookingGlampingResponse.from(domain.getRegisterBookingGlamping()));
        response.setRegisterBookingItemGlamping(RegisterBookingItemGlampingResponse.from(domain.getRegisterBookingItemGlamping()));
        response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));

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
