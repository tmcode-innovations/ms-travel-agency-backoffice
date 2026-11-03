package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.*;

import java.util.List;

public interface CountryUseCase {

    public CountryResponse create(CountryCreateRequest request);

    public CountryResponse edit(String id, CountryUpdateRequest request);

    public void deleteById(String id);

    public List<CountryResponse> findAll();

    public CountryResponse findById(String id);

    public CountrySearchResponse searchAll(CountrySearchRequest countrySearchRequest);

    public CountrySearchResponse searchFilterAll(CountrySearchFilterRequest countrySearchFilterRequest);

}
