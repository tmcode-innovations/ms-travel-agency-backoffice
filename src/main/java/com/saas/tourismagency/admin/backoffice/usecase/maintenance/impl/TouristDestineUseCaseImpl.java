package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultTouristDestine;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.PersistTouristDestineNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.TouristDestineUseCase;
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
public class TouristDestineUseCaseImpl implements TouristDestineUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private TouristDestineRepo repo;

    @Autowired
    private TouristDestineConverter converter;

    @Autowired
    private SetFieldDefaultTouristDestine setFieldDefault;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private DepartmentRegionRepo repoDepartmentRegion;

    @Autowired
    private ProvinceRepo repoProvince;

    @Autowired
    private DistrictRepo repoDistrict;

    @Autowired
    private StateRepo repoState;

    @Autowired
    PersistTouristDestineNumberCallbackImpl persistTouristDestineNumberCallbackImpl;

    @Override
    @Transactional
    public TouristDestineResponse create(TouristDestineCreateRequest request) {
        TouristDestineDomain touristDestineDomainExist = repo.findByName(request.getName());
        if (touristDestineDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
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
        DistrictDomain districtDomain = null;
        if (request.getDistrict() != null && !request.getDistrict().isEmpty()){
            districtDomain = repoDistrict.findById(request.getDistrict());
        }
        StateDomain stateDomain = null;
        if (request.getState() != null && !request.getState().isEmpty()){
            stateDomain = repoState.findById(request.getState());
        }
        TouristDestineDomain newDomain = request.to(countryDomain, departmentRegionDomain, provinceDomain, districtDomain, stateDomain);
        newDomain.setIdentify(persistTouristDestineNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        TouristDestineDomain createdDomain = repo.create(newDomain);
        return TouristDestineResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TouristDestineResponse edit(String id, TouristDestineUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TouristDestineDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tourist destine found with id '%s'.".formatted(id));
        }
        TouristDestineDomain touristDestineDomainExist = repo.findByName(request.getName());
        if (touristDestineDomainExist != null && !touristDestineDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
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
        DistrictDomain districtDomain = null;
        if (request.getDistrict() != null && !request.getDistrict().isEmpty()){
            districtDomain = repoDistrict.findById(request.getDistrict());
        }
        StateDomain stateDomain = null;
        if (request.getState() != null && !request.getState().isEmpty()){
            stateDomain = repoState.findById(request.getState());
        }
        TouristDestineDomain touristDestineDomain = request.to(countryDomain, departmentRegionDomain, provinceDomain, districtDomain, stateDomain);
        touristDestineDomain.setIdentify(old.getIdentify());
        TouristDestineDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(touristDestineDomain, old));
        return TouristDestineResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TouristDestineResponse old = findById(id);
        if (referenceGuard.isReferenced("TouristDestine", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TouristDestineResponse> findAll() {
        return repo.findAll(false).stream().map(touristDestineDomain -> TouristDestineResponse.from(touristDestineDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TouristDestineResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TouristDestineDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tourist destine found with id '%s'.".formatted(id));
        }
        return TouristDestineResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TouristDestineSearchResponse searchAll(TouristDestineSearchRequest customerSearchRequest) {
       PageDTO<TouristDestineDomain> pageDTO = repo.searchAll(customerSearchRequest);
       TouristDestineSearchResponse customerSearchRespose = new TouristDestineSearchResponse();
       customerSearchRespose.setTouristDestines(pageDTO.getContent().stream().map(touristDestineDomain ->  TouristDestineResponse.from(touristDestineDomain)).toList());
       customerSearchRespose.setTotalElements(pageDTO.getTotalElements());
       customerSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return customerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TouristDestineSearchResponse searchFilterAll(TouristDestineSearchFilterRequest customerSearchFilterRequest) {
        PageDTO<TouristDestineDomain> pageDTO = repo.searchAllFilter(customerSearchFilterRequest);
        TouristDestineSearchResponse customerSearchRespose = new TouristDestineSearchResponse();
        customerSearchRespose.setTouristDestines(pageDTO.getContent().stream().map(touristDestineDomain ->  TouristDestineResponse.from(touristDestineDomain)).toList());
        customerSearchRespose.setTotalElements(pageDTO.getTotalElements());
        customerSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return customerSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TouristDestineResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        TouristDestineDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No tourist destine found with name '%s'.".formatted(name));
        }
        return TouristDestineResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TouristDestineResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        TouristDestineDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No tourist destine found with identify '%s'.".formatted(identify));
        }
        return TouristDestineResponse.from(old);
    }
}
