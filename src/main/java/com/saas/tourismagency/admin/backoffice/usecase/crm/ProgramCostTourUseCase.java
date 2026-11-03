package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramCostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;

import java.util.List;

public interface ProgramCostTourUseCase {

    public ProgramCostTourResponse create(ProgramCostTourCreateRequest request);

    public ProgramCostTourResponse create(ProgramCostTourCreateRequest request, ProgramDomain programDomain);

    public ProgramCostTourResponse edit(String id, ProgramCostTourUpdateRequest request);

    public ProgramCostTourResponse edit(String id, ProgramCostTourUpdateRequest request, ProgramDomain programDomain);

    public void deleteById(String id);

    public List<ProgramCostTourResponse> findAll();

    public ProgramCostTourResponse findById(String id);

    public ProgramCostTourDomain findByIdDomain(String id);

    public ProgramCostTourSearchResponse searchAll(ProgramCostTourSearchRequest searchRequest);

    public ProgramCostTourSearchResponse searchFilterAll(ProgramCostTourSearchFilterRequest searchFilterRequest);

    public List<ProgramCostTourResponse> searchAllByProgram(String idProgram);

    public List<ProgramCostTourDomain> searchAllDomainByProgram(String idProgram);

    public ProgramCostTourResponse searchByProgramAndProgramCostTour(String idProgram, String idProgramCostTour);
}
