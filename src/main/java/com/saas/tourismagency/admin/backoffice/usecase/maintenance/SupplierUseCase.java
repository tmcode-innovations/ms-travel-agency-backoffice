package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierUseCase {

    public SupplierResponse create(SupplierCreateRequest request);

    public SupplierResponse edit(String id, SupplierUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierResponse> findAll();

    public SupplierResponse findById(String id);

    public SupplierSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public List<SimpleBaseResponse> findAllSimple();

    public List<SupplierResponse> findAllSimpleForServiceOperation();
}
