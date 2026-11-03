package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.*;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;

import java.util.List;

public interface ExpenseGuideDetailUseCase {

    public ExpenseGuideDetailResponse create(ExpenseGuideDetailCreateRequest request);

    public ExpenseGuideDetailResponse create(ExpenseGuideDetailCreateRequest request, ExpenseGuideDomain expenseGuideDomain);

    public ExpenseGuideDetailResponse edit(String id, ExpenseGuideDetailUpdateRequest request);

    public ExpenseGuideDetailResponse edit(String id, ExpenseGuideDetailUpdateRequest request, ExpenseGuideDomain expenseGuideDomain);

    public void deleteById(String id);

    public List<ExpenseGuideDetailResponse> findAll();

    public ExpenseGuideDetailResponse findById(String id);

    public List<ExpenseGuideDetailResponse> findAllByExpenseGuide(String idExpenseGuide);

    public ExpenseGuideDetailSearchResponse searchAll(ExpenseGuideDetailSearchRequest bankSearchRequest);

    public ExpenseGuideDetailSearchResponse searchFilterAll(ExpenseGuideDetailSearchFilterRequest bankSearchFilterRequest);

}
