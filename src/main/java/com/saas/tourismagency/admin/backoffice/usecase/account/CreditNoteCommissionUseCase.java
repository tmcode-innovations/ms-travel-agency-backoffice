package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.CollectionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;

import java.util.List;

public interface CreditNoteCommissionUseCase {

    public CreditNoteCommissionResponse create(CreditNoteCommissionCreateRequest request);

    public CreditNoteCommissionResponse edit(String id, CreditNoteCommissionUpdateRequest request);

    public void deleteById(String id);

    public List<CreditNoteCommissionResponse> findAll();

    public CreditNoteCommissionResponse findById(String id);

    public CreditNoteCommissionSearchResponse searchAll(CreditNoteCommissionSearchRequest bankSearchRequest);

    public CreditNoteCommissionSearchResponse searchFilterAll(CreditNoteCommissionSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public CreditNoteCommissionResponse getCreditNoteCommissionModificationData(String idDocumentFinance);

    public byte[] exportCreditNoteCommissionPdf(String idCreditNoteCommission);
}
