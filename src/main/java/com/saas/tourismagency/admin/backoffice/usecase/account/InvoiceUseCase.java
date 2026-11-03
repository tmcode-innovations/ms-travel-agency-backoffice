package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.InvoiceCommissionResponse;

import java.util.List;

public interface InvoiceUseCase {

    public InvoiceResponse create(InvoiceCreateRequest request);

    public InvoiceResponse edit(String id, InvoiceUpdateRequest request);

    public void deleteById(String id);

    public List<InvoiceResponse> findAll();

    public InvoiceResponse findById(String id);

    public InvoiceSearchResponse searchAll(InvoiceSearchRequest bankSearchRequest);

    public InvoiceSearchResponse searchFilterAll(InvoiceSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public InvoiceResponse getInvoiceModificationData(String idDocumentFinance);

    public byte[] exportInvoicePdf(String idInvoice);
}
