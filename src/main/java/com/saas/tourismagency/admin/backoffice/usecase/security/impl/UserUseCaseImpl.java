package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultUser;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.app.AppRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.profile.ProfileRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.security.AppDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ProfileDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserPasswordUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.utils.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UserUseCaseImpl implements UserUseCase {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private AppRepo appRepo;

    @Autowired
    private UserConverter converter;

    @Autowired
    private SetFieldDefaultUser setFieldDefault;

    @Autowired
    private UserPasswordUseCase userPasswordUseCase;

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest request) {
        UserDomain userDomainExist = repo.findByEmailAndTenant(request.getEmail());
        if (userDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_EMAIL);
        }

        /*if (request.getUserPassword() == null) {
            throw new ValidationException(
                    List.of(
                            new ValidationErrorMessage(
                                    "user_password",
                                    null,
                                    "When a user is being created, the password is required."
                            )
                    )
            );
        }
        ValidationService.validateAndThrow(request.getUserPassword());*/

        UserDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        UserDomain createdDomain = repo.create(newDomain);
        userPasswordUseCase.create(request.getUserPassword().getPassword(), createdDomain);
        return UserResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public UserResponse edit(String id, UserUpdateRequest request) {
        if (id == null) {
            return null;
        }
        UserDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No user found with id '%s'.".formatted(id));
        }
        UserDomain userDomainExist = repo.findByEmailAndTenant(request.getEmail());
        if (userDomainExist != null && !userDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_EMAIL);
        }
        UserDomain userDomain = request.to();
        if (request.getProfiles() != null && !request.getProfiles().isEmpty()){
            userDomain.setProfiles(new HashSet<>());
            for (String profile: request.getProfiles()){
                ProfileDomain profileDomain = profileRepo.findById(profile);
                userDomain.getProfiles().add(profileDomain);
            }
        }
        if (request.getApps() != null && !request.getApps().isEmpty()){
            userDomain.setApps(new HashSet<>());
            for (String app: request.getApps()){
                AppDomain appDomain = appRepo.findById(app);
                userDomain.getApps().add(appDomain);
            }
        }
        UserDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(userDomain, old));
        if (request.getUserPassword() != null) {
            userPasswordUseCase.edit(request.getUserPassword().getPassword(), editedDomain);
        }
        return UserResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        UserResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return repo.findAll(false).stream().map(userDomain -> UserResponse.from(userDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(String id) {
        if (id == null) {
            return null;
        }
        UserDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No user found with id '%s'.".formatted(id));
        }
        return UserResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public UserSearchResponse searchAll(UserSearchRequest userSearchRequest) {
       PageDTO<UserDomain> pageDTO = repo.searchAll(userSearchRequest);
       UserSearchResponse userSearchResponse = new UserSearchResponse();
       userSearchResponse.setUsers(pageDTO.getContent().stream().map(userDomain ->  UserResponse.from(userDomain)).toList());
       userSearchResponse.setTotalElements(pageDTO.getTotalElements());
       userSearchResponse.setTotalPages(pageDTO.getTotalPages());
       return userSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public UserSearchResponse searchFilterAll(UserSearchFilterRequest userSearchFilterRequest) {
        PageDTO<UserDomain> pageDTO = repo.searchAllFilter(userSearchFilterRequest);
        UserSearchResponse userSearchResponse = new UserSearchResponse();
        userSearchResponse.setUsers(pageDTO.getContent().stream().map(userDomain ->  UserResponse.from(userDomain)).toList());
        userSearchResponse.setTotalElements(pageDTO.getTotalElements());
        userSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return userSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findByUserName(String userName) {
        return UserResponse.from(repo.findByUserName(userName));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        return UserResponse.from(repo.findByEmail(email));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findByUserNameOrEmail(String usermail) {
        return UserResponse.from(repo.findByEmail(usermail));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }
}
