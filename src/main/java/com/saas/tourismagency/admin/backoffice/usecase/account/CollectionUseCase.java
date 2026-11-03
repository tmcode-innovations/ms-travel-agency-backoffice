package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.collection.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.VoucherIngressResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;

import java.util.List;

public interface CollectionUseCase {

    public CollectionResponse create(CollectionCreateRequest request);

    public CollectionResponse edit(String id, CollectionUpdateRequest request);

    public void deleteById(String id);

    public List<CollectionResponse> findAll();

    public CollectionResponse findById(String id);

    public CollectionSearchResponse searchAll(CollectionSearchRequest bankSearchRequest);

    public CollectionSearchResponse searchFilterAll(CollectionSearchFilterRequest bankSearchFilterRequest);

    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance);

    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance);

    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance);

    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance);

    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance);

    public CollectionResponse getCollectionModificationData(String idCollection);

    public byte[] exportCollectionPdf(String idCollection);

}
