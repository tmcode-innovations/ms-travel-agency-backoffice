package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierGuideUseCase {

    public SupplierGuideResponse create(SupplierGuideCreateRequest request);

    public SupplierGuideResponse edit(String id, SupplierGuideUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierGuideResponse> findAll();

    public SupplierGuideResponse findById(String id);

    public SupplierGuideSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierGuideSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierGuideResponse findByName(String name);

    public SupplierGuideResponse findByIdentify(String identify);

    public List<SupplierGuideResponse> findAllSimple();

    public List<SupplierGuideResponse> findAllSimpleVoucherEgress();
}
