package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginResponse;

public interface AuthenticationUseCase {

    public LoginResponse login(LoginRequest loginRequest);

    public LoginResponse resetToken();

}
