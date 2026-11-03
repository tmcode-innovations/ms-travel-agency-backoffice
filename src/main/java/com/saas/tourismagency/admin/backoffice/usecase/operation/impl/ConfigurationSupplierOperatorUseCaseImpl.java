package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.configurationsupplieroperator.ConfigurationSupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultConfigurationSupplierOperator;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.configurationsupplieroperator.ConfigurationSupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.usecase.operation.ConfigurationSupplierOperatorUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ConfigurationSupplierOperatorUseCaseImpl implements ConfigurationSupplierOperatorUseCase {

    @Autowired
    private ConfigurationSupplierOperatorRepo repo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private ConfigurationSupplierOperatorConverter converter;

    @Autowired
    private SetFieldDefaultConfigurationSupplierOperator setFieldDefault;

    @Override
    @Transactional
    public ConfigurationSupplierOperatorResponse create(ConfigurationSupplierOperatorCreateRequest request) {
        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            supplierOperatorDomain = supplierOperatorRepo.findById(request.getSupplierOperator());
        }

        ConfigurationSupplierOperatorDomain newDomain = request.to(supplierOperatorDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        ConfigurationSupplierOperatorDomain createdDomain = repo.create(newDomain);
        return ConfigurationSupplierOperatorResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ConfigurationSupplierOperatorResponse edit(String id, ConfigurationSupplierOperatorUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ConfigurationSupplierOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            supplierOperatorDomain = supplierOperatorRepo.findById(request.getSupplierOperator());
        }

        ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = request.to(supplierOperatorDomain);
        ConfigurationSupplierOperatorDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(configurationSupplierOperatorDomain, old));

        return ConfigurationSupplierOperatorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ConfigurationSupplierOperatorResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConfigurationSupplierOperatorResponse> findAll() {
        return repo.findAll(false).stream().map(configurationSupplierOperatorDomain -> ConfigurationSupplierOperatorResponse.from(configurationSupplierOperatorDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ConfigurationSupplierOperatorResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ConfigurationSupplierOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ConfigurationSupplierOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ConfigurationSupplierOperatorDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        ConfigurationSupplierOperatorDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No entity with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public ConfigurationSupplierOperatorSearchResponse searchAll(ConfigurationSupplierOperatorSearchRequest bankSearchRequest) {
       PageDTO<ConfigurationSupplierOperatorDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ConfigurationSupplierOperatorSearchResponse bankSearchRespose = new ConfigurationSupplierOperatorSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ConfigurationSupplierOperatorResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ConfigurationSupplierOperatorSearchResponse searchFilterAll(ConfigurationSupplierOperatorSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ConfigurationSupplierOperatorDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ConfigurationSupplierOperatorSearchResponse bankSearchRespose = new ConfigurationSupplierOperatorSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ConfigurationSupplierOperatorResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }
}
