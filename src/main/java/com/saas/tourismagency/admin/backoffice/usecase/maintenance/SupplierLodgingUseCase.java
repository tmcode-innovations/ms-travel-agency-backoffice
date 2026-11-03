package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierLodgingUseCase {

    public SupplierLodgingResponse create(SupplierLodgingCreateRequest request);

    public SupplierLodgingResponse edit(String id, SupplierLodgingUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierLodgingResponse> findAll();

    public SupplierLodgingResponse findById(String id);

    public SupplierLodgingSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierLodgingSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierLodgingResponse findByName(String name);

    public SupplierLodgingResponse findByIdentify(String identify);

    public List<SupplierLodgingResponse> findAllSimple();

    public List<SupplierLodgingResponse> findAllSimpleVoucherEgress();
}
