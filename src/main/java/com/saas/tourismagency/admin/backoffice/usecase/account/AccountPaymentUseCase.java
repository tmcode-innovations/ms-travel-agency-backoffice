package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.*;

import java.util.List;

public interface AccountPaymentUseCase {

    public AccountPaymentResponse create(AccountPaymentCreateRequest request);

    public AccountPaymentResponse edit(String id, AccountPaymentUpdateRequest request);

    public void deleteById(String id);

    public List<AccountPaymentResponse> findAll();

    public AccountPaymentResponse findById(String id);

    public AccountPaymentSearchResponse searchAll(AccountPaymentSearchRequest bankSearchRequest);

    public AccountPaymentSearchResponse searchFilterAll(AccountPaymentSearchFilterRequest bankSearchFilterRequest);

    public List<AccountPaymentResponse> findByProformaTypeAndIdentify(String proformaType, String identify);

}
