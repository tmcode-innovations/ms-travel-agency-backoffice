package com.saas.tourismagency.admin.backoffice.usecase.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;

import java.util.List;

public interface CompanyUseCase {

    public CompanyResponse create(CompanyCreateRequest request);

    public CompanyResponse edit(String id, CompanyUpdateRequest request);

    public void deleteById(String id);

    public List<CompanyResponse> findAll();

    public CompanyResponse findById(String id);

    public CompanySearchResponse searchAll(CompanySearchRequest companySearchRequest);

    public CompanySearchResponse searchFilterAll(CompanySearchFilterRequest companySearchFilterRequest);

    public CompanyResponse findByName(String name);

    public CompanyResponse findByIdentify(String identify);

    public CompanyResponse findByTenant(String tenant);

    public CompanyDomain getMyActiveCompany();
}
