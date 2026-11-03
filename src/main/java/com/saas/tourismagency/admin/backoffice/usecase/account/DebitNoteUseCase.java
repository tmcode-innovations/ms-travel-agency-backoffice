package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.CreditNoteResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;

import java.util.List;

public interface DebitNoteUseCase {

    public DebitNoteResponse create(DebitNoteCreateRequest request);

    public DebitNoteResponse edit(String id, DebitNoteUpdateRequest request);

    public void deleteById(String id);

    public List<DebitNoteResponse> findAll();

    public DebitNoteResponse findById(String id);

    public DebitNoteSearchResponse searchAll(DebitNoteSearchRequest bankSearchRequest);

    public DebitNoteSearchResponse searchFilterAll(DebitNoteSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public DebitNoteResponse getDebitNoteModificationData(String idDocumentFinance);

    public byte[] exportDebitNotePdf(String idDebitNote);
}
