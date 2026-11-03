package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.app.AppConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultApp;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.AppRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.AppUseCase;
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
public class AppUseCaseImpl implements AppUseCase {

    @Autowired
    private AppRepo repo;

    @Autowired
    private AppConverter converter;

    @Autowired
    private SetFieldDefaultApp setFieldDefault;

    @Override
    @Transactional
    public AppResponse create(AppCreateRequest request) {
        AppDomain appDomainExist = repo.findByName(request.getName());
        if (appDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        AppDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        AppDomain createdDomain = repo.create(newDomain);
        return AppResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public AppResponse edit(String id, AppUpdateRequest request) {
        if (id == null) {
            return null;
        }
        AppDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No app found with id '%s'.".formatted(id));
        }
        AppDomain appDomainExist = repo.findByName(request.getName());
        if (appDomainExist != null && !appDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        AppDomain appDomain = request.to();
        AppDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(appDomain, old));
        return AppResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        AppResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppResponse> findAll() {
        return repo.findAll(false).stream().map(appDomain -> AppResponse.from(appDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AppResponse findById(String id) {
        if (id == null) {
            return null;
        }
        AppDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No app found with id '%s'.".formatted(id));
        }
        return AppResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AppSearchResponse searchAll(AppSearchRequest appSearchRequest) {
       PageDTO<AppDomain> pageDTO = repo.searchAll(appSearchRequest);
       AppSearchResponse appSearchRespose = new AppSearchResponse();
       appSearchRespose.setApps(pageDTO.getContent().stream().map(appDomain ->  AppResponse.from(appDomain)).toList());
       appSearchRespose.setTotalElements(pageDTO.getTotalElements());
       appSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return appSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AppSearchResponse searchFilterAll(AppSearchFilterRequest appSearchFilterRequest) {
        PageDTO<AppDomain> pageDTO = repo.searchAllFilter(appSearchFilterRequest);
        AppSearchResponse appSearchRespose = new AppSearchResponse();
        appSearchRespose.setApps(pageDTO.getContent().stream().map(appDomain ->  AppResponse.from(appDomain)).toList());
        appSearchRespose.setTotalElements(pageDTO.getTotalElements());
        appSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return appSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AppResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        AppDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No app found with name '%s'.".formatted(name));
        }
        return AppResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AppResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        AppDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No app found with identify '%s'.".formatted(identify));
        }
        return AppResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
