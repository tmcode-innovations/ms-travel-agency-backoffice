package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailUpdateRequest;

import java.util.List;

public interface ExpenseGuideUseCase {

    public ExpenseGuideResponse create(ExpenseGuideCreateRequest request);

    public ExpenseGuideResponse edit(String id, ExpenseGuideUpdateRequest request);

    public void deleteById(String id);

    public List<ExpenseGuideResponse> findAll();

    public ExpenseGuideResponse findById(String id);

    public ExpenseGuideSearchResponse searchAll(ExpenseGuideSearchRequest bankSearchRequest);

    public ExpenseGuideSearchResponse searchFilterAll(ExpenseGuideSearchFilterRequest bankSearchFilterRequest);

    public ExpenseGuideDetailResponse addExpenseGuideDetail(ExpenseGuideDetailCreateRequest request, String idExpenseGuide);

    public ExpenseGuideDetailResponse editExpenseGuideDetail(String id, ExpenseGuideDetailUpdateRequest request, String idExpenseGuide);

    public void deleteExpenseGuideDetail(String id, String idExpenseGuide);

    public List<ExpenseGuideDetailResponse> findAllExpenseGuideDetailByExpenseGuide(String idExpenseGuide);

    public ExpenseGuideDetailResponse findByIdAndExpenseGuide(String id, String idExpenseGuide);

    public ExpenseGuideResponse getExpenseGuideModificationData(String idExpenseGuide);

    public byte[] exportExpenseGuidePdf(String idExpenseGuide);

}
