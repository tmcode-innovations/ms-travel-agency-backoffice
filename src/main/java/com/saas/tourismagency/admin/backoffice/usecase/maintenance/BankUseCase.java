package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.*;

import java.util.List;

public interface BankUseCase {

    public BankResponse create(BankCreateRequest request);

    public BankResponse edit(String id, BankUpdateRequest request);

    public void deleteById(String id);

    public List<BankResponse> findAll();

    public BankResponse findById(String id);

    public BankSearchResponse searchAll(BankSearchRequest bankSearchRequest);

    public BankSearchResponse searchFilterAll(BankSearchFilterRequest bankSearchFilterRequest);

    public BankResponse findByName(String name);

    public BankResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

}
