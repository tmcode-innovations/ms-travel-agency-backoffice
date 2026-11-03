package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingRestaurantDomain;

import java.util.List;

public interface RegisterBookingItemRestaurantUseCase {

    public RegisterBookingItemRestaurantResponse create(RegisterBookingItemRestaurantCreateRequest request);

    public RegisterBookingItemRestaurantResponse edit(String id, RegisterBookingItemRestaurantUpdateRequest request);

    public RegisterBookingItemRestaurantResponse create(RegisterBookingItemRestaurantCreateRequest request, RegisterBookingRestaurantDomain registerBookingRestaurantDomain);

    public RegisterBookingItemRestaurantResponse edit(String id, RegisterBookingItemRestaurantUpdateRequest request, RegisterBookingRestaurantDomain registerBookingRestaurantDomain);

    public void deleteById(String id);

    public List<RegisterBookingItemRestaurantResponse> findAll();

    public RegisterBookingItemRestaurantResponse findById(String id);

    public RegisterBookingItemRestaurantSearchResponse searchAll(RegisterBookingItemRestaurantSearchRequest bankSearchRequest);

    public RegisterBookingItemRestaurantSearchResponse searchFilterAll(RegisterBookingItemRestaurantSearchFilterRequest bankSearchFilterRequest);

    public RegisterBookingItemRestaurantResponse findByName(String name);

    public RegisterBookingItemRestaurantResponse findByIdentify(String identify);

    public List<RegisterBookingItemRestaurantResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemRestaurantResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
