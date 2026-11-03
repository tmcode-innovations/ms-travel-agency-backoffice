package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.objectsecurity.*;

import java.util.List;

public interface ObjectUseCase {

    public ObjectResponse create(ObjectCreateRequest request);

    public ObjectResponse edit(String id, ObjectUpdateRequest request);

    public void deleteById(String id);

    public List<ObjectResponse> findAll();

    public ObjectResponse findById(String id);

    public ObjectSearchResponse searchAll(ObjectSearchRequest objectSearchRequest);

    public ObjectSearchResponse searchFilterAll(ObjectSearchFilterRequest objectSearchFilterRequest);

    public ObjectResponse findByName(String name);

    public ObjectResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

}
