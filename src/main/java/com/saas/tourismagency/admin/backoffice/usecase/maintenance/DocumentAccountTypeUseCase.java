package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.*;

import java.util.List;

public interface DocumentAccountTypeUseCase {

    public DocAccountTypeResponse create(DocAccountTypeCreateRequest request);

    public DocAccountTypeResponse edit(String id, DocAccountTypeUpdateRequest request);

    public void deleteById(String id);

    public List<DocAccountTypeResponse> findAll();

    public DocAccountTypeResponse findById(String id);

    public DocAccountTypeSearchResponse searchAll(DocAccountTypeSearchRequest docAccountTypeSearchRequest);

    public DocAccountTypeSearchResponse searchFilterAll(DocAccountTypeSearchFilterRequest docAccountTypeSearchFilterRequest);

    public DocAccountTypeResponse findByName(String name);

    public DocAccountTypeResponse findByIdentify(String identify);
}
