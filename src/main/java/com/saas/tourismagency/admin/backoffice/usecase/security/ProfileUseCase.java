package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.*;

import java.util.List;

public interface ProfileUseCase {

    public ProfileResponse create(ProfileCreateRequest request);

    public ProfileResponse edit(String id, ProfileUpdateRequest request);

    public void deleteById(String id);

    public List<ProfileResponse> findAll();

    public ProfileResponse findById(String id);

    public ProfileSearchResponse searchAll(ProfileSearchRequest profileSearchRequest);

    public ProfileSearchResponse searchFilterAll(ProfileSearchFilterRequest profileSearchFilterRequest);

    public ProfileResponse findByName(String name);

    public ProfileResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

}
