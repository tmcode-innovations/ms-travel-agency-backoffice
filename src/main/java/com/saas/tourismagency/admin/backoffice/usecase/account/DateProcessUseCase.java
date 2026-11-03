package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.*;

import java.util.List;

public interface DateProcessUseCase {

    public DateProcessResponse create(DateProcessCreateRequest request);

    public DateProcessResponse edit(String id, DateProcessUpdateRequest request);

    public void deleteById(String id);

    public List<DateProcessResponse> findAll();

    public DateProcessResponse findById(String id);

    public DateProcessSearchResponse searchAll(DateProcessSearchRequest bankSearchRequest);

    public DateProcessSearchResponse searchFilterAll(DateProcessSearchFilterRequest bankSearchFilterRequest);

    public DateProcessResponse findDateProcessCurrent();

}
