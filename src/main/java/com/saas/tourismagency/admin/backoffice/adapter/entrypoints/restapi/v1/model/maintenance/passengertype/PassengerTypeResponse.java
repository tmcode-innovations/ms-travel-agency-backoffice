package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature.TypePassengerTcBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerTypeResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("typePassengerTcBooking")
    private String typePassengerTcBooking;

    @JsonProperty("typePassengerTcBookingDescription")
    private String typePassengerTcBookingDescription;

    @JsonProperty("typePassengerTcBookingModel")
    private TypePassengerTcBookingResponse typePassengerTcBookingModel;

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

    public static PassengerTypeResponse from(PassengerTypeDomain domain) {
        PassengerTypeResponse passengerTypeResponse = new PassengerTypeResponse();
        passengerTypeResponse.setId(domain.getId());
        passengerTypeResponse.setName(domain.getName());
        passengerTypeResponse.setDescription(domain.getDescription());
        passengerTypeResponse.setIdentify(domain.getIdentify());
        if (domain.getTypePassengerTcBooking() != null && !domain.getTypePassengerTcBooking().isEmpty()){
            passengerTypeResponse.setTypePassengerTcBooking(domain.getTypePassengerTcBooking());
            TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModelData = EnumMappeableService
                    .map(TypePassengerTcBookingEnum.class)
                    .stream().filter(
                            typePassengerTcBookingModel -> typePassengerTcBookingModel.name().equals(domain.getTypePassengerTcBooking()))
                    .findFirst().get();
            passengerTypeResponse.setTypePassengerTcBookingDescription(typePassengerTcBookingModelData.description());
            passengerTypeResponse.setTypePassengerTcBookingModel(TypePassengerTcBookingResponse.from(typePassengerTcBookingModelData));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            passengerTypeResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            passengerTypeResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            passengerTypeResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        passengerTypeResponse.setCreatedAt(domain.getCreatedAt());
        passengerTypeResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        passengerTypeResponse.setUpdatedAt(domain.getUpdatedAt());
        passengerTypeResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        passengerTypeResponse.setDeletedAt(domain.getDeletedAt());
        passengerTypeResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return passengerTypeResponse;
    }
}
