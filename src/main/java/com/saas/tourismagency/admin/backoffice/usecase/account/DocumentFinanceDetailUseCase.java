package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;

import java.util.List;

public interface DocumentFinanceDetailUseCase {

    public DocumentFinanceDetailResponse create(DocumentFinanceDetailCreateRequest request);

    public DocumentFinanceDetailResponse create(DocumentFinanceDetailCreateRequest request, DocumentFinanceDomain documentFinanceDomain);

    public DocumentFinanceDetailResponse edit(String id, DocumentFinanceDetailUpdateRequest request);

    public DocumentFinanceDetailResponse edit(String id, DocumentFinanceDetailUpdateRequest request, DocumentFinanceDomain documentFinanceDomain);

    public void deleteById(String id);

    public List<DocumentFinanceDetailResponse> findAll();

    public DocumentFinanceDetailResponse findById(String id);

    public DocumentFinanceDetailSearchResponse searchAll(DocumentFinanceDetailSearchRequest bankSearchRequest);

    public DocumentFinanceDetailSearchResponse searchFilterAll(DocumentFinanceDetailSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse searchByDocumentFinanceAndDocumentFinanceDetail(String idDocumentFinance, String idDocumentFinanceDetail);
}
