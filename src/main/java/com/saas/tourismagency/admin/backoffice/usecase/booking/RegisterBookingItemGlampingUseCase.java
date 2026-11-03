package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;

import java.util.List;

public interface RegisterBookingItemGlampingUseCase {

    public RegisterBookingItemGlampingResponse create(RegisterBookingItemGlampingCreateRequest request);

    public RegisterBookingItemGlampingResponse edit(String id, RegisterBookingItemGlampingUpdateRequest request);

    public RegisterBookingItemGlampingResponse create(RegisterBookingItemGlampingCreateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain);

    public RegisterBookingItemGlampingResponse edit(String id, RegisterBookingItemGlampingUpdateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain);


    public void deleteById(String id);

    public List<RegisterBookingItemGlampingResponse> findAll();

    public RegisterBookingItemGlampingResponse findById(String id);

    public RegisterBookingItemGlampingSearchResponse searchAll(RegisterBookingItemGlampingSearchRequest bankSearchRequest);

    public RegisterBookingItemGlampingSearchResponse searchFilterAll(RegisterBookingItemGlampingSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingItemGlampingResponse findByName(String name);

    public RegisterBookingItemGlampingResponse findByIdentify(String identify);


    public List<RegisterBookingItemGlampingResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemGlampingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
