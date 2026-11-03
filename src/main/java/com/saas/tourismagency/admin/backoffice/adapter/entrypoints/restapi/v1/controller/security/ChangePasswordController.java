package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.changepassword.ChangePasswordRequest;
import com.saas.tourismagency.admin.backoffice.usecase.security.ChangePasswordUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Change Password Controller", description = "Change Password Controller")
@RestController
@RequestMapping(ChangePasswordController.CHANGE_PASSWORD)
public class ChangePasswordController {

    public static final String CHANGE_PASSWORD = "/security/change-password";

    public static final String CHANGE = "/change";

    @Autowired
    private ChangePasswordUseCase changePasswordUseCase;

    @PostMapping(CHANGE)
    @Operation(
            summary = "change user password.",
            description = "change user password."
    )
    public ApiResponse change(
            @RequestBody ChangePasswordRequest changePasswordRequest) {
        changePasswordUseCase.changePassword(changePasswordRequest);
        return ApiResponse.success();
    }

}
