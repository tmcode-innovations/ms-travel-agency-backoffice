package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.*;

import java.util.List;

public interface CustomerUseCase {

    public CustomerResponse create(CustomerCreateRequest request);

    public CustomerResponse edit(String id, CustomerUpdateRequest request);

    public void deleteById(String id);

    public List<CustomerResponse> findAll();

    public CustomerResponse findById(String id);

    public CustomerSearchResponse searchAll(CustomerSearchRequest customerSearchRequest);

    public CustomerSearchResponse searchFilterAll(CustomerSearchFilterRequest customerSearchFilterRequest);

    public CustomerResponse findByName(String name);

    public CustomerResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();
}
