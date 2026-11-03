package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent.*;

import java.util.List;

public interface CostSheetTwoPercentUseCase {

    public CostSheetTwoPercentResponse create(CostSheetTwoPercentCreateRequest request);

    public CostSheetTwoPercentResponse edit(String id, CostSheetTwoPercentUpdateRequest request);

    public void deleteById(String id);

    public List<CostSheetTwoPercentResponse> findAll();

    public CostSheetTwoPercentResponse findById(String id);

    public CostSheetTwoPercentSearchResponse searchAll(CostSheetTwoPercentSearchRequest costSheetTwoPercentSearchRequest);

    public CostSheetTwoPercentSearchResponse searchFilterAll(CostSheetTwoPercentSearchFilterRequest costSheetTwoPercentSearchFilterRequest);

    public CostSheetTwoPercentResponse changeAvailable(String id, Boolean available);

    public CostSheetTwoPercentResponse findByName(String name);

    public CostSheetTwoPercentResponse findAvailable();
}
