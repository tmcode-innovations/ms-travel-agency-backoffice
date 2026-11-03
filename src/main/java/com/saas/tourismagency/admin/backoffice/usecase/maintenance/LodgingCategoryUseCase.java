package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.*;

import java.util.List;

public interface LodgingCategoryUseCase {

    public LodgingCategoryResponse create(LodgingCategoryCreateRequest request);

    public LodgingCategoryResponse edit(String id, LodgingCategoryUpdateRequest request);

    public void deleteById(String id);

    public List<LodgingCategoryResponse> findAll();

    public LodgingCategoryResponse findById(String id);

    public LodgingCategorySearchResponse searchAll(LodgingCategorySearchRequest lodgingCategorySearchRequest);

    public LodgingCategorySearchResponse searchFilterAll(LodgingCategorySearchFilterRequest lodgingCategorySearchFilterRequest);

    public LodgingCategoryResponse findByName(String name);

    public LodgingCategoryResponse findByIdentify(String identify);
}
