package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierOperatorUseCase {

    public SupplierOperatorResponse create(SupplierOperatorCreateRequest request);

    public SupplierOperatorResponse edit(String id, SupplierOperatorUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierOperatorResponse> findAll();

    public SupplierOperatorResponse findById(String id);

    public SupplierOperatorSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierOperatorSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierOperatorResponse findByName(String name);

    public SupplierOperatorResponse findByIdentify(String identify);

    public List<SupplierOperatorResponse> findAllSimple();

    public List<SupplierOperatorResponse> findAllSimpleVoucherEgress();
}
