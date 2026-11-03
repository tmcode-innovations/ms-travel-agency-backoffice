package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;

import java.util.List;

public interface RegisterBookingRestaurantUseCase {

    public RegisterBookingRestaurantResponse create(RegisterBookingRestaurantCreateRequest request);

    public RegisterBookingRestaurantResponse edit(String id, RegisterBookingRestaurantUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingRestaurantResponse> findAll();

    public RegisterBookingRestaurantResponse findById(String id);

    public RegisterBookingRestaurantSearchResponse searchAll(RegisterBookingRestaurantSearchRequest bankSearchRequest);

    public RegisterBookingRestaurantSearchResponse searchFilterAll(RegisterBookingRestaurantSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingRestaurantResponse findByName(String name);

    public RegisterBookingRestaurantResponse findByIdentify(String identify);

    public RegisterBookingRestaurantResponse generate(String idRegisterGroup, String idSupplier);


    public List<RegisterBookingItemRestaurantResponse> findAllRegisterBookingItemRestaurantByBooking(String idBooking);

    public RegisterBookingItemRestaurantResponse addRegisterBookingItemRestaurant(RegisterBookingItemRestaurantCreateRequest request, String idBooking);

    public RegisterBookingItemRestaurantResponse editRegisterBookingItemRestaurant(String id, RegisterBookingItemRestaurantUpdateRequest request, String idBooking);

    public void deleteRegisterBookingItemRestaurant(String id, String idBooking);

    public RegisterBookingItemRestaurantResponse findByIdAndBooking(String id, String idBooking);


    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking);

    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking);

    public void deletePassengerBooking(String id, String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking);

    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking);

    // Export Data

    public byte[] exportRegisterBookingRestaurantCuponPdf(String idRegisterBooking);

    public byte[] exportRegisterBookingRestaurantPdf(String idRegisterBooking);


    public RegisterBookingRestaurantResponse changeState(String id, RegisterBookingChangeStateRequest request);

    public RegisterBookingRestaurantResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request);
}
