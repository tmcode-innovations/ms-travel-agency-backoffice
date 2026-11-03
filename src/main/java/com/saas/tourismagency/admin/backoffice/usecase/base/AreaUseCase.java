package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankResponse;

import java.util.List;

public interface AreaUseCase {

    public AreaResponse create(AreaCreateRequest request);

    public AreaResponse edit(String id, AreaUpdateRequest request);

    public void deleteById(String id);

    public List<AreaResponse> findAll();

    public AreaResponse findById(String id);

    public AreaSearchResponse searchAll(AreaSearchRequest aeaSearchRequest);

    public AreaSearchResponse searchFilterAll(AreaSearchFilterRequest aeaSearchFilterRequest);

    public AreaResponse findByName(String name);

    public AreaResponse findByIdentify(String identify);

}
