package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.*;

import java.util.List;

public interface CurrencyUseCase {

    public CurrencyResponse create(CurrencyCreateRequest request);

    public CurrencyResponse edit(String id, CurrencyUpdateRequest request);

    public void deleteById(String id);

    public List<CurrencyResponse> findAll();

    public CurrencyResponse findById(String id);

    public CurrencySearchResponse searchAll(CurrencySearchRequest bankSearchRequest);

    public CurrencySearchResponse searchFilterAll(CurrencySearchFilterRequest bankSearchFilterRequest);

    public CurrencyResponse findByName(String name);

    public CurrencyResponse findByIdentify(String identify);
}
