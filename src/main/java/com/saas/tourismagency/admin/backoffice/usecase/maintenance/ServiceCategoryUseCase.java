package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.*;

import java.util.List;

public interface ServiceCategoryUseCase {

    public ServiceCategoryResponse create(ServiceCategoryCreateRequest request);

    public ServiceCategoryResponse edit(String id, ServiceCategoryUpdateRequest request);

    public void deleteById(String id);

    public List<ServiceCategoryResponse> findAll();

    public ServiceCategoryResponse findById(String id);

    public ServiceCategorySearchResponse searchAll(ServiceCategorySearchRequest customerSearchRequest);

    public ServiceCategorySearchResponse searchFilterAll(ServiceCategorySearchFilterRequest customerSearchFilterRequest);

    public ServiceCategoryResponse findByName(String name);

    public ServiceCategoryResponse findByIdentify(String identify);
}
