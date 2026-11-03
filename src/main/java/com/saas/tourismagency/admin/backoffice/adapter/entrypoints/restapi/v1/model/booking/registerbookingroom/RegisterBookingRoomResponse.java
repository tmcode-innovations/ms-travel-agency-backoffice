package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRoomDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBookingRoomResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("typeRoomBooking")
    private String typeRoomBooking;

    @JsonProperty("registerBookingLodging")
    private RegisterBookingLodgingResponse registerBookingLodging;

    @JsonProperty("registerBookingGlamping")
    private RegisterBookingGlampingResponse registerBookingGlamping;

    @JsonProperty("registerBookingLodger")
    private RegisterBookingLodgerResponse registerBookingLodger;

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

    public static RegisterBookingRoomResponse from(RegisterBookingRoomDomain domain) {
        RegisterBookingRoomResponse response = new RegisterBookingRoomResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setTypeRoomBooking(domain.getTypeRoomBooking());

        if (domain.getRegisterBookingLodger() != null &&
                domain.getRegisterBookingLodger().getId() != null &&
                !domain.getRegisterBookingLodger().getId().isEmpty()){
            response.setRegisterBookingLodger(RegisterBookingLodgerResponse.from(domain.getRegisterBookingLodger()));
        }

        if (domain.getRegisterBookingLodging() != null &&
                domain.getRegisterBookingLodging().getId() != null &&
                !domain.getRegisterBookingLodging().getId().isEmpty()){
            response.setRegisterBookingLodging(RegisterBookingLodgingResponse.from(domain.getRegisterBookingLodging()));
        }

        if (domain.getRegisterBookingGlamping() != null &&
                domain.getRegisterBookingGlamping().getId() != null &&
                !domain.getRegisterBookingGlamping().getId().isEmpty()){
            response.setRegisterBookingGlamping(RegisterBookingGlampingResponse.from(domain.getRegisterBookingGlamping()));
        }

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
