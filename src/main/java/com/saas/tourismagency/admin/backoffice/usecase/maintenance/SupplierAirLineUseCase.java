package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierAirLineUseCase {

    public SupplierAirLineResponse create(SupplierAirLineCreateRequest request);

    public SupplierAirLineResponse edit(String id, SupplierAirLineUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierAirLineResponse> findAll();

    public SupplierAirLineResponse findById(String id);

    public SupplierAirLineSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierAirLineSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierAirLineResponse findByName(String name);

    public SupplierAirLineResponse findByIdentify(String identify);

    public List<SupplierAirLineResponse> findAllSimple();

    public List<SupplierAirLineResponse> findAllSimpleVoucherEgress();
}
