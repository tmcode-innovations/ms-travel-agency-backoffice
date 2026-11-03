package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;

import java.util.List;

public interface PassengerBookingUseCase {

    public PassengerBookingResponse create(PassengerBookingCreateRequest request);

    public PassengerBookingResponse create(
            PassengerBookingCreateRequest request,
            PassengerGroupDomain passengerGroupDomain,
            RegisterBookingOperatorDomain registerBookingOperatorDomain,
            RegisterBookingRestaurantDomain registerBookingRestaurantDomain,
            RegisterBookingLodgingDomain registerBookingLodgingDomain,
            RegisterBookingLodgerDomain registerBookingLodgerDomain,
            RegisterBookingGlampingDomain registerBookingGlampingDomain,
            RegisterBookingInstitutionDomain registerBookingInstitutionDomain,
            RegisterGroupDomain registerGroupDomain,
            RegisterBookingRoomDomain registerBookingRoomDomain
            );

    public List<PassengerBookingResponse> createMultiple(
            PassengerBookingCreateRequest request,
            RegisterBookingOperatorDomain registerBookingOperatorDomain,
            RegisterBookingRestaurantDomain registerBookingRestaurantDomain,
            RegisterBookingLodgingDomain registerBookingLodgingDomain,
            RegisterBookingLodgerDomain registerBookingLodgerDomain,
            RegisterBookingGlampingDomain registerBookingGlampingDomain,
            RegisterBookingInstitutionDomain registerBookingInstitutionDomain,
            RegisterGroupDomain registerGroupDomain,
            RegisterBookingRoomDomain registerBookingRoomDomain
    );

    public PassengerBookingResponse edit(String id, PassengerBookingUpdateRequest request);

    public void deleteById(String id);

    public List<PassengerBookingResponse> findAll();

    public PassengerBookingResponse findById(String id);

    public PassengerBookingSearchResponse searchAll(PassengerBookingSearchRequest bankSearchRequest);

    public PassengerBookingSearchResponse searchFilterAll(PassengerBookingSearchFilterRequest bankSearchFilterRequest);


    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingOperatorAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingRestaurantAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingGlampingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup);

    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroup(String idRegisterBooking, String idRegisterGroup);


    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking);


    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);

    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking);
}
