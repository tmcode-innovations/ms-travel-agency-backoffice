package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.*;

import java.util.List;

public interface PartnerUseCase {

    public PartnerResponse create(PartnerCreateRequest request);

    public PartnerResponse edit(String id, PartnerUpdateRequest request);

    public void deleteById(String id);

    public List<PartnerResponse> findAll();

    public PartnerResponse findById(String id);

    public PartnerSearchResponse searchAll(PartnerSearchRequest aeaSearchRequest);

    public PartnerSearchResponse searchFilterAll(PartnerSearchFilterRequest aeaSearchFilterRequest);

}
