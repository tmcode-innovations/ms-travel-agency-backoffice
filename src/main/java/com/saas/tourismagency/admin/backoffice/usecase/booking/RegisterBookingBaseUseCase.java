package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.*;

import java.util.List;

public interface RegisterBookingBaseUseCase {

    public RegisterBookingBaseResponse create(RegisterBookingBaseCreateRequest request);

    public RegisterBookingBaseResponse edit(String id, RegisterBookingBaseUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingBaseResponse> findAll();

    public RegisterBookingBaseResponse findById(String id);

    public RegisterBookingBaseSearchResponse searchAll(RegisterBookingBaseSearchRequest bankSearchRequest);

    public RegisterBookingBaseSearchResponse searchFilterAll(RegisterBookingBaseSearchFilterRequest bankSearchFilterRequest);

}
