package com.saas.tourismagency.admin.backoffice.usecase.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.organization.OrganizationResponse;

import java.util.List;

public interface OrganizationUseCase {

    public List<OrganizationResponse> findAll();

    public OrganizationResponse findById(String id);

}
