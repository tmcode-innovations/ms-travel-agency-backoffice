package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.*;

import java.util.List;

public interface PersonUseCase {

    public PersonResponse create(PersonCreateRequest request);

    public PersonResponse edit(String id, PersonUpdateRequest request);

    public void deleteById(String id);

    public List<PersonResponse> findAll();

    public PersonResponse findById(String id);

    public PersonSearchResponse searchAll(PersonSearchRequest aeaSearchRequest);

    public PersonSearchResponse searchFilterAll(PersonSearchFilterRequest aeaSearchFilterRequest);

}
