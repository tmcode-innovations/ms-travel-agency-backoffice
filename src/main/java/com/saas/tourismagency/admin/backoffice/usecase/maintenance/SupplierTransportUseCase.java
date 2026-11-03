package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierTransportUseCase {

    public SupplierTransportResponse create(SupplierTransportCreateRequest request);

    public SupplierTransportResponse edit(String id, SupplierTransportUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierTransportResponse> findAll();

    public SupplierTransportResponse findById(String id);

    public SupplierTransportSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierTransportSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierTransportResponse findByName(String name);

    public SupplierTransportResponse findByIdentify(String identify);

    public List<SupplierTransportResponse> findAllSimple();

    public List<SupplierTransportResponse> findAllSimpleVoucherEgress();
}
