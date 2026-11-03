package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultChangeType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ChangeTypeUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ChangeTypeUseCaseImpl implements ChangeTypeUseCase {

    @Autowired
    private ChangeTypeRepo repo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ChangeTypeConverter converter;

    @Autowired
    private SetFieldDefaultChangeType setFieldDefault;

    @Override
    @Transactional
    public ChangeTypeResponse create(ChangeTypeCreateRequest request) {
        ChangeTypeDomain changeType = repo.findByDate(request.getDateProcessDate());
        if (changeType != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_CHANGE_TYPE_FOR_DATE);
        }
        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcessDate() != null){
            dateProcessDomain = dateProcessRepo.findByDate(request.getDateProcessDate(), UserResolver.resolveUser().getTenant());
            if (dateProcessDomain == null){
                dateProcessDomain = new DateProcessDomain();
                dateProcessDomain.setDate(request.getDateProcessDate());
                dateProcessDomain.setTenant(UserResolver.resolveUser().getTenant());
                dateProcessDomain = dateProcessRepo.create(dateProcessDomain);
            }
        }
        ChangeTypeDomain newDomain = request.to(dateProcessDomain);
        newDomain.setDate(request.getDateProcessDate());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ChangeTypeDomain createdDomain = repo.create(newDomain);
        return ChangeTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ChangeTypeResponse edit(String id, ChangeTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ChangeTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcess() != null && !request.getDateProcess().isEmpty()){
            IdBaseDomain dateProcessIdBaseDomain = dateProcessRepo.findByIdEntity(request.getDateProcess());
            dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(dateProcessIdBaseDomain.getId());
        }
        ChangeTypeDomain changeTypeDomain = request.to(dateProcessDomain);
        changeTypeDomain.setDate(old.getDate());
        ChangeTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(changeTypeDomain, old));
        return ChangeTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ChangeTypeResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeTypeResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ChangeTypeResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ChangeTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ChangeTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeTypeSearchResponse searchAll(ChangeTypeSearchRequest bankSearchRequest) {
       PageDTO<ChangeTypeDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ChangeTypeSearchResponse bankSearchRespose = new ChangeTypeSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ChangeTypeResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeTypeSearchResponse searchFilterAll(ChangeTypeSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ChangeTypeDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ChangeTypeSearchResponse bankSearchRespose = new ChangeTypeSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ChangeTypeResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeTypeResponse findByDate(ChangeTypeFindByDateRequest changeTypeFindByDateRequest) {
        ChangeTypeDomain old = repo.findByDate(changeTypeFindByDateRequest.getDate());
        if (old == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_DATE_PROCESS_NOT_EXIST_BY_CHANGE_TYPE);
        }
        return ChangeTypeResponse.from(old);
    }
}
