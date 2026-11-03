package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;

import java.util.List;

public interface SupplierRestaurantUseCase {

    public SupplierRestaurantResponse create(SupplierRestaurantCreateRequest request);

    public SupplierRestaurantResponse edit(String id, SupplierRestaurantUpdateRequest request);

    public void deleteById(String id);

    public List<SupplierRestaurantResponse> findAll();

    public SupplierRestaurantResponse findById(String id);

    public SupplierRestaurantSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest);

    public SupplierRestaurantSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest);

    public SupplierRestaurantResponse findByName(String name);

    public SupplierRestaurantResponse findByIdentify(String identify);

    public List<SupplierRestaurantResponse> findAllSimple();

    public List<SupplierRestaurantResponse> findAllSimpleVoucherEgress();
}
