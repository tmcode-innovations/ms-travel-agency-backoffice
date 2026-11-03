package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area.AreaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultArea;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.area.AreaRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.area.PersistAreaNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.CompanyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.AreaUseCase;
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
public class AreaUseCaseImpl implements AreaUseCase {

    @Autowired
    private AreaRepo repo;

    @Autowired
    private CompanyRepo repoCompany;

    @Autowired
    private AreaConverter converter;

    @Autowired
    private SetFieldDefaultArea setFieldDefault;

    @Autowired
    PersistAreaNumberCallbackImpl persistAreaNumberCallbackImpl;

    @Override
    @Transactional
    public AreaResponse create(AreaCreateRequest request) {
        AreaDomain areaDomainExist = repo.findByName(request.getName());
        if (areaDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

        CompanyDomain companyDomain = null;
        if (request.getCompany() != null && !request.getCompany().isEmpty()){
            companyDomain = repoCompany.findById(request.getCompany());
        }

        AreaDomain newDomain = request.to(companyDomain);
        newDomain.setIdentify(persistAreaNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        AreaDomain createdDomain = repo.create(newDomain);
        return AreaResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public AreaResponse edit(String id, AreaUpdateRequest request) {
        if (id == null) {
            return null;
        }
        AreaDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No area found with id '%s'.".formatted(id));
        }
        AreaDomain areaDomainExist = repo.findByName(request.getName());
        if (areaDomainExist != null && !areaDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        CompanyDomain companyDomain = null;
        if (request.getCompany() != null && !request.getCompany().isEmpty()){
            companyDomain = repoCompany.findById(request.getCompany());
        }
        AreaDomain areaDomain = request.to(companyDomain);
        areaDomain.setIdentify(old.getIdentify());
        AreaDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(areaDomain, old));
        return AreaResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        AreaResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaResponse> findAll() {
        return repo.findAll(false).stream().map(areaDomain -> AreaResponse.from(areaDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AreaResponse findById(String id) {
        if (id == null) {
            return null;
        }
        AreaDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No area found with id '%s'.".formatted(id));
        }
        return AreaResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AreaSearchResponse searchAll(AreaSearchRequest areaSearchRequest) {
       PageDTO<AreaDomain> pageDTO = repo.searchAll(areaSearchRequest);
       AreaSearchResponse areaSearchRespose = new AreaSearchResponse();
       areaSearchRespose.setAreas(pageDTO.getContent().stream().map(areaDomain ->  AreaResponse.from(areaDomain)).toList());
       areaSearchRespose.setTotalElements(pageDTO.getTotalElements());
       areaSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return areaSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AreaSearchResponse searchFilterAll(AreaSearchFilterRequest areaSearchFilterRequest) {
        PageDTO<AreaDomain> pageDTO = repo.searchAllFilter(areaSearchFilterRequest);
        AreaSearchResponse areaSearchRespose = new AreaSearchResponse();
        areaSearchRespose.setAreas(pageDTO.getContent().stream().map(areaDomain ->  AreaResponse.from(areaDomain)).toList());
        areaSearchRespose.setTotalElements(pageDTO.getTotalElements());
        areaSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return areaSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public AreaResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        AreaDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No bank found with name '%s'.".formatted(name));
        }
        return AreaResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public AreaResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        AreaDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No bank found with identify '%s'.".formatted(identify));
        }
        return AreaResponse.from(old);
    }
}
