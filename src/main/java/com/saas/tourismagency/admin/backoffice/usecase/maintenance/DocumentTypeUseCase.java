package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.*;

import java.util.List;

public interface DocumentTypeUseCase {

    public DocumentTypeResponse create(DocumentTypeCreateRequest request);

    public DocumentTypeResponse edit(String id, DocumentTypeUpdateRequest request);

    public void deleteById(String id);

    public List<DocumentTypeResponse> findAll();

    public DocumentTypeResponse findById(String id);

    public DocumentTypeSearchResponse searchAll(DocumentTypeSearchRequest documentTypeSearchRequest);

    public DocumentTypeSearchResponse searchFilterAll(DocumentTypeSearchFilterRequest documentTypeSearchFilterRequest);

    public DocumentTypeResponse findByName(String name);

    public DocumentTypeResponse findByIdentify(String identify);
}
