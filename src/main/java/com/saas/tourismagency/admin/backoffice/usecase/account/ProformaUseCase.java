package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;

import java.util.List;

public interface ProformaUseCase {

    public ProformaResponse create(ProformaCreateRequest request);

    public ProformaResponse edit(String id, ProformaUpdateRequest request);

    public void deleteById(String id);

    public List<ProformaResponse> findAll();

    public ProformaResponse findById(String id);

    public ProformaSearchResponse searchAll(ProformaSearchRequest bankSearchRequest);

    public ProformaSearchResponse searchFilterAll(ProformaSearchFilterRequest bankSearchFilterRequest);

    public ProformaDetailResponse addProformaDetail(ProformaDetailCreateRequest request, String idProforma);

    public ProformaDetailResponse editProformaDetail(String id, ProformaDetailUpdateRequest request, String idProforma);

    public void deleteProformaDetail(String id, String idProforma);

    public List<ProformaDetailResponse> findAllProformaDetailByProforma(String idProforma);

    public ProformaDetailResponse findByIdAndProforma(String id, String idProforma);
}
