package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingUpdateRequest;

import java.util.List;

public interface RegisterBookingGlampingUseCase {

    public RegisterBookingGlampingResponse create(RegisterBookingGlampingCreateRequest request);

    public RegisterBookingGlampingResponse edit(String id, RegisterBookingGlampingUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingGlampingResponse> findAll();

    public RegisterBookingGlampingResponse findById(String id);

    public RegisterBookingGlampingSearchResponse searchAll(RegisterBookingGlampingSearchRequest bankSearchRequest);

    public RegisterBookingGlampingSearchResponse searchFilterAll(RegisterBookingGlampingSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingGlampingResponse findByName(String name);

    public RegisterBookingGlampingResponse findByIdentify(String identify);

    public RegisterBookingGlampingResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemGlampingResponse> findAllRegisterBookingItemGlampingByBooking(String idBooking);

    public RegisterBookingItemGlampingResponse addRegisterBookingItemGlamping(RegisterBookingItemGlampingCreateRequest request, String idBooking);

    public RegisterBookingItemGlampingResponse editRegisterBookingItemGlamping(String id, RegisterBookingItemGlampingUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemGlamping(String id, String idBooking);

    public RegisterBookingItemGlampingResponse findByIdAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking);

    // Export Data

    public byte[] exportRegisterBookingGlampingCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingGlampingItemCuponPdf(String idRegisterBookingItem);

    public byte[] exportRegisterBookingGlampingPdf(String idRegisterBooking);


    public RegisterBookingGlampingResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingGlampingResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);

}
