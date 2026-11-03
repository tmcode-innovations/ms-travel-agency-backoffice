package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.*;

import java.util.List;

public interface TransportTypeUseCase {

    public TransportTypeResponse create(TransportTypeCreateRequest request);

    public TransportTypeResponse edit(String id, TransportTypeUpdateRequest request);

    public void deleteById(String id);

    public List<TransportTypeResponse> findAll();

    public TransportTypeResponse findById(String id);

    public TransportTypeSearchResponse searchAll(TransportTypeSearchRequest transportTypeSearchRequest);

    public TransportTypeSearchResponse searchFilterAll(TransportTypeSearchFilterRequest transportTypeSearchFilterRequest);

    public TransportTypeResponse findByName(String name);

    public TransportTypeResponse findByIdentify(String identify);
}
