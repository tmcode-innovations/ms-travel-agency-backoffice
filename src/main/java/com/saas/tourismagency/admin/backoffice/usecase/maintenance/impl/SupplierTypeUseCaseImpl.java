package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplierType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierTypeUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SupplierTypeUseCaseImpl implements SupplierTypeUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private SupplierTypeRepo repo;

    @Autowired
    private SupplierTypeConverter converter;

    @Autowired
    private SetFieldDefaultSupplierType setFieldDefault;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public SupplierTypeResponse create(SupplierTypeCreateRequest request) {
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        SupplierTypeDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SupplierTypeDomain createdDomain = repo.create(newDomain);
        return SupplierTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SupplierTypeResponse edit(String id, SupplierTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SupplierTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier type found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomain = request.to();
        supplierTypeDomain.setIdentify(old.getIdentify());
        supplierTypeDomain.setName(old.getName());
        SupplierTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(supplierTypeDomain, old));
        return SupplierTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        SupplierTypeResponse old = findById(id);
        if (referenceGuard.isReferenced("SupplierType", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierTypeResponse> findAll() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        return repo.findAll(false)
                .stream()
                .filter(supplierTypeDomain -> this.existSupplierTypeByCompany(supplierTypeDomain, companyDomain))
                .map(supplierTypeDomain -> SupplierTypeResponse.from(supplierTypeDomain))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SupplierTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier type found with id '%s'.".formatted(id));
        }
        return SupplierTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierTypeSearchResponse searchAll(SupplierTypeSearchRequest supplierTypeSearchRequest) {
       CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
       PageDTO<SupplierTypeDomain> pageDTO = repo.searchAll(supplierTypeSearchRequest);
       SupplierTypeSearchResponse supplierTypeSearchRespose = new SupplierTypeSearchResponse();
       supplierTypeSearchRespose.setSupplierTypes(pageDTO.getContent()
               .stream()
               .filter(supplierTypeDomain -> this.existSupplierTypeByCompany(supplierTypeDomain, companyDomain))
               .map(supplierTypeDomain ->  SupplierTypeResponse.from(supplierTypeDomain))
               .toList());
       supplierTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       supplierTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return supplierTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierTypeSearchResponse searchFilterAll(SupplierTypeSearchFilterRequest supplierTypeSearchFilterRequest) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        PageDTO<SupplierTypeDomain> pageDTO = repo.searchAllFilter(supplierTypeSearchFilterRequest);
        SupplierTypeSearchResponse supplierTypeSearchRespose = new SupplierTypeSearchResponse();
        supplierTypeSearchRespose.setSupplierTypes(pageDTO.getContent()
                .stream()
                .filter(supplierTypeDomain -> this.existSupplierTypeByCompany(supplierTypeDomain, companyDomain))
                .map(supplierTypeDomain ->  SupplierTypeResponse.from(supplierTypeDomain))
                .toList());
        supplierTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        supplierTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return supplierTypeSearchRespose;
    }

    private Boolean existSupplierTypeByCompany(SupplierTypeDomain supplierTypeDomain, CompanyDomain companyDomain){
        Boolean result = false;
        switch (supplierTypeDomain.getIdentify()) {
            case "1", "2", "3", "4", "5", "6", "7", "8" -> {
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())
                || companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    result = true;
                }
            }
            case "9", "10" -> {
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    result = false;
                }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    result = true;
                }
            }
            default -> {
                result = false;
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierTypeResponse> findAllFromOperationOrderService() {
        SupplierTypeDomain supplierTypeDomainOperator = repo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = repo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainTransport = repo.findOne("4", null);
        List<SupplierTypeResponse> result = List.of(supplierTypeDomainOperator, supplierTypeDomainLodge, supplierTypeDomainTransport)
                .stream()
                .map(supplierTypeDomain -> SupplierTypeResponse.from(supplierTypeDomain)).collect(Collectors.toList());
        return result;
    }
}
