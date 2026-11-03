package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.*;

import java.util.List;

public interface ServicePqtTouristUseCase {

    public ServicePqtTouristResponse create(ServicePqtTouristCreateRequest request);

    public ServicePqtTouristResponse edit(String id, ServicePqtTouristUpdateRequest request);

    public void deleteById(String id);

    public List<ServicePqtTouristResponse> findAll();

    public ServicePqtTouristResponse findById(String id);

    public ServicePqtTouristSearchResponse searchAll(ServicePqtTouristSearchRequest bankSearchRequest);

    public ServicePqtTouristSearchResponse searchFilterAll(ServicePqtTouristSearchFilterRequest bankSearchFilterRequest);

    public ServicePqtTouristResponse findByName(String name);

    public ServicePqtTouristResponse findByIdentify(String identify);

}
