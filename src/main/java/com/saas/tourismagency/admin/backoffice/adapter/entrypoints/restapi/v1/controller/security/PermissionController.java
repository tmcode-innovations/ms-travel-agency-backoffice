package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.permission.PermissionCreateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.security.PermissionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Permission Controller", description = "Permission Controller")
@RestController
@RequestMapping(PermissionController.PERMISSION)
public class PermissionController {

    public static final String PERMISSION = "security/permissions";

    public static final String FIND_ALL = "/find-all";

    public static final String CREATE = "/create";

    @Autowired
    private PermissionUseCase permissionUseCase;

    @GetMapping(FIND_ALL)
    @Operation(summary = "Find all permissions.", description = "Find all permissions.")
    public ApiResponse findAll() {
        return ApiResponse.success(permissionUseCase.findAll());
    }

    @PostMapping(CREATE)
    @Operation(summary = "Create permission.", description = "Create permission.")
    public ApiResponse create(@RequestBody PermissionCreateRequest request) {
        return ApiResponse.success(permissionUseCase.create(request));
    }
}
