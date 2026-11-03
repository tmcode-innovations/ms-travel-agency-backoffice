package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;

import java.util.List;

public interface CreditNoteUseCase {

    public CreditNoteResponse create(CreditNoteCreateRequest request);

    public CreditNoteResponse edit(String id, CreditNoteUpdateRequest request);

    public void deleteById(String id);

    public List<CreditNoteResponse> findAll();

    public CreditNoteResponse findById(String id);

    public CreditNoteSearchResponse searchAll(CreditNoteSearchRequest bankSearchRequest);

    public CreditNoteSearchResponse searchFilterAll(CreditNoteSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public CreditNoteResponse getCreditNoteModificationData(String idDocumentFinance);

    public byte[] exportCreditNotePdf(String idCreditNote);

}
