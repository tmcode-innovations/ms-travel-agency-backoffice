package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramDateAllItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramModelViewResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramTourItemCreateRequest;

import java.util.List;

public interface ProgramUseCase {

    public ProgramResponse create(ProgramCreateRequest request);

    public ProgramResponse edit(String id, ProgramUpdateRequest request);

    public void deleteById(String id);

    public List<ProgramResponse> findAll();

    public ProgramResponse findById(String id);

    public ProgramSearchResponse searchAll(ProgramSearchRequest programSearchRequest);

    public ProgramSearchResponse searchFilterAll(ProgramSearchFilterRequest programSearchFilterRequest);

    public ProgramResponse findByName(String name);

    public ProgramResponse findByIdentify(String identify);


    public List<ProgramItemResponse> findAllProgramItemByProgram(String idProgram);

    public List<ProgramDateResponse> findAllProgramDateByProgram(String idProgram);

    public ProgramItemResponse addProgram(ProgramItemCreateRequest request, String idProgram);

    public void addProgramMultiple(ProgramItemCreateRequest request, String idProgram);

    public void addProgramWithTour(ProgramTourItemCreateRequest request, String idProgram);

    public ProgramItemResponse editProgram(String id, ProgramItemUpdateRequest request, String idProgram);

    public void deleteProgram(String id, String idProgram);

    public ProgramItemResponse findByProgramItemIdAndProgram(String id, String idProgram);

    public ProgramDateResponse findByProgramDateIdAndProgram(String id, String idProgram);

    public List<ProgramDateAllItemResponse> findAllProgramDateWithProgramItemByProgram(String idProgram);

    public List<ProgramModelViewResponse> findAllProgramDateWithProgramItemByProgramModelView(String idProgram);

    public ProgramDateResponse editProgramDate(String id, ProgramDateUpdateOnlyNameRequest request, String idProgram);

    public void changeDateProgramDay(String idProgram, ChangeProgramDayCrmRequest changeProgramDayRequest);

    public List<ProgramResponse> findAllSimple();


    // Program Cost Tour

    public List<ProgramCostTourResponse> findAllProgramCostTourByProgram(String idProgram);

    public ProgramCostTourResponse addProgramCostTour(ProgramCostTourCreateRequest request, String idProgram);

    public ProgramCostTourResponse editProgramCostTour(String id, ProgramCostTourUpdateRequest request, String idProgram);

    public void deleteProgramCostTour(String id, String idProgram);

    public ProgramCostTourResponse findByProgramCostTourAndProgram(String id, String idProgram);

    public void removeToursAndProgramCostTourByProgram(String idProgram, String idTour);

    public void removeToursAndProgramCostTourByProgramAndTourAndProgramCostTour(String idProgram, String idTour, String idProgramCostTour);
}
