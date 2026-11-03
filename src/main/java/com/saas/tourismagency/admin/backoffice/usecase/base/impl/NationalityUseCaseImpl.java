package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.nationality.NationalityConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultNationality;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.NationalityUseCase;
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
public class NationalityUseCaseImpl implements NationalityUseCase {

    @Autowired
    private NationalityRepo repo;

    @Autowired
    private NationalityConverter converter;

    @Autowired
    private SetFieldDefaultNationality setFieldDefault;

    @Override
    @Transactional
    public NationalityResponse create(NationalityCreateRequest request) {
        NationalityDomain nationalityDomainExist = repo.findByName(request.getName());
        if (nationalityDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        NationalityDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        NationalityDomain createdDomain = repo.create(newDomain);
        return NationalityResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public NationalityResponse edit(String id, NationalityUpdateRequest request) {
        if (id == null) {
            return null;
        }
        NationalityDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No nationality found with id '%s'.".formatted(id));
        }
        NationalityDomain nationalityDomainExist = repo.findByName(request.getName());
        if (nationalityDomainExist != null && !nationalityDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        NationalityDomain nationalityDomain = request.to();
        NationalityDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(nationalityDomain, old));
        return NationalityResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        NationalityResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NationalityResponse> findAll() {
        return repo.findAll(false).stream().map(nationalityDomain -> NationalityResponse.from(nationalityDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NationalityResponse findById(String id) {
        if (id == null) {
            return null;
        }
        NationalityDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No nationality found with id '%s'.".formatted(id));
        }
        return NationalityResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public NationalitySearchResponse searchAll(NationalitySearchRequest nationalitySearchRequest) {
       PageDTO<NationalityDomain> pageDTO = repo.searchAll(nationalitySearchRequest);
       NationalitySearchResponse nationalitySearchRespose = new NationalitySearchResponse();
       nationalitySearchRespose.setNationalities(pageDTO.getContent().stream().map(nationalityDomain ->  NationalityResponse.from(nationalityDomain)).toList());
       nationalitySearchRespose.setTotalElements(pageDTO.getTotalElements());
       nationalitySearchRespose.setTotalPages(pageDTO.getTotalPages());
       return nationalitySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public NationalitySearchResponse searchFilterAll(NationalitySearchFilterRequest nationalitySearchFilterRequest) {
        PageDTO<NationalityDomain> pageDTO = repo.searchAllFilter(nationalitySearchFilterRequest);
        NationalitySearchResponse nationalitySearchRespose = new NationalitySearchResponse();
        nationalitySearchRespose.setNationalities(pageDTO.getContent().stream().map(nationalityDomain ->  NationalityResponse.from(nationalityDomain)).toList());
        nationalitySearchRespose.setTotalElements(pageDTO.getTotalElements());
        nationalitySearchRespose.setTotalPages(pageDTO.getTotalPages());
        return nationalitySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public NationalityResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        NationalityDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No nationality found with name '%s'.".formatted(name));
        }
        return NationalityResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
