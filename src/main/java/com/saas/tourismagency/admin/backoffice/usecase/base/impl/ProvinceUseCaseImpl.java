package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultProvince;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.ProvinceUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProvinceUseCaseImpl implements ProvinceUseCase {

    @Autowired
    private ProvinceRepo repo;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private DepartmentRegionRepo repoDepartmentRegion;

    @Autowired
    private ProvinceConverter converter;

    @Autowired
    private SetFieldDefaultProvince setFieldDefault;

    @Override
    @Transactional
    public ProvinceResponse create(ProvinceCreateRequest request) {
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = repoDepartmentRegion.findById(request.getDepartmentRegion());
        }
        ProvinceDomain newDomain = request.to(departmentRegionDomain, countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProvinceDomain createdDomain = repo.create(newDomain);
        return ProvinceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProvinceResponse edit(String id, ProvinceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProvinceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No province found with id '%s'.".formatted(id));
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = repoDepartmentRegion.findById(request.getDepartmentRegion());
        }
        ProvinceDomain provinceDomain = request.to(departmentRegionDomain, countryDomain);
        ProvinceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(provinceDomain, old));
        return ProvinceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProvinceResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvinceResponse> findAll() {
        return repo.findAll(false).stream().map(provinceDomain -> ProvinceResponse.from(provinceDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProvinceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProvinceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No province found with id '%s'.".formatted(id));
        }
        return ProvinceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProvinceSearchResponse searchAll(ProvinceSearchRequest provinceSearchRequest) {
       PageDTO<ProvinceDomain> pageDTO = repo.searchAll(provinceSearchRequest);
        ProvinceSearchResponse provinceSearchRespose = new ProvinceSearchResponse();
        provinceSearchRespose.setProvinces(pageDTO.getContent().stream().map(provinceDomain ->  ProvinceResponse.from(provinceDomain)).toList());
        provinceSearchRespose.setTotalElements(pageDTO.getTotalElements());
        provinceSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return provinceSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProvinceSearchResponse searchFilterAll(ProvinceSearchFilterRequest provinceSearchFilterRequest) {
        PageDTO<ProvinceDomain> pageDTO = repo.searchAllFilter(provinceSearchFilterRequest);
        ProvinceSearchResponse provinceSearchRespose = new ProvinceSearchResponse();
        provinceSearchRespose.setProvinces(pageDTO.getContent().stream().map(provinceDomain ->  ProvinceResponse.from(provinceDomain)).toList());
        provinceSearchRespose.setTotalElements(pageDTO.getTotalElements());
        provinceSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return provinceSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProvinceWithoutPageSearchResponse searchAllByDepartmentRegion(BaseSearchRequestById baseSearchRequestById) {
        List<ProvinceDomain> list = repo.searchAllByDepartmentRegion(baseSearchRequestById.getIdEntity());
        ProvinceWithoutPageSearchResponse provinceWithoutPageSearchResponse = new ProvinceWithoutPageSearchResponse();
        provinceWithoutPageSearchResponse.setEntities(list.stream().map(provinceDomain ->  ProvinceResponse.from(provinceDomain)).toList());
        return provinceWithoutPageSearchResponse;
    }
}
