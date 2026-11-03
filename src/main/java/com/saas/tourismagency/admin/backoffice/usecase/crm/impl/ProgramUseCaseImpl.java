package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgram;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.PersistProgramNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.ProgramItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programtype.ProgramTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateProgramEnum;
import com.saas.tourismagency.admin.backoffice.usecase.crm.*;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ProgramUseCaseImpl implements ProgramUseCase {

    @Autowired
    private ProgramRepo repo;

    @Autowired
    private ProgramTypeRepo programTypeRepo;

    @Autowired
    private ProgramConverter converter;

    @Autowired
    private ProgramDateUseCase programDateUseCase;

    @Autowired
    private ProgramItemUseCase programItemUseCase;

    @Autowired
    private SetFieldDefaultProgram setFieldDefault;

    @Autowired
    PersistProgramNumberCallbackImpl persistProgramNumberCallbackImpl;

    @Autowired
    private ProgramDateRepo programDateRepo;

    @Autowired
    private ProgramCostTourUseCase programCostTourUseCase;

    @Autowired
    private ProgramCostTourRepo programCostTourRepo;

    @Autowired
    private ProgramItemRepo programItemRepo;

    @Autowired
    private TourRepo tourRepo;

    @Override
    @Transactional
    public ProgramResponse create(ProgramCreateRequest request) {
        ProgramDomain programDomainExist = repo.findByName(request.getName());
        if (programDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ProgramTypeDomain programTypeDomain = null;
        if (request.getProgramType() != null && !request.getProgramType().isEmpty()){
            programTypeDomain = programTypeRepo.findById(request.getProgramType());
        }
        ProgramDomain newDomain = request.to(programTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistProgramNumberCallbackImpl.generateNumber());
        newDomain.setStateProgram(StateProgramEnum.DRAFT.getName());
        ProgramDomain createdDomain = repo.create(newDomain);
        programDateUseCase.generateDateProgram(createdDomain, createdDomain.getDay());
        return ProgramResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramResponse edit(String id, ProgramUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProgramDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDomain programDomainExist = repo.findByName(request.getName());
        if (programDomainExist != null && !programDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ProgramTypeDomain programTypeDomain = null;
        if (request.getProgramType() != null && !request.getProgramType().isEmpty()){
            programTypeDomain = programTypeRepo.findById(request.getProgramType());
        }

        ProgramDomain programDomain = request.to(programTypeDomain);
        programDomain.setIdentify(old.getIdentify());
        programDomain.setStateProgram(old.getStateProgram());
        ProgramDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programDomain, old));
        return ProgramResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProgramResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramResponse> findAll() {
        return repo.findAll(false).stream().map(programDomain -> ProgramResponse.from(programDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProgramDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(id));
        }
        return ProgramResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramSearchResponse searchAll(ProgramSearchRequest programSearchRequest) {
       PageDTO<ProgramDomain> pageDTO = repo.searchAll(programSearchRequest);
       ProgramSearchResponse programSearchRespose = new ProgramSearchResponse();
       programSearchRespose.setPrograms(pageDTO.getContent().stream().map(programDomain ->  ProgramResponse.from(programDomain)).toList());
       programSearchRespose.setTotalElements(pageDTO.getTotalElements());
       programSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return programSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramSearchResponse searchFilterAll(ProgramSearchFilterRequest programSearchFilterRequest) {
        PageDTO<ProgramDomain> pageDTO = repo.searchAllFilter(programSearchFilterRequest);
        ProgramSearchResponse programSearchRespose = new ProgramSearchResponse();
        programSearchRespose.setPrograms(pageDTO.getContent().stream().map(programDomain ->  ProgramResponse.from(programDomain)).toList());
        programSearchRespose.setTotalElements(pageDTO.getTotalElements());
        programSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return programSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ProgramDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No program found with name '%s'.".formatted(name));
        }
        return ProgramResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ProgramDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No program found with identify '%s'.".formatted(identify));
        }
        return ProgramResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramItemResponse> findAllProgramItemByProgram(String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        return programItemUseCase.searchAllByTour(idProgram);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramDateResponse> findAllProgramDateByProgram(String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        return programDateUseCase.searchAllByProgram(idProgram);
    }

    @Override
    @Transactional
    public ProgramItemResponse addProgram(ProgramItemCreateRequest request, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramItemResponse resultCreate = programItemUseCase.create(request, programDomain);
        return resultCreate;
    }

    @Override
    @Transactional
    public void addProgramMultiple(ProgramItemCreateRequest request, String idProgram) {
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramDomain programDomain = new ProgramDomain();
        programDomain.setId(programIdBaseDomain.getId());
        programItemUseCase.createMultiple(request, programDomain);
    }

    @Override
    @Transactional
    public void addProgramWithTour(ProgramTourItemCreateRequest request, String idProgram) {
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        programItemUseCase.createTour(request, programDomain);
    }

    @Override
    @Transactional
    public ProgramItemResponse editProgram(String id, ProgramItemUpdateRequest request, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramItemResponse resultEdit = programItemUseCase.edit(id, request, programDomain);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteProgram(String id, String idProgram) {
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }

        programItemUseCase.deleteById(id);
    }

    @Override
    @Transactional
    public ProgramItemResponse findByProgramItemIdAndProgram(String id, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        return programItemUseCase.searchByProgramAndProgramItem(idProgram, id);
    }

    @Override
    @Transactional
    public ProgramDateResponse findByProgramDateIdAndProgram(String id, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        return programDateUseCase.searchByProgramAndProgramDate(idProgram, id);
    }

    @Override
    @Transactional
    public List<ProgramDateAllItemResponse> findAllProgramDateWithProgramItemByProgram(String idProgram) {
        List<ProgramDateAllItemResponse> result = new ArrayList<>();
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        List<ProgramDateDomain> listProgramDate = programDateUseCase.searchAllDomainByProgram(idProgram);
        if (listProgramDate != null && !listProgramDate.isEmpty()){
            List<ProgramDateDomain> listProgramDateResult = listProgramDate.stream().collect(Collectors.toList());
            Collections.sort(listProgramDateResult);
            for (ProgramDateDomain programDateDomain: listProgramDateResult
            ) {
                List<ProgramItemDomain> listProgramItem = programItemUseCase.searchAllDomainByProgramAndProgramDate(programDateDomain.getId(), idProgram);
                List<ProgramItemDomain> listProgramItemResult = new ArrayList<>();
                if (listProgramItem != null && !listProgramItem.isEmpty()){
                    listProgramItemResult = listProgramItem.stream().collect(Collectors.toList());
                    Collections.sort(listProgramItemResult);
                }
                result.add(ProgramDateAllItemResponse.from(programDateDomain, listProgramItemResult));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<ProgramModelViewResponse> findAllProgramDateWithProgramItemByProgramModelView(String idProgram) {
        List<ProgramDateAllItemResponse> list = this.findAllProgramDateWithProgramItemByProgram(idProgram);
        List<ProgramModelViewResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            for (ProgramDateAllItemResponse programDateAllItemResponse: list
            ) {
                List<ProgramModelViewResponse> details = new ArrayList<>();
                if (programDateAllItemResponse.getProgramItems() != null && !programDateAllItemResponse.getProgramItems().isEmpty()){
                    for (ProgramItemResponse programItemResponse: programDateAllItemResponse.getProgramItems()
                    ) {
                        ProgramModelViewResponse programModelViewResponseChild = ProgramModelViewResponse.from(
                                programItemResponse.getId(),
                                null,
                                programItemResponse.getSupplierType(),
                                programItemResponse.getSupplier(),
                                programItemResponse.getService(),
                                programItemResponse.getObservation(),
                                programItemResponse.getProgramDateTime(),
                                programItemResponse.getProgramDateTimeStr(),
                                false,
                                null
                        );
                        details.add(programModelViewResponseChild);
                    }
                }
                ProgramModelViewResponse programModelViewResponseParent = ProgramModelViewResponse.from(
                        programDateAllItemResponse.getId(),
                        programDateAllItemResponse.getName(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        true,
                        details
                );
                result.add(programModelViewResponseParent);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public ProgramDateResponse editProgramDate(String id, ProgramDateUpdateOnlyNameRequest request, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramDateDomain programDateDomain = programDateUseCase.findByIdDomain(id);

        ProgramDateUpdateRequest programDateUpdateRequest = new ProgramDateUpdateRequest();
        programDateUpdateRequest.setId(request.getId());
        programDateUpdateRequest.setName(request.getName());
        programDateUpdateRequest.setDescription(programDateDomain.getDescription());
        programDateUpdateRequest.setDay(programDateDomain.getDay());
        programDateUpdateRequest.setIdentify(programDateDomain.getIdentify());
        programDateUpdateRequest.setProgram(request.getProgram());

        ProgramDateResponse resultEdit = programDateUseCase.edit(id, programDateUpdateRequest);

        return resultEdit;
    }

    @Override
    @Transactional
    public void changeDateProgramDay(String idProgram, ChangeProgramDayCrmRequest changeProgramDayRequest) {
        if (idProgram == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }

        if (programDomain.getDay().equals(changeProgramDayRequest.getDays())){

            throw new BadRequestException(MessagesValidation.VALIDATION_SAME_DAY_TOUR);

        }else if (changeProgramDayRequest.getDays() > programDomain.getDay()){
            Integer days = changeProgramDayRequest.getDays() - programDomain.getDay();
            Stream.iterate(0, n -> n + 1)
                    .limit(days)
                    .forEach(item -> {
                        ProgramDateDomain programDateDomain = new ProgramDateDomain();
                        Integer indexDay = programDomain.getDay() + item + 1;
                        programDateDomain.setName("Dia " + indexDay);
                        programDateDomain.setProgram(programDomain);
                        programDateDomain.setDay(indexDay);
                        programDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                        programDateRepo.create(programDateDomain);
                    });

            programDomain.setDay(changeProgramDayRequest.getDays());

            repo.edit(programDomain);

        }else if (changeProgramDayRequest.getDays() < programDomain.getDay()){

            throw new BadRequestException(MessagesValidation.VALIDATION_LESS_THAN_DAY_TOUR);

        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramResponse> findAllSimple() {
        return repo.findAllSimpleWithDay().stream().map(programDomain -> ProgramResponse.from(programDomain)).toList();
    }

    @Override
    @Transactional
    public List<ProgramCostTourResponse> findAllProgramCostTourByProgram(String idProgram) {
        List<ProgramCostTourResponse> result = new ArrayList<>();
        if (idProgram == null) {
            return null;
        }
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        List<ProgramCostTourDomain> listProgramCostTour = programCostTourRepo.searchAllByProgramSimpleDomainCaseShowProgramCostTour(idProgram);
        if (listProgramCostTour != null && !listProgramCostTour.isEmpty()){
            listProgramCostTour = listProgramCostTour.stream().collect(Collectors.toList());
            Collections.sort(listProgramCostTour);
            result = listProgramCostTour.stream().map(programCostTourDomain -> ProgramCostTourResponse.from(programCostTourDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public ProgramCostTourResponse addProgramCostTour(ProgramCostTourCreateRequest request, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramDomain programDomain = new ProgramDomain();
        programDomain.setId(programIdBaseDomain.getId());
        return programCostTourUseCase.create(request, programDomain);
    }

    @Override
    @Transactional
    public ProgramCostTourResponse editProgramCostTour(String id, ProgramCostTourUpdateRequest request, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        ProgramDomain programDomain = new ProgramDomain();
        programDomain.setId(programIdBaseDomain.getId());
        return programCostTourUseCase.edit(id, request, programDomain);
    }

    @Override
    @Transactional
    public void deleteProgramCostTour(String id, String idProgram) {
        if (idProgram == null) {
            throw new BadRequestException("The program id cannot be null");
        }
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        programCostTourUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourResponse findByProgramCostTourAndProgram(String id, String idProgram) {
        if (idProgram == null) {
            return null;
        }
        IdBaseDomain programIdBaseDomain = repo.findByIdEntity(idProgram);
        if (programIdBaseDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        return programCostTourUseCase.searchByProgramAndProgramCostTour(idProgram, id);
    }

    @Override
    @Transactional
    public void removeToursAndProgramCostTourByProgram(String idProgram, String idTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        List<ProgramDateDomain> listDates = programDateRepo.searchAllByQuotationSimpleDomainCaseDeleteTour(idProgram);
        if (listDates != null && !listDates.isEmpty()){
            listDates = listDates.stream().collect(Collectors.toList());
            for(ProgramDateDomain programDateDomain: listDates){
                Set<TourEntity> tours = new HashSet<>();
                if (programDateDomain.getTours() != null && !programDateDomain.getTours().isEmpty()){
                    for (TourDomain tourDomain: programDateDomain.getTours()){
                        if (!idTour.equals(tourDomain.getId())){
                            TourEntity tourEntity = new TourEntity();
                            tourEntity.setId(tourDomain.getId());
                            tours.add(tourEntity);
                        }
                    }
                }
                programDateRepo.updateToursByIdSimpleDomainCaseDeleteTour(programDateDomain.getId(), tours);

                List<ProgramItemDomain> programItemDomainListToDelete = programItemRepo.searchAllByProgramAndProgramDateSimpleDomainCaseDeleteTours(idProgram, programDateDomain.getId(), idTour);
                if (programItemDomainListToDelete != null && !programItemDomainListToDelete.isEmpty()){
                    programItemDomainListToDelete = programItemDomainListToDelete.stream().toList();
                    for (ProgramItemDomain programItemDomain : programItemDomainListToDelete){
                        programItemRepo.deleteById(programItemDomain.getId());
                    }
                }
            }
        }

        List<ProgramCostTourDomain> listProgramCostTourDelete = programCostTourRepo.searchAllByProgramAndTourSimpleDomainCaseShowProgramCostTour(idProgram, idTour);
        if (listProgramCostTourDelete != null && !listProgramCostTourDelete.isEmpty()){
            listProgramCostTourDelete = listProgramCostTourDelete.stream().toList();
            for (ProgramCostTourDomain programCostTourDomain : listProgramCostTourDelete) {
                programCostTourRepo.deleteById(programCostTourDomain.getId());
            }
        }
    }

    @Override
    @Transactional
    public void removeToursAndProgramCostTourByProgramAndTourAndProgramCostTour(String idProgram, String idTour, String idProgramCostTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        ProgramDomain programDomain = repo.findById(idProgram);
        if (programDomain == null) {
            throw new NotFoundException("No program found with id '%s'.".formatted(idProgram));
        }
        TourDomain tourExistDomain = tourRepo.findById(idTour);
        if (tourExistDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        ProgramCostTourDomain programCostTourExistDomain = programCostTourRepo.findById(idProgramCostTour);
        if (programCostTourExistDomain == null) {
            throw new NotFoundException("No program cost tour found with id '%s'.".formatted(idProgramCostTour));
        }
        List<ProgramDateDomain> listDates = programDateRepo.searchAllByQuotationSimpleDomainCaseDeleteTour(idProgram);
        if (listDates != null && !listDates.isEmpty()){
            listDates = listDates.stream().collect(Collectors.toList());
            for(ProgramDateDomain programDateDomain: listDates){
                Set<TourEntity> tours = new HashSet<>();
                if (programDateDomain.getTours() != null && !programDateDomain.getTours().isEmpty()){
                    for (TourDomain tourDomain: programDateDomain.getTours()){
                        if (!idTour.equals(tourDomain.getId())){
                            TourEntity tourEntity = new TourEntity();
                            tourEntity.setId(tourDomain.getId());
                            tours.add(tourEntity);
                        }
                    }
                }
                programDateRepo.updateToursByIdSimpleDomainCaseDeleteTour(programDateDomain.getId(), tours);

                List<ProgramItemDomain> programItemDomainListToDelete = programItemRepo.searchAllByProgramAndProgramDateAndProgramCostTourSimpleDomainCaseDeleteTours(idProgram, programDateDomain.getId(), idTour, idProgramCostTour);
                if (programItemDomainListToDelete != null && !programItemDomainListToDelete.isEmpty()){
                    programItemDomainListToDelete = programItemDomainListToDelete.stream().toList();
                    for (ProgramItemDomain programItemDomain : programItemDomainListToDelete){
                        programItemRepo.deleteById(programItemDomain.getId());
                    }
                }
            }
        }

        /*List<ProgramCostTourDomain> listProgramCostTourDelete = programCostTourRepo.searchAllByProgramAndTourSimpleDomainCaseShowProgramCostTour(idProgram, idTour);
        if (listProgramCostTourDelete != null && !listProgramCostTourDelete.isEmpty()){
            listProgramCostTourDelete = listProgramCostTourDelete.stream().toList();
            for (ProgramCostTourDomain programCostTourDomain : listProgramCostTourDelete) {
                programCostTourRepo.deleteById(programCostTourDomain.getId());
            }
        }*/
        programCostTourRepo.deleteById(programCostTourExistDomain.getId());
    }
}
