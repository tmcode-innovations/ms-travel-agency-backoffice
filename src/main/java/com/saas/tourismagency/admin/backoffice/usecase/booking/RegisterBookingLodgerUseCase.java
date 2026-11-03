package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.ExportCuponTypePassengerBookingEnum;

import java.util.List;

public interface RegisterBookingLodgerUseCase {

    public RegisterBookingLodgerResponse create(RegisterBookingLodgerCreateRequest request);

    public RegisterBookingLodgerResponse edit(String id, RegisterBookingLodgerUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingLodgerResponse> findAll();

    public RegisterBookingLodgerResponse findById(String id);

    public RegisterBookingLodgerSearchResponse searchAll(RegisterBookingLodgerSearchRequest bankSearchRequest);

    public RegisterBookingLodgerSearchResponse searchFilterAll(RegisterBookingLodgerSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingLodgerResponse findByName(String name);

    public RegisterBookingLodgerResponse findByIdentify(String identify);

    public RegisterBookingLodgerResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemLodgerLodgingResponse> findAllRegisterBookingItemLodgerLodgingByBooking(String idBooking);

    public RegisterBookingItemLodgerLodgingResponse addRegisterBookingItemLodgerLodging(RegisterBookingItemLodgerLodgingCreateRequest request, String idBooking);

    public RegisterBookingItemLodgerLodgingResponse editRegisterBookingItemLodgerLodging(String id, RegisterBookingItemLodgerLodgingUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemLodgerLodging(String id, String idBooking);

    public RegisterBookingItemLodgerLodgingResponse findByIdLodgerLodgingAndBooking(String id, String idBooking);


    public List<RegisterBookingItemLodgerResponse> findAllRegisterBookingItemLodgerByBooking(String idBooking);

    public RegisterBookingItemLodgerResponse addRegisterBookingItemLodger(RegisterBookingItemLodgerCreateRequest request, String idBooking);

    public RegisterBookingItemLodgerResponse editRegisterBookingItemLodger(String id, RegisterBookingItemLodgerUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemLodger(String id, String idBooking);

    public RegisterBookingItemLodgerResponse findByIdLodgerAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking);

    // Export Data

    public byte[] exportRegisterBookingLodgerCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingLodgerLodgingItemCuponPdf(String idRegisterBookingItem);

    public byte[] exportRegisterBookingLodgerLodgingItemCuponPdf(String idRegisterBookingItem, String exportCuponTypePassengerBooking);

    public byte[] exportRegisterBookingLodgerPdf(String idRegisterBooking);


    public RegisterBookingLodgerResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingLodgerResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);

}
