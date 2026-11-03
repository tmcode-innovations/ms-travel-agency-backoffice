package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.*;

import java.util.List;

public interface ProvinceUseCase {

    public ProvinceResponse create(ProvinceCreateRequest request);

    public ProvinceResponse edit(String id, ProvinceUpdateRequest request);

    public void deleteById(String id);

    public List<ProvinceResponse> findAll();

    public ProvinceResponse findById(String id);

    public ProvinceSearchResponse searchAll(ProvinceSearchRequest provinceSearchRequest);

    public ProvinceSearchResponse searchFilterAll(ProvinceSearchFilterRequest provinceSearchFilterRequest);

    public ProvinceWithoutPageSearchResponse searchAllByDepartmentRegion(BaseSearchRequestById baseSearchRequestById);
}
