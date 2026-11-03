package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingroom.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;

import java.util.List;

public interface RegisterBookingRoomUseCase {

    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request);

    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request);

    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain);

    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain);

    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);

    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);

    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain);

    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain);


    public void deleteById(String id);

    public List<RegisterBookingRoomResponse> findAll();

    public RegisterBookingRoomResponse findById(String id);

    public RegisterBookingRoomSearchResponse searchAll(RegisterBookingRoomSearchRequest searchRequest);

    public RegisterBookingRoomSearchResponse searchFilterAll(RegisterBookingRoomSearchFilterRequest searchFilterRequest);

    public RegisterBookingRoomResponse findByName(String name);

    public RegisterBookingRoomResponse findByIdentify(String identify);


    public List<RegisterBookingRoomResponse> searchAllByBookingLodging(String idBooking);

    public List<RegisterBookingRoomResponse> searchAllByBookingGlamping(String idBooking);

    public List<RegisterBookingRoomResponse> searchAllByBookingLodger(String idBooking);

    public List<RegisterBookingRoomResponse> searchAllByBookingLodgingAndTypeRoomBooking(String idBooking, String typeRoomBooking);

    public List<RegisterBookingRoomResponse> searchAllByBookingLodgerAndTypeRoomBooking(String idBooking, String typeRoomBooking);

    public List<RegisterBookingRoomResponse> searchAllByBookingGlampingAndTypeRoomBooking(String idBooking, String typeRoomBooking);
}
