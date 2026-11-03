package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.DebitNoteResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.*;

import java.util.List;

public interface InvoiceCommissionUseCase {

    public InvoiceCommissionResponse create(InvoiceCommissionCreateRequest request);

    public InvoiceCommissionResponse edit(String id, InvoiceCommissionUpdateRequest request);

    public void deleteById(String id);

    public List<InvoiceCommissionResponse> findAll();

    public InvoiceCommissionResponse findById(String id);

    public InvoiceCommissionSearchResponse searchAll(InvoiceCommissionSearchRequest bankSearchRequest);

    public InvoiceCommissionSearchResponse searchFilterAll(InvoiceCommissionSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public InvoiceCommissionResponse getInvoiceCommissionModificationData(String idDocumentFinance);

    public byte[] exportInvoiceCommissionPdf(String idInvoiceCommission);
}
