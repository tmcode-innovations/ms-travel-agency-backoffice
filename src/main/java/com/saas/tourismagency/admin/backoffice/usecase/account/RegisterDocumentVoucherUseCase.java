package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.*;

import java.util.List;

public interface RegisterDocumentVoucherUseCase {

    public RegisterDocumentVoucherResponse create(RegisterDocumentVoucherCreateRequest request);

    public RegisterDocumentVoucherResponse edit(String id, RegisterDocumentVoucherUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterDocumentVoucherResponse> findAll();

    public RegisterDocumentVoucherResponse findById(String id);

    public RegisterDocumentVoucherSearchResponse searchAll(RegisterDocumentVoucherSearchRequest bankSearchRequest);

    public RegisterDocumentVoucherSearchResponse searchFilterAll(RegisterDocumentVoucherSearchFilterRequest bankSearchFilterRequest);

}
