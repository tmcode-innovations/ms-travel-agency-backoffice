package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;

import java.util.List;

public interface ProformaInvoiceUseCase {

    public ProformaInvoiceResponse create(ProformaInvoiceCreateRequest request);

    public ProformaInvoiceResponse edit(String id, ProformaInvoiceUpdateRequest request);

    public void deleteById(String id);

    public List<ProformaInvoiceResponse> findAll();

    public ProformaInvoiceResponse findById(String id);

    public ProformaInvoiceSearchResponse searchAll(ProformaInvoiceSearchRequest bankSearchRequest);

    public ProformaInvoiceSearchResponse searchFilterAll(ProformaInvoiceSearchFilterRequest bankSearchFilterRequest);

    public ProformaDetailResponse addProformaDetail(ProformaDetailCreateRequest request, String idProforma);

    public ProformaDetailResponse editProformaDetail(String id, ProformaDetailUpdateRequest request, String idProforma);

    public void deleteProformaDetail(String id, String idProforma);

    public List<ProformaDetailResponse> findAllProformaDetailByProforma(String idProforma);

    public ProformaDetailResponse findByIdAndProforma(String id, String idProforma);

    public byte[] exportProformaInvoicePdf(String idProformaInvoice);

    public ProformaInvoiceResponse getProformaInvoiceModificationData(String idProformaInvoice);
}
