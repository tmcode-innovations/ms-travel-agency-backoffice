package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.grouppqttourist.GroupPqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultGroupPqtTourist;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.GroupPqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.grouppqttourist.PersistGroupPqtTouristNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.GroupPqtTouristUseCase;
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
public class GroupPqtTouristUseCaseImpl implements GroupPqtTouristUseCase {

    @Autowired
    private GroupPqtTouristRepo repo;

    @Autowired
    private GroupPqtTouristConverter converter;

    @Autowired
    private SetFieldDefaultGroupPqtTourist setFieldDefault;

    @Autowired
    PersistGroupPqtTouristNumberCallbackImpl persistGroupPqtTouristNumberCallbackImpl;

    @Override
    @Transactional
    public GroupPqtTouristResponse create(GroupPqtTouristCreateRequest request) {
        GroupPqtTouristDomain groupPqtTouristDomainExist = repo.findByName(request.getName());
        if (groupPqtTouristDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupPqtTouristDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistGroupPqtTouristNumberCallbackImpl.generateNumber());
        GroupPqtTouristDomain createdDomain = repo.create(newDomain);
        return GroupPqtTouristResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public GroupPqtTouristResponse edit(String id, GroupPqtTouristUpdateRequest request) {
        if (id == null) {
            return null;
        }
        GroupPqtTouristDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        GroupPqtTouristDomain groupPqtTouristDomainExist = repo.findByName(request.getName());
        if (groupPqtTouristDomainExist != null && !groupPqtTouristDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupPqtTouristDomain groupPqtTouristDomain = request.to();
        groupPqtTouristDomain.setIdentify(old.getIdentify());
        GroupPqtTouristDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(groupPqtTouristDomain, old));
        return GroupPqtTouristResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        GroupPqtTouristResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupPqtTouristResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> GroupPqtTouristResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GroupPqtTouristResponse findById(String id) {
        if (id == null) {
            return null;
        }
        GroupPqtTouristDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return GroupPqtTouristResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupPqtTouristSearchResponse searchAll(GroupPqtTouristSearchRequest bankSearchRequest) {
       PageDTO<GroupPqtTouristDomain> pageDTO = repo.searchAll(bankSearchRequest);
       GroupPqtTouristSearchResponse bankSearchRespose = new GroupPqtTouristSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  GroupPqtTouristResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupPqtTouristSearchResponse searchFilterAll(GroupPqtTouristSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<GroupPqtTouristDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        GroupPqtTouristSearchResponse bankSearchRespose = new GroupPqtTouristSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  GroupPqtTouristResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupPqtTouristResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        GroupPqtTouristDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No group package tourist found with name '%s'.".formatted(name));
        }
        return GroupPqtTouristResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupPqtTouristResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        GroupPqtTouristDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No group package tourist found with identify '%s'.".formatted(identify));
        }
        return GroupPqtTouristResponse.from(old);
    }
}
