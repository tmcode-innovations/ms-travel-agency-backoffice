package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierInstitutionUseCase {

    public SupplierInstitutionResponse create(SupplierInstitutionCreateRequest request);

    public SupplierInstitutionResponse edit(String id, SupplierInstitutionUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierInstitutionResponse> findAll();

    public SupplierInstitutionResponse findById(String id);

    public SupplierInstitutionSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierInstitutionSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierInstitutionResponse findByName(String name);

    public SupplierInstitutionResponse findByIdentify(String identify);

    public List<SupplierInstitutionResponse> findAllSimple();

    public List<SupplierInstitutionResponse> findAllSimpleVoucherEgress();

}
