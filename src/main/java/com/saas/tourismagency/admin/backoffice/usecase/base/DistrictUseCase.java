package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceWithoutPageSearchResponse;

import java.util.List;

public interface DistrictUseCase {

    public DistrictResponse create(DistrictCreateRequest request);

    public DistrictResponse edit(String id, DistrictUpdateRequest request);

    public void deleteById(String id);

    public List<DistrictResponse> findAll();

    public DistrictResponse findById(String id);

    public DistrictSearchResponse searchAll(DistrictSearchRequest districtSearchRequest);

    public DistrictSearchResponse searchFilterAll(DistrictSearchFilterRequest districtSearchFilterRequest);

    public DistrictWithoutPageSearchResponse searchAllByProvince(BaseSearchRequestById baseSearchRequestById);
}
