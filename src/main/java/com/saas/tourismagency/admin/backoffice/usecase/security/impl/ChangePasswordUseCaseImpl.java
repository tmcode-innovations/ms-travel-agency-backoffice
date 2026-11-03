package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.changepassword.ChangePasswordRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.password.PasswordService;
import com.saas.tourismagency.admin.backoffice.core.utils.JwtUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.*;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.ForbiddenException;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.utils.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserPasswordUseCase userPasswordUseCase;

    @Autowired
    private UserPasswordHistoryUseCase userPasswordHistoryUseCase;

    @Autowired
    private PasswordService pepperAdminPassService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        ForbiddenException ex = new ForbiddenException("El email o contraseña son incorrectos");
        UserDomain userDomain = userRepo.findByUserNameOrEmail(changePasswordRequest.getUsermail());
        if (userDomain == null) {
            throw ex;
        }

        // ValidationService.validateAndThrow(changePasswordRequest);

        UserPasswordDomain newDomain = _convertPass(changePasswordRequest.getPlainPassword(), userDomain);

        UserPasswordHistoryDomain historical = userPasswordHistoryUseCase.findByPasswordHash(
                newDomain.getPasswordHash(),
                userDomain
        );

        if (historical != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_PASSWORD_ALREADY_USED);
        }

        UserPasswordDomain userPasswordEdit = userPasswordUseCase.edit(changePasswordRequest.getPlainPassword(), userDomain);

        userPasswordHistoryUseCase.create(
                        userPasswordEdit,
                        userDomain
                );
    }

    private UserPasswordDomain _convertPass(String plainPassword, UserDomain user) {
        String salt = UUID.randomUUID().toString();
        String passHash = passwordService.hash(plainPassword, salt);
        return new UserPasswordDomain(
                salt,
                passHash,
                user
        );
    }

}
