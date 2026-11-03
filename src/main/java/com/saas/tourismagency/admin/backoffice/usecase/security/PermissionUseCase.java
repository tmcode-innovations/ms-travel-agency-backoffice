package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission.*;

import java.util.List;

public interface PermissionUseCase {

    public PermissionResponse create(PermissionCreateRequest request);

    public PermissionResponse edit(String id, PermissionUpdateRequest request);

    public void deleteById(String id);

    public List<PermissionResponse> findAll();

    public PermissionResponse findById(String id);

    public PermissionSearchResponse searchAll(PermissionSearchRequest permissionSearchRequest);

    public PermissionSearchResponse searchFilterAll(PermissionSearchFilterRequest permissionSearchFilterRequest);

    public PermissionResponse findByName(String name);

    public PermissionResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

}
