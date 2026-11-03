package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.ExportCuponTypePassengerBookingEnum;

import java.util.List;

public interface RegisterBookingLodgingUseCase {

    public RegisterBookingLodgingResponse create(RegisterBookingLodgingCreateRequest request);

    public RegisterBookingLodgingResponse edit(String id, RegisterBookingLodgingUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingLodgingResponse> findAll();

    public RegisterBookingLodgingResponse findById(String id);

    public RegisterBookingLodgingSearchResponse searchAll(RegisterBookingLodgingSearchRequest bankSearchRequest);

    public RegisterBookingLodgingSearchResponse searchFilterAll(RegisterBookingLodgingSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingLodgingResponse findByName(String name);

    public RegisterBookingLodgingResponse findByIdentify(String identify);

    public RegisterBookingLodgingResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemLodgingResponse> findAllRegisterBookingItemLodgingByBooking(String idBooking);

    public RegisterBookingItemLodgingResponse addRegisterBookingItemLodging(RegisterBookingItemLodgingCreateRequest request, String idBooking);

    public RegisterBookingItemLodgingResponse editRegisterBookingItemLodging(String id, RegisterBookingItemLodgingUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemLodging(String id, String idBooking);

    public RegisterBookingItemLodgingResponse findByIdAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking);

    // Export Data

    public byte[] exportRegisterBookingLodgingCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingLodgingCuponPdf(String idRegisterBooking, String exportCuponTypePassengerBooking);

    public byte[] exportRegisterBookingLodgingItemCuponPdf(String idRegisterBookingItem);

    public byte[] exportRegisterBookingLodgingItemCuponPdf(String idRegisterBookingItem, String exportCuponTypePassengerBooking);

    public byte[] exportRegisterBookingLodgingPdf(String idRegisterBooking);


    public RegisterBookingLodgingResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingLodgingResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);

}
