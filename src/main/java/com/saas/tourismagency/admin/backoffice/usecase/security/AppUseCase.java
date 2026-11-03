package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.*;

import java.util.List;

public interface AppUseCase {

    public AppResponse create(AppCreateRequest request);

    public AppResponse edit(String id, AppUpdateRequest request);

    public void deleteById(String id);

    public List<AppResponse> findAll();

    public AppResponse findById(String id);

    public AppSearchResponse searchAll(AppSearchRequest appSearchRequest);

    public AppSearchResponse searchFilterAll(AppSearchFilterRequest appSearchFilterRequest);

    public AppResponse findByName(String name);

    public AppResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();


}
