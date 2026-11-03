package com.saas.tourismagency.admin.backoffice.usecase.external;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external.TenantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external.TenantResponse;

public interface TenantUseCase {

    public TenantResponse create(TenantCreateRequest request);

}
