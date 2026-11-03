package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;

import java.util.List;

public interface RegisterBookingItemOperatorUseCase {

    public RegisterBookingItemOperatorResponse create(RegisterBookingItemOperatorCreateRequest request);

    public RegisterBookingItemOperatorResponse edit(String id, RegisterBookingItemOperatorUpdateRequest request);

    public RegisterBookingItemOperatorResponse create(RegisterBookingItemOperatorCreateRequest request, RegisterBookingOperatorDomain registerBookingOperatorDomain);

    public RegisterBookingItemOperatorResponse edit(String id, RegisterBookingItemOperatorUpdateRequest request, RegisterBookingOperatorDomain registerBookingOperatorDomain);

    public void deleteById(String id);

    public List<RegisterBookingItemOperatorResponse> findAll();

    public RegisterBookingItemOperatorResponse findById(String id);

    public RegisterBookingItemOperatorSearchResponse searchAll(RegisterBookingItemOperatorSearchRequest bankSearchRequest);

    public RegisterBookingItemOperatorSearchResponse searchFilterAll(RegisterBookingItemOperatorSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingItemOperatorResponse findByName(String name);

    public RegisterBookingItemOperatorResponse findByIdentify(String identify);

    public List<RegisterBookingItemOperatorResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemOperatorResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
