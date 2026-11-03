package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programcosttour.ProgramCostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgramCostTour;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramCostTourUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProgramCostTourUseCaseImpl implements ProgramCostTourUseCase {

    @Autowired
    private ProgramCostTourRepo repo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ProgramDateRepo programDateRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private ProgramCostTourConverter converter;

    @Autowired
    private SetFieldDefaultProgramCostTour setFieldDefault;

    @Override
    @Transactional
    public ProgramCostTourResponse create(ProgramCostTourCreateRequest request) {
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            programDomain = new ProgramDomain();
            programDomain.setId(programIdBaseDomain.getId());
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            programDateDomain = new ProgramDateDomain();
            programDateDomain.setId(programDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }

        ProgramCostTourDomain newDomain = request.to(programDomain, programDateDomain, tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramCostTourDomain createdDomain = repo.create(newDomain);
        return ProgramCostTourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramCostTourResponse create(ProgramCostTourCreateRequest request, ProgramDomain programDomain) {
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            programDateDomain = new ProgramDateDomain();
            programDateDomain.setId(programDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }

        ProgramCostTourDomain newDomain = request.to(programDomain, programDateDomain, tourDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramCostTourDomain createdDomain = repo.create(newDomain);
        return ProgramCostTourResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramCostTourResponse edit(String id, ProgramCostTourUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProgramCostTourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            programDomain = new ProgramDomain();
            programDomain.setId(programIdBaseDomain.getId());
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            programDateDomain = new ProgramDateDomain();
            programDateDomain.setId(programDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        ProgramCostTourDomain programCostTourDomain = request.to(programDomain, programDateDomain, tourDomain);
        programCostTourDomain.setDay(old.getDay());
        ProgramCostTourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programCostTourDomain, old));
        return ProgramCostTourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ProgramCostTourResponse edit(String id, ProgramCostTourUpdateRequest request, ProgramDomain programDomain) {
        if (id == null) {
            return null;
        }
        ProgramCostTourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            programDateDomain = new ProgramDateDomain();
            programDateDomain.setId(programDateIdBaseDomain.getId());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        ProgramCostTourDomain programCostTourDomain = request.to(programDomain, programDateDomain, tourDomain);
        programCostTourDomain.setDay(old.getDay());
        ProgramCostTourDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programCostTourDomain, old));
        return ProgramCostTourResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProgramCostTourResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramCostTourResponse> findAll() {
        return repo.findAll(false).stream().map(programCostTourDomain -> ProgramCostTourResponse.from(programCostTourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProgramCostTourDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No program cost tour found with id '%s'.".formatted(id));
        }
        return ProgramCostTourResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        ProgramCostTourDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No program cost tour found with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourSearchResponse searchAll(ProgramCostTourSearchRequest searchRequest) {
       PageDTO<ProgramCostTourDomain> pageDTO = repo.searchAll(searchRequest);
       ProgramCostTourSearchResponse programCostTourSearchResponse = new ProgramCostTourSearchResponse();
       programCostTourSearchResponse.setProgramCostTours(pageDTO.getContent().stream().map( programCostTourDomain ->  ProgramCostTourResponse.from(programCostTourDomain)).toList());
       programCostTourSearchResponse.setTotalElements(pageDTO.getTotalElements());
       programCostTourSearchResponse.setTotalPages(pageDTO.getTotalPages());
       return programCostTourSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourSearchResponse searchFilterAll(ProgramCostTourSearchFilterRequest programCostTourSearchFilterRequest) {
        PageDTO<ProgramCostTourDomain> pageDTO = repo.searchAllFilter(programCostTourSearchFilterRequest);
        ProgramCostTourSearchResponse programCostTourSearchResponse = new ProgramCostTourSearchResponse();
        programCostTourSearchResponse.setProgramCostTours(pageDTO.getContent().stream().map( programCostTourDomain ->  ProgramCostTourResponse.from(programCostTourDomain)).toList());
        programCostTourSearchResponse.setTotalElements(pageDTO.getTotalElements());
        programCostTourSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return programCostTourSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramCostTourResponse> searchAllByProgram(String idProgram) {
        List<ProgramCostTourDomain> list = repo.searchAllByProgram(idProgram);
        return list.stream().map(programCostTourDomain -> ProgramCostTourResponse.from(programCostTourDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramCostTourDomain> searchAllDomainByProgram(String idProgram) {
        List<ProgramCostTourDomain> list = repo.searchAllByProgram(idProgram);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramCostTourResponse searchByProgramAndProgramCostTour(String idProgram, String idProgramCostTour) {
        ProgramCostTourDomain programCostTourDomain = repo.searchAllByProgramAndProgramCostTour(idProgram, idProgramCostTour);
        return ProgramCostTourResponse.from(programCostTourDomain);
    }

}
