package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierLodgerUseCase {

    public SupplierLodgerResponse create(SupplierLodgerCreateRequest request);

    public SupplierLodgerResponse edit(String id, SupplierLodgerUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierLodgerResponse> findAll();

    public SupplierLodgerResponse findById(String id);

    public SupplierLodgerSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierLodgerSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierLodgerResponse findByName(String name);

    public SupplierLodgerResponse findByIdentify(String identify);

    public List<SupplierLodgerResponse> findAllSimple();

    public List<SupplierLodgerResponse> findAllSimpleVoucherEgress();
}
