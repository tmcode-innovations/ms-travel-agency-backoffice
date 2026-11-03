package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.*;

import java.time.LocalDate;
import java.util.List;

public interface ChangeTypeUseCase {

    public ChangeTypeResponse create(ChangeTypeCreateRequest request);

    public ChangeTypeResponse edit(String id, ChangeTypeUpdateRequest request);

    public void deleteById(String id);

    public List<ChangeTypeResponse> findAll();

    public ChangeTypeResponse findById(String id);

    public ChangeTypeSearchResponse searchAll(ChangeTypeSearchRequest bankSearchRequest);

    public ChangeTypeSearchResponse searchFilterAll(ChangeTypeSearchFilterRequest bankSearchFilterRequest);

    public ChangeTypeResponse findByDate(ChangeTypeFindByDateRequest changeTypeFindByDateRequest);

}
