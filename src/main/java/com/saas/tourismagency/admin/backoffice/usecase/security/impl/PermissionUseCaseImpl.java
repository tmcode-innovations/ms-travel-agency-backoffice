package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.permission.PermissionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultPermission;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.PermissionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.PermissionUseCase;
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
public class PermissionUseCaseImpl implements PermissionUseCase {

    @Autowired
    private PermissionRepo repo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private PermissionConverter converter;

    @Autowired
    private SetFieldDefaultPermission setFieldDefault;

    @Override
    @Transactional
    public PermissionResponse create(PermissionCreateRequest request) {
        ObjectDomain objectDomain = null;
        if (request.getEntity() != null && !request.getEntity().isEmpty()){
            IdBaseDomain objectIdBaseDomain = objectRepo.findByIdEntity(request.getEntity());
            objectDomain = new ObjectDomain();
            objectDomain.setId(objectIdBaseDomain.getId());
        }

        PermissionDomain newDomain = request.to(objectDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PermissionDomain createdDomain = repo.create(newDomain);
        return PermissionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PermissionResponse edit(String id, PermissionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PermissionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No permission found with id '%s'.".formatted(id));
        }

        ObjectDomain objectDomain = null;
        if (request.getEntity() != null && !request.getEntity().isEmpty()){
            IdBaseDomain objectIdBaseDomain = objectRepo.findByIdEntity(request.getEntity());
            objectDomain = new ObjectDomain();
            objectDomain.setId(objectIdBaseDomain.getId());
        }

        PermissionDomain permissionDomain = request.to(objectDomain);
        PermissionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(permissionDomain, old));
        return PermissionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PermissionResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAll() {
        return repo.findAll(false).stream().map(permissionDomain -> PermissionResponse.from(permissionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PermissionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No permission found with id '%s'.".formatted(id));
        }
        return PermissionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionSearchResponse searchAll(PermissionSearchRequest permissionSearchRequest) {
       PageDTO<PermissionDomain> pageDTO = repo.searchAll(permissionSearchRequest);
       PermissionSearchResponse permissionSearchRespose = new PermissionSearchResponse();
       permissionSearchRespose.setPermissions(pageDTO.getContent().stream().map(permissionDomain ->  PermissionResponse.from(permissionDomain)).toList());
       permissionSearchRespose.setTotalElements(pageDTO.getTotalElements());
       permissionSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return permissionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionSearchResponse searchFilterAll(PermissionSearchFilterRequest permissionSearchFilterRequest) {
        PageDTO<PermissionDomain> pageDTO = repo.searchAllFilter(permissionSearchFilterRequest);
        PermissionSearchResponse permissionSearchRespose = new PermissionSearchResponse();
        permissionSearchRespose.setPermissions(pageDTO.getContent().stream().map(permissionDomain ->  PermissionResponse.from(permissionDomain)).toList());
        permissionSearchRespose.setTotalElements(pageDTO.getTotalElements());
        permissionSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return permissionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        PermissionDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No permission found with name '%s'.".formatted(name));
        }
        return PermissionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        PermissionDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No permission found with identify '%s'.".formatted(identify));
        }
        return PermissionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
