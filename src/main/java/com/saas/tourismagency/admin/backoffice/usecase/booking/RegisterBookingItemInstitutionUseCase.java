package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingInstitutionDomain;

import java.util.List;

public interface RegisterBookingItemInstitutionUseCase {

    public RegisterBookingItemInstitutionResponse create(RegisterBookingItemInstitutionCreateRequest request);

    public RegisterBookingItemInstitutionResponse edit(String id, RegisterBookingItemInstitutionUpdateRequest request);

    public RegisterBookingItemInstitutionResponse create(RegisterBookingItemInstitutionCreateRequest request, RegisterBookingInstitutionDomain registerBookingInstitutionDomain);

    public RegisterBookingItemInstitutionResponse edit(String id, RegisterBookingItemInstitutionUpdateRequest request, RegisterBookingInstitutionDomain registerBookingInstitutionDomain);

    public void deleteById(String id);

    public List<RegisterBookingItemInstitutionResponse> findAll();

    public RegisterBookingItemInstitutionResponse findById(String id);

    public RegisterBookingItemInstitutionSearchResponse searchAll(RegisterBookingItemInstitutionSearchRequest searchRequest);

    public RegisterBookingItemInstitutionSearchResponse searchFilterAll(RegisterBookingItemInstitutionSearchFilterRequest searchFilterRequest);

    public RegisterBookingItemInstitutionResponse findByName(String name);

    public RegisterBookingItemInstitutionResponse findByIdentify(String identify);

    public List<RegisterBookingItemInstitutionResponse> searchAllByBooking(String idBooking);

    public RegisterBookingItemInstitutionResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem);
}
