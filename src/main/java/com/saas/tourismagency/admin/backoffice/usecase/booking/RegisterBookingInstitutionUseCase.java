package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionUpdateRequest;

import java.util.List;

public interface RegisterBookingInstitutionUseCase {

    public RegisterBookingInstitutionResponse create(RegisterBookingInstitutionCreateRequest request);

    public RegisterBookingInstitutionResponse edit(String id, RegisterBookingInstitutionUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingInstitutionResponse> findAll();

    public RegisterBookingInstitutionResponse findById(String id);

    public RegisterBookingInstitutionSearchResponse searchAll(RegisterBookingInstitutionSearchRequest searchRequest);

    public RegisterBookingInstitutionSearchResponse searchFilterAll(RegisterBookingInstitutionSearchFilterRequest searchFilterRequest);

    public RegisterBookingInstitutionResponse findByName(String name);

    public RegisterBookingInstitutionResponse findByIdentify(String identify);

    public RegisterBookingInstitutionResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemInstitutionResponse> findAllRegisterBookingItemInstitutionByBooking(String idBooking);

    public RegisterBookingItemInstitutionResponse addRegisterBookingItemInstitution(RegisterBookingItemInstitutionCreateRequest request, String idBooking);

    public RegisterBookingItemInstitutionResponse editRegisterBookingItemInstitution(String id, RegisterBookingItemInstitutionUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemInstitution(String id, String idBooking);

    public RegisterBookingItemInstitutionResponse findByIdAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking);

    // Export Data

    public byte[] exportRegisterBookingInstitutionCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingInstitutionPdf(String idRegisterBooking);


    public RegisterBookingInstitutionResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingInstitutionResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);

}
