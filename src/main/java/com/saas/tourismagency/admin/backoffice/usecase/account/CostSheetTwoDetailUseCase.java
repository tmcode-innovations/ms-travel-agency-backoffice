package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.*;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;

import java.util.List;

public interface CostSheetTwoDetailUseCase {

    public CostSheetTwoDetailResponse create(CostSheetTwoDetailCreateRequest request);

    public CostSheetTwoDetailResponse create(CostSheetTwoDetailCreateRequest request, CostSheetTwoDomain costSheetTwoDomain);

    public CostSheetTwoDetailResponse edit(String id, CostSheetTwoDetailUpdateRequest request);

    public CostSheetTwoDetailResponse edit(String id, CostSheetTwoDetailUpdateRequest request, CostSheetTwoDomain costSheetTwoDomain);

    public void deleteById(String id);

    public List<CostSheetTwoDetailResponse> findAll();

    public CostSheetTwoDetailResponse findById(String id);

    public CostSheetTwoDetailSearchResponse searchAll(CostSheetTwoDetailSearchRequest bankSearchRequest);

    public CostSheetTwoDetailSearchResponse searchFilterAll(CostSheetTwoDetailSearchFilterRequest bankSearchFilterRequest);

    public List<CostSheetTwoDetailResponse> searchAllByCostSheetTwo(String idCostSheetTwo);

    public CostSheetTwoDetailResponse searchByCostSheetTwoAndCostSheetTwoDetail(String idCostSheetTwo, String idCostSheetTwoDetail);
}
