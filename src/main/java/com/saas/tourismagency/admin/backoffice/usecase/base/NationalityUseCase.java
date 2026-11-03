package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.*;

import java.util.List;

public interface NationalityUseCase {

    public NationalityResponse create(NationalityCreateRequest request);

    public NationalityResponse edit(String id, NationalityUpdateRequest request);

    public void deleteById(String id);

    public List<NationalityResponse> findAll();

    public NationalityResponse findById(String id);

    public NationalitySearchResponse searchAll(NationalitySearchRequest nationalitySearchRequest);

    public NationalitySearchResponse searchFilterAll(NationalitySearchFilterRequest nationalitySearchFilterRequest);

    public NationalityResponse findByName(String name);

    public List<SimpleBaseResponse> findAllSimple();
}
