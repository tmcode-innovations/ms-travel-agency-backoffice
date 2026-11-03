package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.*;

import java.util.List;

public interface GroupPqtTouristUseCase {

    public GroupPqtTouristResponse create(GroupPqtTouristCreateRequest request);

    public GroupPqtTouristResponse edit(String id, GroupPqtTouristUpdateRequest request);

    public void deleteById(String id);

    public List<GroupPqtTouristResponse> findAll();

    public GroupPqtTouristResponse findById(String id);

    public GroupPqtTouristSearchResponse searchAll(GroupPqtTouristSearchRequest bankSearchRequest);

    public GroupPqtTouristSearchResponse searchFilterAll(GroupPqtTouristSearchFilterRequest bankSearchFilterRequest);

    public GroupPqtTouristResponse findByName(String name);

    public GroupPqtTouristResponse findByIdentify(String identify);

}
