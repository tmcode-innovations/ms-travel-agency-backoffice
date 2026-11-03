package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.password.PasswordService;
import com.saas.tourismagency.admin.backoffice.core.utils.JwtUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.AuthenticationUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserPasswordUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserUseCase;
import dev.mm.javaee.core.exceptions.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserPasswordUseCase userPasswordUseCase;

    @Autowired
    private PasswordService pepperAdminPassService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public LoginResponse resetToken() {
        UserDomain user = UserResolver.resolveUser();
        return jwtUtil.generateToken(user);
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        ForbiddenException ex = new ForbiddenException("El email o contraseña son incorrectos o el usuario no existe en el sistema");
        UserDomain userDomain = userRepo.findByUserNameOrEmail(loginRequest.getUsermail());
        if (userDomain == null) {
            throw ex;
        }

        UserPasswordDomain pass = userPasswordUseCase.findByUser(userDomain);
        if (pepperAdminPassService.matches(loginRequest.getPlainPassword(), pass.getSalt(), pass.getPasswordHash())) {
            return jwtUtil.generateToken(userDomain);
        }
        throw ex;
    }

}
