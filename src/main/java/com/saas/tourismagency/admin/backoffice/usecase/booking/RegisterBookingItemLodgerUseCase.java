package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgerDomain;

import java.util.List;

public interface RegisterBookingItemLodgerUseCase {

    public RegisterBookingItemLodgerResponse create(RegisterBookingItemLodgerCreateRequest request);

    public RegisterBookingItemLodgerResponse edit(String id, RegisterBookingItemLodgerUpdateRequest request);

    public RegisterBookingItemLodgerResponse create(RegisterBookingItemLodgerCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);

    public RegisterBookingItemLodgerResponse edit(String id, RegisterBookingItemLodgerUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain);

    public void deleteById(String id);

    public List<RegisterBookingItemLodgerResponse> findAll();

    public RegisterBookingItemLodgerResponse findById(String id);

    public RegisterBookingItemLodgerSearchResponse searchAll(RegisterBookingItemLodgerSearchRequest bankSearchRequest);

    public RegisterBookingItemLodgerSearchResponse searchFilterAll(RegisterBookingItemLodgerSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingItemLodgerResponse findByName(String name);

    public RegisterBookingItemLodgerResponse findByIdentify(String identify);


    public List<RegisterBookingItemLodgerResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemLodgerResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
