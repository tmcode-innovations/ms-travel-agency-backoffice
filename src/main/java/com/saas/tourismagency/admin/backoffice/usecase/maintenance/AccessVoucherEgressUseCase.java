package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.*;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;

import java.util.List;

public interface AccessVoucherEgressUseCase {

    public AccessVoucherEgressResponse create(AccessVoucherEgressCreateRequest request);

    public AccessVoucherEgressResponse edit(String id, AccessVoucherEgressUpdateRequest request);

    public void deleteById(String id);

    public List<AccessVoucherEgressResponse> findAll();

    public AccessVoucherEgressResponse findById(String id);

    public AccessVoucherEgressDomain findByIdDomain(String id);

    public AccessVoucherEgressSearchResponse searchAll(AccessVoucherEgressSearchRequest searchRequest);

    public AccessVoucherEgressSearchResponse searchFilterAll(AccessVoucherEgressSearchFilterRequest searchFilterRequest);

    public boolean userWithAccessVoucherEgress(BaseSearchRequestById request);
}
