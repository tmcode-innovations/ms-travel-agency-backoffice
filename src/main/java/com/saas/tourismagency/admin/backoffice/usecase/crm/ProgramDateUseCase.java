package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;

import java.util.List;

public interface ProgramDateUseCase {

    public ProgramDateResponse create(ProgramDateCreateRequest request);

    public ProgramDateResponse edit(String id, ProgramDateUpdateRequest request);

    public void deleteById(String id);

    public List<ProgramDateResponse> findAll();

    public ProgramDateResponse findById(String id);

    public ProgramDateDomain findByIdDomain(String id);

    public ProgramDateSearchResponse searchAll(ProgramDateSearchRequest programItemTraditionalSearchRequest);

    public ProgramDateSearchResponse searchFilterAll(ProgramDateSearchFilterRequest programItemTraditionalSearchFilterRequest);

    public List<ProgramDateResponse> searchAllByProgram(String idProgram);

    public void generateDateProgram(ProgramDomain program, Integer days);

    public List<ProgramDateDomain> searchAllDomainByProgram(String idProgram);

    public ProgramDateResponse searchByProgramAndProgramDate(String idProgram, String idProgramDate);
}
