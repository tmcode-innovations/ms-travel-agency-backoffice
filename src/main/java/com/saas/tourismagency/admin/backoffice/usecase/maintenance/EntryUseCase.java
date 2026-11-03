package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.*;

import java.util.List;

public interface EntryUseCase {

    public EntryResponse create(EntryCreateRequest request);

    public EntryResponse edit(String id, EntryUpdateRequest request);

    public void deleteById(String id);

    public List<EntryResponse> findAll();

    public EntryResponse findById(String id);

    public EntrySearchResponse searchAll(EntrySearchRequest bankSearchRequest);

    public EntrySearchResponse searchFilterAll(EntrySearchFilterRequest bankSearchFilterRequest);

    public EntryResponse findByName(String name);

    public EntryResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

    public List<EntryResponse> findAllSimpleDocumentFinance();
}
