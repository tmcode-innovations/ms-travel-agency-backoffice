package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultIdiom;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.IdiomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.IdiomUseCase;
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
public class IdiomUseCaseImpl implements IdiomUseCase {

    @Autowired
    private IdiomRepo repo;

    @Autowired
    private IdiomConverter converter;

    @Autowired
    private SetFieldDefaultIdiom setFieldDefault;

    @Override
    @Transactional
    public IdiomResponse create(IdiomCreateRequest request) {
        IdiomDomain idiomDomainExist = repo.findByName(request.getName());
        if (idiomDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        IdiomDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        IdiomDomain createdDomain = repo.create(newDomain);
        return IdiomResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public IdiomResponse edit(String id, IdiomUpdateRequest request) {
        if (id == null) {
            return null;
        }
        IdiomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No idiom found with id '%s'.".formatted(id));
        }
        IdiomDomain idiomDomainExist = repo.findByName(request.getName());
        if (idiomDomainExist != null && !idiomDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        IdiomDomain idiomDomain = request.to();
        IdiomDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(idiomDomain, old));
        return IdiomResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        IdiomResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdiomResponse> findAll() {
        return repo.findAll(false).stream().map(idiomDomain -> IdiomResponse.from(idiomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public IdiomResponse findById(String id) {
        if (id == null) {
            return null;
        }
        IdiomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No idiom found with id '%s'.".formatted(id));
        }
        return IdiomResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public IdiomSearchResponse searchAll(IdiomSearchRequest idiomSearchRequest) {
       PageDTO<IdiomDomain> pageDTO = repo.searchAll(idiomSearchRequest);
       IdiomSearchResponse idiomSearchRespose = new IdiomSearchResponse();
       idiomSearchRespose.setIdioms(pageDTO.getContent().stream().map(idiomDomain ->  IdiomResponse.from(idiomDomain)).toList());
       idiomSearchRespose.setTotalElements(pageDTO.getTotalElements());
       idiomSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return idiomSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public IdiomSearchResponse searchFilterAll(IdiomSearchFilterRequest idiomSearchFilterRequest) {
        PageDTO<IdiomDomain> pageDTO = repo.searchAllFilter(idiomSearchFilterRequest);
        IdiomSearchResponse idiomSearchRespose = new IdiomSearchResponse();
        idiomSearchRespose.setIdioms(pageDTO.getContent().stream().map(idiomDomain ->  IdiomResponse.from(idiomDomain)).toList());
        idiomSearchRespose.setTotalElements(pageDTO.getTotalElements());
        idiomSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return idiomSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public IdiomResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        IdiomDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No idiom found with name '%s'.".formatted(name));
        }
        return IdiomResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
