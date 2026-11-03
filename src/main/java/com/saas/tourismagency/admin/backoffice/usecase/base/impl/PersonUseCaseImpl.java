package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonUpdateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.PersonUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PersonUseCaseImpl implements PersonUseCase {

    @Autowired
    private PersonRepo repo;

    @Autowired
    private PersonConverter converter;

    @Autowired
    private SetFieldDefaultPerson setFieldDefault;

    @Override
    @Transactional
    public PersonResponse create(PersonCreateRequest request) {
        PersonDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdDomain = repo.create(newDomain);
        return PersonResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PersonResponse edit(String id, PersonUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PersonDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(id));
        }
        PersonDomain personDomain = request.to();
        PersonDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(personDomain, old));
        return PersonResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PersonResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findAll() {
        return repo.findAll(false).stream().map(personDomain -> PersonResponse.from(personDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PersonDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(id));
        }
        return PersonResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSearchResponse searchAll(PersonSearchRequest personSearchRequest) {
       PageDTO<PersonDomain> pageDTO = repo.searchAll(personSearchRequest);
       PersonSearchResponse personSearchRespose = new PersonSearchResponse();
       personSearchRespose.setPersons(pageDTO.getContent().stream().map(personDomain ->  PersonResponse.from(personDomain)).toList());
       personSearchRespose.setTotalElements(pageDTO.getTotalElements());
       personSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return personSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSearchResponse searchFilterAll(PersonSearchFilterRequest personSearchFilterRequest) {
        PageDTO<PersonDomain> pageDTO = repo.searchAllFilter(personSearchFilterRequest);
        PersonSearchResponse personSearchRespose = new PersonSearchResponse();
        personSearchRespose.setPersons(pageDTO.getContent().stream().map(personDomain ->  PersonResponse.from(personDomain)).toList());
        personSearchRespose.setTotalElements(pageDTO.getTotalElements());
        personSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return personSearchRespose;
    }
}
