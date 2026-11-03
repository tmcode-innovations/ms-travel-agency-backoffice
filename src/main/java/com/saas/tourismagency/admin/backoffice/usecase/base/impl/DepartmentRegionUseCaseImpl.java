package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultDepartmentRegion;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.DepartmentRegionUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DepartmentRegionUseCaseImpl implements DepartmentRegionUseCase {

    @Autowired
    private DepartmentRegionRepo repo;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private DepartmentRegionConverter converter;

    @Autowired
    private SetFieldDefaultDepartmentRegion setFieldDefault;

    @Override
    @Transactional
    public DepartmentRegionResponse create(DepartmentRegionCreateRequest request) {
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain newDomain = request.to(countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        DepartmentRegionDomain createdDomain = repo.create(newDomain);
        return DepartmentRegionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DepartmentRegionResponse edit(String id, DepartmentRegionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DepartmentRegionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No department region found with id '%s'.".formatted(id));
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain departmentRegionDomain = request.to(countryDomain);
        DepartmentRegionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(departmentRegionDomain, old));
        return DepartmentRegionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DepartmentRegionResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentRegionResponse> findAll() {
        return repo.findAll(false).stream().map(departmentRegionDomain -> DepartmentRegionResponse.from(departmentRegionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentRegionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DepartmentRegionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No department region found with id '%s'.".formatted(id));
        }
        return DepartmentRegionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentRegionSearchResponse searchAll(DepartmentRegionSearchRequest departmentRegionSearchRequest) {
       PageDTO<DepartmentRegionDomain> pageDTO = repo.searchAll(departmentRegionSearchRequest);
       DepartmentRegionSearchResponse departmentRegionSearchRespose = new DepartmentRegionSearchResponse();
       departmentRegionSearchRespose.setDepartmentRegions(pageDTO.getContent().stream().map(departmentRegionDomain ->  DepartmentRegionResponse.from(departmentRegionDomain)).toList());
       departmentRegionSearchRespose.setTotalElements(pageDTO.getTotalElements());
       departmentRegionSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return departmentRegionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentRegionSearchResponse searchFilterAll(DepartmentRegionSearchFilterRequest departmentRegionSearchFilterRequest) {
        PageDTO<DepartmentRegionDomain> pageDTO = repo.searchAllFilter(departmentRegionSearchFilterRequest);
        DepartmentRegionSearchResponse departmentRegionSearchRespose = new DepartmentRegionSearchResponse();
        departmentRegionSearchRespose.setDepartmentRegions(pageDTO.getContent().stream().map(departmentRegionDomain ->  DepartmentRegionResponse.from(departmentRegionDomain)).toList());
        departmentRegionSearchRespose.setTotalElements(pageDTO.getTotalElements());
        departmentRegionSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return departmentRegionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentRegionWithoutPageSearchResponse searchAllByCountry(BaseSearchRequestById baseSearchRequestById) {
        List<DepartmentRegionDomain> list = repo.searchAllByCountry(baseSearchRequestById.getIdEntity());
        DepartmentRegionWithoutPageSearchResponse departmentRegionWithoutPageSearchResponse = new DepartmentRegionWithoutPageSearchResponse();
        departmentRegionWithoutPageSearchResponse.setEntities(list.stream().map(departmentRegionDomain ->  DepartmentRegionResponse.from(departmentRegionDomain)).toList());
        return departmentRegionWithoutPageSearchResponse;
    }
}
