package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.*;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;

import java.util.List;

public interface ProformaDetailUseCase {

    public ProformaDetailResponse create(ProformaDetailCreateRequest request);

    public ProformaDetailResponse edit(String id, ProformaDetailUpdateRequest request);

    public void deleteById(String id);

    public List<ProformaDetailResponse> findAll();

    public ProformaDetailResponse findById(String id);

    public ProformaDetailSearchResponse searchAll(ProformaDetailSearchRequest bankSearchRequest);

    public ProformaDetailSearchResponse searchFilterAll(ProformaDetailSearchFilterRequest bankSearchFilterRequest);

    public ProformaDetailResponse create(ProformaDetailCreateRequest request, ProformaDomain proformaDomain);

    public ProformaDetailResponse edit(String id, ProformaDetailUpdateRequest request, ProformaDomain proformaDomain);

    public ProformaDetailResponse searchByProformaAndProformaDetail(String idProforma, String idProformaDetail);

    public void recalculateProformaTotals(String idProforma);
}
