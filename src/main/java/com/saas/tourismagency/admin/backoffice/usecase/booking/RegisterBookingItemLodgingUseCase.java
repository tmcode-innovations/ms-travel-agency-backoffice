package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;

import java.util.List;

public interface RegisterBookingItemLodgingUseCase {

    public RegisterBookingItemLodgingResponse create(RegisterBookingItemLodgingCreateRequest request);

    public RegisterBookingItemLodgingResponse edit(String id, RegisterBookingItemLodgingUpdateRequest request);

    public RegisterBookingItemLodgingResponse create(RegisterBookingItemLodgingCreateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain);

    public RegisterBookingItemLodgingResponse edit(String id, RegisterBookingItemLodgingUpdateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain);


    public void deleteById(String id);

    public List<RegisterBookingItemLodgingResponse> findAll();

    public RegisterBookingItemLodgingResponse findById(String id);

    public RegisterBookingItemLodgingSearchResponse searchAll(RegisterBookingItemLodgingSearchRequest bankSearchRequest);

    public RegisterBookingItemLodgingSearchResponse searchFilterAll(RegisterBookingItemLodgingSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingItemLodgingResponse findByName(String name);

    public RegisterBookingItemLodgingResponse findByIdentify(String identify);


    public List<RegisterBookingItemLodgingResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemLodgingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
