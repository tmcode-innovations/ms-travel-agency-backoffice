package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;

import java.util.List;

public interface ProgramItemUseCase {

    public ProgramItemResponse create(ProgramItemCreateRequest request);

    public ProgramItemResponse create(ProgramItemCreateRequest request, ProgramDomain programDomain);

    public void  createMultiple(ProgramItemCreateRequest request, ProgramDomain programDomain);

    public void createTour(ProgramTourItemCreateRequest request, ProgramDomain programDomain);

    public ProgramItemResponse edit(String id, ProgramItemUpdateRequest request);

    public ProgramItemResponse edit(String id, ProgramItemUpdateRequest request, ProgramDomain programDomain);

    public void deleteById(String id);

    public List<ProgramItemResponse> findAll();

    public ProgramItemResponse findById(String id);

    public ProgramItemSearchResponse searchAll(ProgramItemSearchRequest programItemSearchRequest);

    public ProgramItemSearchResponse searchFilterAll(ProgramItemSearchFilterRequest programItemSearchFilterRequest);

    public List<ProgramItemResponse> searchAllByTour(String idProgram);

    public ProgramItemResponse searchByProgramAndProgramItem(String idProgram, String idProgramItem);

    public List<ProgramItemDomain> searchAllDomainByProgram(String idProgram);

    public List<ProgramItemDomain> searchAllDomainByProgramAndProgramDate(String idProgramDate, String idProgram);

}
