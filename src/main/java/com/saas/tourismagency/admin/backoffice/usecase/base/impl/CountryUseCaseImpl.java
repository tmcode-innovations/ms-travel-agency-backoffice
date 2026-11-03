package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultCountry;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.CountryUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CountryUseCaseImpl implements CountryUseCase {

    @Autowired
    private CountryRepo repo;

    @Autowired
    private CountryConverter converter;

    @Autowired
    private SetFieldDefaultCountry setFieldDefault;

    @Override
    @Transactional
    public CountryResponse create(CountryCreateRequest request) {
        CountryDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CountryDomain createdDomain = repo.create(newDomain);
        return CountryResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CountryResponse edit(String id, CountryUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CountryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No country found with id '%s'.".formatted(id));
        }
        CountryDomain countryDomain = request.to();
        CountryDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(countryDomain, old));
        return CountryResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CountryResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryResponse> findAll() {
        return repo.findAll(false).stream().map(countryDomain -> CountryResponse.from(countryDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CountryResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CountryDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No country found with id '%s'.".formatted(id));
        }
        return CountryResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CountrySearchResponse searchAll(CountrySearchRequest countrySearchRequest) {
       PageDTO<CountryDomain> pageDTO = repo.searchAll(countrySearchRequest);
       CountrySearchResponse countrySearchRespose = new CountrySearchResponse();
       countrySearchRespose.setCountries(pageDTO.getContent().stream().map(countryDomain ->  CountryResponse.from(countryDomain)).toList());
       countrySearchRespose.setTotalElements(pageDTO.getTotalElements());
       countrySearchRespose.setTotalPages(pageDTO.getTotalPages());
       return countrySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CountrySearchResponse searchFilterAll(CountrySearchFilterRequest countrySearchFilterRequest) {
        PageDTO<CountryDomain> pageDTO = repo.searchAllFilter(countrySearchFilterRequest);
        CountrySearchResponse countrySearchRespose = new CountrySearchResponse();
        countrySearchRespose.setCountries(pageDTO.getContent().stream().map(countryDomain ->  CountryResponse.from(countryDomain)).toList());
        countrySearchRespose.setTotalElements(pageDTO.getTotalElements());
        countrySearchRespose.setTotalPages(pageDTO.getTotalPages());
        return countrySearchRespose;
    }
}
