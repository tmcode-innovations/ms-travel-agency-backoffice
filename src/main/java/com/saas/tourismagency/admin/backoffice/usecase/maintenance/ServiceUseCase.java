package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.*;

import java.util.List;

public interface ServiceUseCase {

    public ServiceResponse create(ServiceCreateRequest request);

    public ServiceResponse edit(String id, ServiceUpdateRequest request);

    public void deleteById(String id);

    public List<ServiceResponse> findAll();

    public ServiceResponse findById(String id);

    public ServiceSearchResponse searchAll(ServiceSearchRequest customerSearchRequest);

    public ServiceSearchResponse searchFilterAll(ServiceSearchFilterRequest customerSearchFilterRequest);

    public ServiceResponse findByName(String name);

    public ServiceResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();
}
