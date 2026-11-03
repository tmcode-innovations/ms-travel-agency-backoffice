package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.profile.ProfileConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultProfile;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.permission.PermissionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.ProfileRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.security.PermissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.ProfileUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ProfileUseCaseImpl implements ProfileUseCase {

    @Autowired
    private ProfileRepo repo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private ProfileConverter converter;

    @Autowired
    private SetFieldDefaultProfile setFieldDefault;

    @Override
    @Transactional
    public ProfileResponse create(ProfileCreateRequest request) {
        ProfileDomain profileDomainExist = repo.findByName(request.getName());
        if (profileDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        Set<PermissionDomain> permissions = resolvePermissions(request.getPermissions());
        ProfileDomain newDomain = request.to(permissions);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProfileDomain createdDomain = repo.create(newDomain);
        return ProfileResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProfileResponse edit(String id, ProfileUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProfileDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No profile found with id '%s'.".formatted(id));
        }
        ProfileDomain profileDomainExist = repo.findByName(request.getName());
        if (profileDomainExist != null && !profileDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        Set<PermissionDomain> permissions = resolvePermissions(request.getPermissions());
        ProfileDomain profileDomain = request.to(permissions);
        ProfileDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(profileDomain, old));
        return ProfileResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProfileResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileResponse> findAll() {
        return repo.findAll(false).stream().map(profileDomain -> ProfileResponse.from(profileDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProfileDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No profile found with id '%s'.".formatted(id));
        }
        return ProfileResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileSearchResponse searchAll(ProfileSearchRequest profileSearchRequest) {
       PageDTO<ProfileDomain> pageDTO = repo.searchAll(profileSearchRequest);
       ProfileSearchResponse profileSearchRespose = new ProfileSearchResponse();
       profileSearchRespose.setProfiles(pageDTO.getContent().stream().map(profileDomain ->  ProfileResponse.from(profileDomain)).toList());
       profileSearchRespose.setTotalElements(pageDTO.getTotalElements());
       profileSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return profileSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileSearchResponse searchFilterAll(ProfileSearchFilterRequest profileSearchFilterRequest) {
        PageDTO<ProfileDomain> pageDTO = repo.searchAllFilter(profileSearchFilterRequest);
        ProfileSearchResponse profileSearchRespose = new ProfileSearchResponse();
        profileSearchRespose.setProfiles(pageDTO.getContent().stream().map(profileDomain ->  ProfileResponse.from(profileDomain)).toList());
        profileSearchRespose.setTotalElements(pageDTO.getTotalElements());
        profileSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return profileSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ProfileDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No profile found with name '%s'.".formatted(name));
        }
        return ProfileResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ProfileDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No profile found with identify '%s'.".formatted(identify));
        }
        return ProfileResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    private Set<PermissionDomain> resolvePermissions(Set<String> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return new HashSet<>();
        }
        Set<PermissionDomain> permissions = new HashSet<>();
        for (String permissionId : permissionIds) {
            PermissionDomain permission = permissionRepo.findById(permissionId);
            if (permission != null) {
                permissions.add(permission);
            }
        }
        return permissions;
    }
}
