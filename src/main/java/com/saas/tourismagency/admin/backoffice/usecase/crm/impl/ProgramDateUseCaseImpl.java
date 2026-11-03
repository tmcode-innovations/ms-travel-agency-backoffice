package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate.ProgramDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgramDate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramDateUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class ProgramDateUseCaseImpl implements ProgramDateUseCase {

    @Autowired
    private ProgramDateRepo repo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ProgramDateConverter converter;

    @Autowired
    private SetFieldDefaultProgramDate setFieldDefault;

    @Override
    @Transactional
    public ProgramDateResponse create(ProgramDateCreateRequest request) {
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            programDomain = programRepo.findById(request.getProgram());
        }
        ProgramDateDomain newDomain = request.to(programDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramDateDomain createdDomain = repo.create(newDomain);
        return ProgramDateResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramDateResponse edit(String id, ProgramDateUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProgramDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            programDomain = programRepo.findById(request.getProgram());
        }
        ProgramDateDomain programDateDomain = request.to(programDomain);
        ProgramDateDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programDateDomain, old));
        return ProgramDateResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProgramDateResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramDateResponse> findAll() {
        return repo.findAll().stream().map(programDateDomain -> ProgramDateResponse.from(programDateDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDateResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProgramDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No program date found with id '%s'.".formatted(id));
        }
        return ProgramDateResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDateDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        ProgramDateDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No program date found with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDateSearchResponse searchAll(ProgramDateSearchRequest programSearchRequest) {
       PageDTO<ProgramDateDomain> pageDTO = repo.searchAll(programSearchRequest);
       ProgramDateSearchResponse programDateSearchRespose = new ProgramDateSearchResponse();
       programDateSearchRespose.setProgramDates(pageDTO.getContent().stream().map(programDateDomain ->  ProgramDateResponse.from(programDateDomain)).toList());
       programDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
       programDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return programDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDateSearchResponse searchFilterAll(ProgramDateSearchFilterRequest programSearchFilterRequest) {
        PageDTO<ProgramDateDomain> pageDTO = repo.searchAllFilter(programSearchFilterRequest);
        ProgramDateSearchResponse programDateSearchRespose = new ProgramDateSearchResponse();
        programDateSearchRespose.setProgramDates(pageDTO.getContent().stream().map(programDateDomain ->  ProgramDateResponse.from(programDateDomain)).toList());
        programDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
        programDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return programDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramDateResponse> searchAllByProgram(String idProgram) {
        List<ProgramDateDomain> list = repo.searchAllByProgram(idProgram);
        return list.stream().map(programDateDomain ->  ProgramDateResponse.from(programDateDomain)).toList();
    }

    @Override
    @Transactional
    public void generateDateProgram(ProgramDomain program, Integer days) {
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> createDateProgram(program, item));
    }

    private void createDateProgram(ProgramDomain program, Integer day){
        ProgramDateDomain programDateDomain = new ProgramDateDomain();
        Integer indexDay = day + 1;
        // programDateDomain.setName("Dia " + indexDay);
        programDateDomain.setName("");
        programDateDomain.setProgram(program);
        programDateDomain.setDay(indexDay);
        programDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        repo.create(programDateDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramDateDomain> searchAllDomainByProgram(String idProgram) {
        List<ProgramDateDomain> list = repo.searchAllByProgram(idProgram);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramDateResponse searchByProgramAndProgramDate(String idProgram, String idProgramDate) {
        ProgramDateDomain programDateDomain = repo.searchAllByProgramAndProgramDate(idProgram, idProgramDate);
        return ProgramDateResponse.from(programDateDomain);
    }
}
