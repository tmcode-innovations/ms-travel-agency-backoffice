package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;

import dev.mm.javaee.core.exceptions.BadRequestException;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.accessvoucheregress.AccessVoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultAccessVoucherEgress;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.accessvoucheregress.AccessVoucherEgressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.accessvoucheregress.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.AccessVoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.AccessVoucherEgressUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccessVoucherEgressUseCaseImpl implements AccessVoucherEgressUseCase {

    @Autowired
    private AccessVoucherEgressRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccessVoucherEgressConverter converter;

    @Autowired
    private SetFieldDefaultAccessVoucherEgress setFieldDefault;

    @Override
    @Transactional
    public AccessVoucherEgressResponse create(AccessVoucherEgressCreateRequest request) {
        UserDomain userDomain = null;
        if (request.getUser() != null && !request.getUser().isEmpty()){
            userDomain = userRepo.findById(request.getUser());
        }

        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        AccessVoucherEgressDomain newDomain = request.to(userDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        AccessVoucherEgressDomain createdDomain = repo.create(newDomain);
        return AccessVoucherEgressResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public AccessVoucherEgressResponse edit(String id, AccessVoucherEgressUpdateRequest request) {
        if (id == null) {
            return null;
        }
        AccessVoucherEgressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

        UserDomain userDomain = null;
        if (request.getUser() != null && !request.getUser().isEmpty()){
            userDomain = userRepo.findById(request.getUser());
        }

        AccessVoucherEgressDomain accessVoucherEgressDomain = request.to(userDomain);
        AccessVoucherEgressDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(accessVoucherEgressDomain, old));

        return AccessVoucherEgressResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        AccessVoucherEgressResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccessVoucherEgressResponse> findAll() {
        return repo.findAll(false).stream().map(accessVoucherEgressDomain -> AccessVoucherEgressResponse.from(accessVoucherEgressDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccessVoucherEgressResponse findById(String id) {
        if (id == null) {
            return null;
        }
        AccessVoucherEgressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return AccessVoucherEgressResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AccessVoucherEgressDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        AccessVoucherEgressDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No entity with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public AccessVoucherEgressSearchResponse searchAll(AccessVoucherEgressSearchRequest searchRequest) {
       PageDTO<AccessVoucherEgressDomain> pageDTO = repo.searchAll(searchRequest);
       AccessVoucherEgressSearchResponse searchRespose = new AccessVoucherEgressSearchResponse();
       searchRespose.setItems(pageDTO.getContent().stream().map(domain ->  AccessVoucherEgressResponse.from(domain)).toList());
       searchRespose.setTotalElements(pageDTO.getTotalElements());
       searchRespose.setTotalPages(pageDTO.getTotalPages());
       return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AccessVoucherEgressSearchResponse searchFilterAll(AccessVoucherEgressSearchFilterRequest searchFilterRequest) {
        PageDTO<AccessVoucherEgressDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        AccessVoucherEgressSearchResponse searchRespose = new AccessVoucherEgressSearchResponse();
        searchRespose.setItems(pageDTO.getContent().stream().map(domain ->  AccessVoucherEgressResponse.from(domain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    @Override
    public boolean userWithAccessVoucherEgress(BaseSearchRequestById request) {
        List<AccessVoucherEgressResponse> list = findAll();
        list = list.stream().filter(accessVoucherEgressResponse -> accessVoucherEgressResponse.getUser().getId().equals(request.getIdEntity())).collect(Collectors.toList());
        if (list != null && !list.isEmpty()){
            return true;
        }
;       return false;
    }
}
