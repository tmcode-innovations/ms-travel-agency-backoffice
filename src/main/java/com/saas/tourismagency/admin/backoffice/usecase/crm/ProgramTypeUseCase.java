package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.*;

import java.util.List;

public interface ProgramTypeUseCase {

    public ProgramTypeResponse create(ProgramTypeCreateRequest request);

    public ProgramTypeResponse edit(String id, ProgramTypeUpdateRequest request);

    public void deleteById(String id);

    public List<ProgramTypeResponse> findAll();

    public ProgramTypeResponse findById(String id);

    public ProgramTypeSearchResponse searchAll(ProgramTypeSearchRequest programTypeSearchRequest);

    public ProgramTypeSearchResponse searchFilterAll(ProgramTypeSearchFilterRequest programTypeSearchFilterRequest);

    public ProgramTypeResponse findByName(String name);

    public ProgramTypeResponse findByIdentify(String identify);
}
