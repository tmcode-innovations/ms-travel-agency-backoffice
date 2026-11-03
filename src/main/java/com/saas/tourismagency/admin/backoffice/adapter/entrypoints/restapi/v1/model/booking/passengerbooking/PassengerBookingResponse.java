package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution.RegisterBookingInstitutionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingroom.RegisterBookingRoomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeRoomBookingEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerBookingResponse {

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
    private TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModel;

    @JsonProperty("typeRoomBooking")
    private String typeRoomBooking;

    @JsonProperty("typeRoomBookingDescription")
    private String typeRoomBookingDescription;

    @JsonProperty("typeRoomBookingModel")
    private TypeRoomBookingEnum.TypeRoomBookingModel typeRoomBookingModel;

    @JsonProperty("passengerGroup")
    private PassengerGroupResponse passengerGroup;

    @JsonProperty("registerBookingOperator")
    private RegisterBookingOperatorResponse registerBookingOperator;

    @JsonProperty("registerBookingRestaurant")
    private RegisterBookingRestaurantResponse registerBookingRestaurant;

    @JsonProperty("registerBookingLodging")
    private RegisterBookingLodgingResponse registerBookingLodging;

    @JsonProperty("registerBookingGlamping")
    private RegisterBookingGlampingResponse registerBookingGlamping;

    @JsonProperty("registerBookingLodger")
    private RegisterBookingLodgerResponse registerBookingLodger;

    @JsonProperty("registerBookingInstitution")
    private RegisterBookingInstitutionResponse registerBookingInstitution;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("registerBookingRoom")
    private RegisterBookingRoomResponse registerBookingRoom;

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

    public static PassengerBookingResponse from(PassengerBookingDomain domain) {
        PassengerBookingResponse response = new PassengerBookingResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());

        response.setTypePassengerTcBooking(domain.getTypePassengerTcBooking());
        if (response.getTypePassengerTcBooking() != null && !response.getTypePassengerTcBooking().isEmpty()){
            TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModelData = EnumMappeableService
                    .map(TypePassengerTcBookingEnum.class)
                    .stream().filter(
                            typePassengerTcBookingModel -> typePassengerTcBookingModel.name().equals(domain.getTypePassengerTcBooking()))
                    .findFirst().get();
            response.setTypePassengerTcBookingModel(typePassengerTcBookingModelData);
            response.setTypePassengerTcBookingDescription(typePassengerTcBookingModelData.description());
        }

        response.setTypeRoomBooking(domain.getTypeRoomBooking());
        if (response.getTypeRoomBooking() != null && !response.getTypeRoomBooking().isEmpty()){
            TypeRoomBookingEnum.TypeRoomBookingModel typeRoomBookingModelData = EnumMappeableService
                    .map(TypeRoomBookingEnum.class)
                    .stream().filter(
                            typeRoomBookingModel -> typeRoomBookingModel.name().equals(domain.getTypeRoomBooking()))
                    .findFirst().get();
            response.setTypeRoomBookingModel(typeRoomBookingModelData);
            response.setTypeRoomBookingDescription(typeRoomBookingModelData.description());
        }

        if (domain.getPassengerGroup() != null &&
                domain.getPassengerGroup().getId() != null &&
                !domain.getPassengerGroup().getId().isEmpty()){
            response.setPassengerGroup(PassengerGroupResponse.from(domain.getPassengerGroup()));
        }

        if (domain.getRegisterBookingOperator() != null &&
                domain.getRegisterBookingOperator().getId() != null &&
                !domain.getRegisterBookingOperator().getId().isEmpty()){
            response.setRegisterBookingOperator(RegisterBookingOperatorResponse.from(domain.getRegisterBookingOperator()));
        }

        if (domain.getRegisterBookingRestaurant() != null &&
                domain.getRegisterBookingRestaurant().getId() != null &&
                !domain.getRegisterBookingRestaurant().getId().isEmpty()){
            response.setRegisterBookingRestaurant(RegisterBookingRestaurantResponse.from(domain.getRegisterBookingRestaurant()));
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

        if (domain.getRegisterBookingLodger() != null &&
                domain.getRegisterBookingLodger().getId() != null &&
                !domain.getRegisterBookingLodger().getId().isEmpty()){
            response.setRegisterBookingLodger(RegisterBookingLodgerResponse.from(domain.getRegisterBookingLodger()));
        }

        if (domain.getRegisterBookingInstitution() != null &&
                domain.getRegisterBookingInstitution().getId() != null &&
                !domain.getRegisterBookingInstitution().getId().isEmpty()){
            response.setRegisterBookingInstitution(RegisterBookingInstitutionResponse.from(domain.getRegisterBookingInstitution()));
        }

        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }

        if (domain.getRegisterBookingRoom() != null &&
                domain.getRegisterBookingRoom().getId() != null &&
                !domain.getRegisterBookingRoom().getId().isEmpty()){
            response.setRegisterBookingRoom(RegisterBookingRoomResponse.from(domain.getRegisterBookingRoom()));
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
