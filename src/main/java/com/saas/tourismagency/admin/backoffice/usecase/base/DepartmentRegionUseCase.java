package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.*;

import java.util.List;

public interface DepartmentRegionUseCase {

    public DepartmentRegionResponse create(DepartmentRegionCreateRequest request);

    public DepartmentRegionResponse edit(String id, DepartmentRegionUpdateRequest request);

    public void deleteById(String id);

    public List<DepartmentRegionResponse> findAll();

    public DepartmentRegionResponse findById(String id);

    public DepartmentRegionSearchResponse searchAll(DepartmentRegionSearchRequest departmentRegionSearchRequest);

    public DepartmentRegionSearchResponse searchFilterAll(DepartmentRegionSearchFilterRequest departmentRegionSearchFilterRequest);

    public DepartmentRegionWithoutPageSearchResponse searchAllByCountry(BaseSearchRequestById baseSearchRequestById);
}
