package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;

import java.util.List;

public interface RegisterBookingOperatorUseCase {

    public RegisterBookingOperatorResponse create(RegisterBookingOperatorCreateRequest request);

    public RegisterBookingOperatorResponse edit(String id, RegisterBookingOperatorUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingOperatorResponse> findAll();

    public RegisterBookingOperatorResponse findById(String id);

    public RegisterBookingOperatorSearchResponse searchAll(RegisterBookingOperatorSearchRequest bankSearchRequest);

    public RegisterBookingOperatorSearchResponse searchFilterAll(RegisterBookingOperatorSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingOperatorResponse findByName(String name);

    public RegisterBookingOperatorResponse findByIdentify(String identify);

    public RegisterBookingOperatorResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorByBooking(String idBooking);

    public RegisterBookingItemOperatorResponse addRegisterBookingItemOperator(RegisterBookingItemOperatorCreateRequest request, String idBooking);

    public RegisterBookingItemOperatorResponse editRegisterBookingItemOperator(String id, RegisterBookingItemOperatorUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemOperator(String id, String idBooking);

    public RegisterBookingItemOperatorResponse findByIdAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking);

    // Export Data

    public byte[] exportRegisterBookingOperatorCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingOperatorPdf(String idRegisterBooking);


    public RegisterBookingOperatorResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingOperatorResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);

}
