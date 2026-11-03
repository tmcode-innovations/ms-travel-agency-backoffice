package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierGlampingUseCase {

    public SupplierGlampingResponse create(SupplierGlampingCreateRequest request);

    public SupplierGlampingResponse edit(String id, SupplierGlampingUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierGlampingResponse> findAll();

    public SupplierGlampingResponse findById(String id);

    public SupplierGlampingSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierGlampingSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierGlampingResponse findByName(String name);

    public SupplierGlampingResponse findByIdentify(String identify);

    public List<SupplierGlampingResponse> findAllSimple();

    public List<SupplierGlampingResponse> findAllSimpleVoucherEgress();
}
