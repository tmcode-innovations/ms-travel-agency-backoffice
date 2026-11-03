package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.*;

import java.util.List;

public interface SupplierTypeUseCase {

    public SupplierTypeResponse create(SupplierTypeCreateRequest request);

    public SupplierTypeResponse edit(String id, SupplierTypeUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierTypeResponse> findAll();

    public SupplierTypeResponse findById(String id);

    public SupplierTypeSearchResponse searchAll(SupplierTypeSearchRequest supplierTypeSearchRequest);

    public SupplierTypeSearchResponse searchFilterAll(SupplierTypeSearchFilterRequest supplierTypeSearchFilterRequest);

    public List<SupplierTypeResponse> findAllFromOperationOrderService();
}
