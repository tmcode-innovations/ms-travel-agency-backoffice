package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.object.ObjectConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultObject;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.objectsecurity.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.ObjectUseCase;
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
public class ObjectUseCaseImpl implements ObjectUseCase {

    @Autowired
    private ObjectRepo repo;

    @Autowired
    private ObjectConverter converter;

    @Autowired
    private SetFieldDefaultObject setFieldDefault;

    @Override
    @Transactional
    public ObjectResponse create(ObjectCreateRequest request) {
        ObjectDomain objectDomainExist = repo.findByName(request.getName());
        if (objectDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ObjectDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ObjectDomain createdDomain = repo.create(newDomain);
        return ObjectResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ObjectResponse edit(String id, ObjectUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ObjectDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No object found with id '%s'.".formatted(id));
        }
        ObjectDomain objectDomainExist = repo.findByName(request.getName());
        if (objectDomainExist != null && !objectDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        ObjectDomain objectDomain = request.to();
        ObjectDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(objectDomain, old));
        return ObjectResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ObjectResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObjectResponse> findAll() {
        return repo.findAll(false).stream().map(objectDomain -> ObjectResponse.from(objectDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ObjectResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ObjectDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No object found with id '%s'.".formatted(id));
        }
        return ObjectResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ObjectSearchResponse searchAll(ObjectSearchRequest objectSearchRequest) {
       PageDTO<ObjectDomain> pageDTO = repo.searchAll(objectSearchRequest);
       ObjectSearchResponse objectSearchRespose = new ObjectSearchResponse();
       objectSearchRespose.setObjects(pageDTO.getContent().stream().map(objectDomain ->  ObjectResponse.from(objectDomain)).toList());
       objectSearchRespose.setTotalElements(pageDTO.getTotalElements());
       objectSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return objectSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ObjectSearchResponse searchFilterAll(ObjectSearchFilterRequest objectSearchFilterRequest) {
        PageDTO<ObjectDomain> pageDTO = repo.searchAllFilter(objectSearchFilterRequest);
        ObjectSearchResponse objectSearchRespose = new ObjectSearchResponse();
        objectSearchRespose.setObjects(pageDTO.getContent().stream().map(objectDomain ->  ObjectResponse.from(objectDomain)).toList());
        objectSearchRespose.setTotalElements(pageDTO.getTotalElements());
        objectSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return objectSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ObjectResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ObjectDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No object found with name '%s'.".formatted(name));
        }
        return ObjectResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ObjectResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ObjectDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No object found with identify '%s'.".formatted(identify));
        }
        return ObjectResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
