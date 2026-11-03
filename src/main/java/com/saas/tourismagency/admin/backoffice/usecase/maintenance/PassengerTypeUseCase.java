package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.*;

import java.util.List;

public interface PassengerTypeUseCase {

    public PassengerTypeResponse create(PassengerTypeCreateRequest request);

    public PassengerTypeResponse edit(String id, PassengerTypeUpdateRequest request);

    public void deleteById(String id);

    public List<PassengerTypeResponse> findAll();

    public PassengerTypeResponse findById(String id);

    public PassengerTypeSearchResponse searchAll(PassengerTypeSearchRequest searchRequest);

    public PassengerTypeSearchResponse searchFilterAll(PassengerTypeSearchFilterRequest searchFilterRequest);

}
