package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.*;

import java.util.List;

public interface DetractionUseCase {

    public DetractionResponse create(DetractionCreateRequest request);

    public DetractionResponse edit(String id, DetractionUpdateRequest request);

    public void deleteById(String id);

    public List<DetractionResponse> findAll();

    public DetractionResponse findById(String id);

    public DetractionSearchResponse searchAll(DetractionSearchRequest detractionSearchRequest);

    public DetractionSearchResponse searchFilterAll(DetractionSearchFilterRequest detractionSearchFilterRequest);

    public DetractionResponse changeAvailable(String id, Boolean available);

    public DetractionResponse findByName(String name);
}
