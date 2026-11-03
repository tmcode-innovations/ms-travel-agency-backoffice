package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailUpdateRequest;

import java.util.List;

public interface CostSheetTwoUseCase {

    public CostSheetTwoResponse create(CostSheetTwoCreateRequest request);

    public CostSheetTwoResponse edit(String id, CostSheetTwoUpdateRequest request);

    public void deleteById(String id);

    public List<CostSheetTwoResponse> findAll();

    public CostSheetTwoResponse findById(String id);

    public CostSheetTwoSearchResponse searchAll(CostSheetTwoSearchRequest bankSearchRequest);

    public CostSheetTwoSearchResponse searchFilterAll(CostSheetTwoSearchFilterRequest bankSearchFilterRequest);

    public CostSheetTwoResponse changeState(String id, CostSheetTwoChangeStateRequest request);



    public CostSheetTwoDetailResponse addCostSheetTwoDetail(CostSheetTwoDetailCreateRequest request, String idCostSheetTwo);

    public CostSheetTwoDetailResponse editCostSheetTwoDetail(String id, CostSheetTwoDetailUpdateRequest request, String idCostSheetTwo);

    public void deleteCostSheetTwoDetail(String id, String idCostSheetTwo);

    public List<CostSheetTwoDetailResponse> findAllCostSheetTwoDetailByCostSheetTwo(String idCostSheetTwo);

    public CostSheetTwoDetailResponse findByIdAndCostSheetTwo(String id, String idCostSheetTwo);

    public Boolean existRegisterGroupInCostSheetTwo(String idRegisterGroup);

    public CostSheetTwoResponse updateCalculate(String id, CostSheetTwoUpdateRequest request);


    public byte[] exportCostSheetTwoPdf(String idCostSheetTwo);

    public byte[] exportCostSheetTwoExcel(String idCostSheetTwo);
}
