package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;

import java.util.List;

public interface RegisterBookingItemLodgerLodgingUseCase {

    public RegisterBookingItemLodgerLodgingResponse create(RegisterBookingItemLodgerLodgingCreateRequest request);

    public RegisterBookingItemLodgerLodgingResponse edit(String id, RegisterBookingItemLodgerLodgingUpdateRequest request);

    public RegisterBookingItemLodgerLodgingResponse create(RegisterBookingItemLodgerLodgingCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);

    public RegisterBookingItemLodgerLodgingResponse edit(String id, RegisterBookingItemLodgerLodgingUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);


    public void deleteById(String id);

    public List<RegisterBookingItemLodgerLodgingResponse> findAll();

    public RegisterBookingItemLodgerLodgingResponse findById(String id);

    public RegisterBookingItemLodgerLodgingSearchResponse searchAll(RegisterBookingItemLodgerLodgingSearchRequest bankSearchRequest);

    public RegisterBookingItemLodgerLodgingSearchResponse searchFilterAll(RegisterBookingItemLodgerLodSearchFilterReq bankSearchFilterRequest);

    public RegisterBookingItemLodgerLodgingResponse findByName(String name);

    public RegisterBookingItemLodgerLodgingResponse findByIdentify(String identify);


    public List<RegisterBookingItemLodgerLodgingResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemLodgerLodgingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
