package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultDistrict;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.DistrictUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DistrictUseCaseImpl implements DistrictUseCase {

    @Autowired
    private DistrictRepo repo;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private DepartmentRegionRepo repoDepartmentRegion;

    @Autowired
    private ProvinceRepo repoProvince;

    @Autowired
    private DistrictConverter converter;

    @Autowired
    private SetFieldDefaultDistrict setFieldDefault;

    @Override
    @Transactional
    public DistrictResponse create(DistrictCreateRequest request) {
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = repoDepartmentRegion.findById(request.getDepartmentRegion());
        }
        ProvinceDomain provinceDomain = null;
        if (request.getProvince() != null && !request.getProvince().isEmpty()){
            provinceDomain = repoProvince.findById(request.getProvince());
        }
        DistrictDomain newDomain = request.to(provinceDomain, departmentRegionDomain, countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        DistrictDomain createdDomain = repo.create(newDomain);
        return DistrictResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public DistrictResponse edit(String id, DistrictUpdateRequest request) {
        if (id == null) {
            return null;
        }
        DistrictDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No district found with id '%s'.".formatted(id));
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = repoDepartmentRegion.findById(request.getDepartmentRegion());
        }
        ProvinceDomain provinceDomain = null;
        if (request.getProvince() != null && !request.getProvince().isEmpty()){
            provinceDomain = repoProvince.findById(request.getProvince());
        }
        DistrictDomain districtDomain = request.to(provinceDomain, departmentRegionDomain, countryDomain);
        DistrictDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(districtDomain, old));
        return DistrictResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        DistrictResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DistrictResponse> findAll() {
        return repo.findAll(false).stream().map(districtDomain -> DistrictResponse.from(districtDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DistrictResponse findById(String id) {
        if (id == null) {
            return null;
        }
        DistrictDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No district found with id '%s'.".formatted(id));
        }
        return DistrictResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public DistrictSearchResponse searchAll(DistrictSearchRequest districtSearchRequest) {
       PageDTO<DistrictDomain> pageDTO = repo.searchAll(districtSearchRequest);
        DistrictSearchResponse districtSearchRespose = new DistrictSearchResponse();
        districtSearchRespose.setDistricts(pageDTO.getContent().stream().map(districtDomain ->  DistrictResponse.from(districtDomain)).toList());
        districtSearchRespose.setTotalElements(pageDTO.getTotalElements());
        districtSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return districtSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DistrictSearchResponse searchFilterAll(DistrictSearchFilterRequest districtSearchFilterRequest) {
        PageDTO<DistrictDomain> pageDTO = repo.searchAllFilter(districtSearchFilterRequest);
        DistrictSearchResponse districtSearchRespose = new DistrictSearchResponse();
        districtSearchRespose.setDistricts(pageDTO.getContent().stream().map(districtDomain ->  DistrictResponse.from(districtDomain)).toList());
        districtSearchRespose.setTotalElements(pageDTO.getTotalElements());
        districtSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return districtSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public DistrictWithoutPageSearchResponse searchAllByProvince(BaseSearchRequestById baseSearchRequestById) {
        List<DistrictDomain> list = repo.searchAllByProvince(baseSearchRequestById.getIdEntity());
        DistrictWithoutPageSearchResponse districtWithoutPageSearchResponse = new DistrictWithoutPageSearchResponse();
        districtWithoutPageSearchResponse.setEntities(list.stream().map(districtDomain ->  DistrictResponse.from(districtDomain)).toList());
        return districtWithoutPageSearchResponse;
    }
}
