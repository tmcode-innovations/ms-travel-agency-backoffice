package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;

import dev.mm.javaee.core.exceptions.BadRequestException;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplier;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SupplierUseCaseImpl implements SupplierUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private SupplierRepo repo;

    @Autowired
    private SupplierConverter converter;

    @Autowired
    private SetFieldDefaultSupplier setFieldDefault;

    @Override
    @Transactional
    public SupplierResponse create(SupplierCreateRequest request) {
        if (repo.existsByNameGlobal(request.getName(), null)) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        SupplierDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SupplierDomain createdDomain = repo.create(newDomain);
        return SupplierResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SupplierResponse edit(String id, SupplierUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SupplierDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        if (repo.existsByNameGlobal(request.getName(), old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        SupplierDomain supplierDomain = request.to();
        SupplierDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(supplierDomain, old));
        return SupplierResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        SupplierResponse old = findById(id);
        if (referenceGuard.isReferenced("Supplier", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findAll() {
        return repo.findAll(false).stream().map(supplierDomain -> SupplierResponse.from(supplierDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SupplierDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        return SupplierResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest) {
       PageDTO<SupplierDomain> pageDTO = repo.searchAll(supplierSearchRequest);
       SupplierSearchResponse supplierSearchRespose = new SupplierSearchResponse();
       supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierResponse.from(supplierDomain)).toList());
       supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
       supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest) {
        PageDTO<SupplierDomain> pageDTO = repo.searchAllFilter(supplierSearchFilterRequest);
        SupplierSearchResponse supplierSearchRespose = new SupplierSearchResponse();
        supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierResponse.from(supplierDomain)).toList());
        supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
        supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findAllSimpleForServiceOperation() {
        return repo.findAllSimpleForServiceOperation().stream().map(supplierDomain -> SupplierResponse.from(supplierDomain)).toList();
    }
}
