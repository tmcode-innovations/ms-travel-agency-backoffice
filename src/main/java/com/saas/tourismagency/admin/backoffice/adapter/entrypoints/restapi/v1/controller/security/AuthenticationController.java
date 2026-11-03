package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.authentications.login.LoginResponse;
import com.saas.tourismagency.admin.backoffice.usecase.security.AuthenticationUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Authentication Controller", description = "Authentication Controller")
@RestController
@RequestMapping(AuthenticationController.AUTHENTICATION)
public class AuthenticationController {

    public static final String AUTHENTICATION = "/security/authentications";

    public static final String LOGIN = "/login";
    public static final String RESET_TOKEN = "/reset_token";

    @Autowired
    private AuthenticationUseCase authenticationUseCase;

    @PostMapping(LOGIN)
    @Operation(
            summary = "login user.",
            description = "login user."
    )
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {
        return ApiResponse.success(
                authenticationUseCase.login(loginRequest)
        );
    }

    @PostMapping(RESET_TOKEN)
    @Operation(
            summary = "Reset token.",
            description = "Reset token."
    )
    public ApiResponse<LoginResponse> resetToken() {
        return ApiResponse.success(
                authenticationUseCase.resetToken()
        );
    }

}
