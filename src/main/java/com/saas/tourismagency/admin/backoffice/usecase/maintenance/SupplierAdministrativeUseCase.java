package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierAdministrativeUseCase {

    public SupplierAdministrativeResponse create(SupplierAdministrativeCreateRequest request);

    public SupplierAdministrativeResponse edit(String id, SupplierAdministrativeUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierAdministrativeResponse> findAll();

    public SupplierAdministrativeResponse findById(String id);

    public SupplierAdministrativeSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierAdministrativeSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierAdministrativeResponse findByName(String name);

    public SupplierAdministrativeResponse findByIdentify(String identify);

    public List<SupplierAdministrativeResponse> findAllSimple();

    public List<SupplierAdministrativeResponse> findAllSimpleVoucherEgress();
}
