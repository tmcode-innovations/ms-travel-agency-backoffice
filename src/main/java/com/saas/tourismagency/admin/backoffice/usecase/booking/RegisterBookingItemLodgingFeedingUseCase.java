package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.*;

import java.util.List;

public interface RegisterBookingItemLodgingFeedingUseCase {

    public RegisterBookingItemLodgingFeedingResponse create(RegisterBookingItemLodgingFeedingCreateRequest request);

    public RegisterBookingItemLodgingFeedingResponse edit(String id, RegisterBookingItemLodgingFeedingUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterBookingItemLodgingFeedingResponse> findAll();

    public RegisterBookingItemLodgingFeedingResponse findById(String id);

    public RegisterBookingItemLodgingFeedingSearchResponse searchAll(RegisterBookingItemLodgingFeedingSearchRequest bankSearchRequest);

    public RegisterBookingItemLodgingFeedingSearchResponse searchFilterAll(RegisterBookingItemLodgingFeedSearchFilterReq bankSearchFilterRequest);

    public RegisterBookingItemLodgingFeedingResponse findByName(String name);

    public RegisterBookingItemLodgingFeedingResponse findByIdentify(String identify);
}
