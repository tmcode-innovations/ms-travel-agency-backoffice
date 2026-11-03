package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.*;

import java.util.List;

public interface IgvUseCase {

    public IgvResponse create(IgvCreateRequest request);

    public IgvResponse edit(String id, IgvUpdateRequest request);

    public void deleteById(String id);

    public List<IgvResponse> findAll();

    public IgvResponse findById(String id);

    public IgvSearchResponse searchAll(IgvSearchRequest igvSearchRequest);

    public IgvSearchResponse searchFilterAll(IgvSearchFilterRequest igvSearchFilterRequest);

    public IgvResponse changeAvailable(String id, Boolean available);

    public IgvResponse findByName(String name);

    public IgvResponse findAvailable();
}
