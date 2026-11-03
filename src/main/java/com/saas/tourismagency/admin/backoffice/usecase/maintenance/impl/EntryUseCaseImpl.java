package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.entry.EntryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultEntry;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry.EntryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.entry.PersistEntryNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.EntryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.EntryUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EntryUseCaseImpl implements EntryUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private EntryRepo repo;

    @Autowired
    private EntryConverter converter;

    @Autowired
    private SetFieldDefaultEntry setFieldDefault;

    @Autowired
    PersistEntryNumberCallbackImpl persistEntryNumberCallbackImpl;

    @Override
    @Transactional
    public EntryResponse create(EntryCreateRequest request) {
        EntryDomain entryDomainExist = repo.findByName(request.getName());
        if (entryDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        EntryDomain newDomain = request.to();
        newDomain.setIdentify(persistEntryNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        EntryDomain createdDomain = repo.create(newDomain);
        return EntryResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public EntryResponse edit(String id, EntryUpdateRequest request) {
        if (id == null) {
            return null;
        }
        EntryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entry found with id '%s'.".formatted(id));
        }
        EntryDomain entryDomainExist = repo.findByName(request.getName());
        if (entryDomainExist != null && !entryDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        EntryDomain entryDomain = request.to();
        entryDomain.setIdentify(old.getIdentify());
        EntryDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(entryDomain, old));
        return EntryResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        EntryResponse old = findById(id);
        if (referenceGuard.isReferenced("Entry", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntryResponse> findAll() {
        return repo.findAll(false).stream().map(entryDomain -> EntryResponse.from(entryDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EntryResponse findById(String id) {
        if (id == null) {
            return null;
        }
        EntryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No bank found with id '%s'.".formatted(id));
        }
        return EntryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EntrySearchResponse searchAll(EntrySearchRequest bankSearchRequest) {
       PageDTO<EntryDomain> pageDTO = repo.searchAll(bankSearchRequest);
       EntrySearchResponse bankSearchRespose = new EntrySearchResponse();
       bankSearchRespose.setEntries(pageDTO.getContent().stream().map(bankDomain ->  EntryResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EntrySearchResponse searchFilterAll(EntrySearchFilterRequest bankSearchFilterRequest) {
        PageDTO<EntryDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        EntrySearchResponse bankSearchRespose = new EntrySearchResponse();
        bankSearchRespose.setEntries(pageDTO.getContent().stream().map(bankDomain ->  EntryResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EntryResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        EntryDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No entry found with name '%s'.".formatted(name));
        }
        return EntryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EntryResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        EntryDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No entry found with identify '%s'.".formatted(identify));
        }
        return EntryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntryResponse> findAllSimpleDocumentFinance() {
        return repo.findAllSimpleDocumentFinance().stream().map(entryDomain -> EntryResponse.from(entryDomain)).toList();
    }
}
