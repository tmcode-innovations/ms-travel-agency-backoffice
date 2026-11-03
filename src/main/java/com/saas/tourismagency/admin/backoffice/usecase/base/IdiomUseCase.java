package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.*;

import java.util.List;

public interface IdiomUseCase {

    public IdiomResponse create(IdiomCreateRequest request);

    public IdiomResponse edit(String id, IdiomUpdateRequest request);

    public void deleteById(String id);

    public List<IdiomResponse> findAll();

    public IdiomResponse findById(String id);

    public IdiomSearchResponse searchAll(IdiomSearchRequest idiomSearchRequest);

    public IdiomSearchResponse searchFilterAll(IdiomSearchFilterRequest idiomSearchFilterRequest);

    public IdiomResponse findByName(String name);

    public List<SimpleBaseResponse> findAllSimple();
}
