package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.*;

import java.util.List;

public interface DocumentFinanceUseCase {

    public DocumentFinanceResponse create(DocumentFinanceCreateRequest request);

    public DocumentFinanceResponse edit(String id, DocumentFinanceUpdateRequest request);

    public void deleteById(String id);

    public List<DocumentFinanceResponse> findAll();

    public DocumentFinanceResponse findById(String id);

    public DocumentFinanceSearchResponse searchAll(DocumentFinanceSearchRequest bankSearchRequest);

    public DocumentFinanceSearchResponse searchFilterAll(DocumentFinanceSearchFilterRequest bankSearchFilterRequest);

}
