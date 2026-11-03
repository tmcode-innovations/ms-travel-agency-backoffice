package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.external;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.external.TenantCreateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.external.TenantUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.external.TenantController.TENANT;

@Slf4j
@Tag(name = "External - Tenant", description = "External Module")
@RestController
@RequestMapping(TENANT)
public class TenantController {

    public static final String TENANT = "/tenants";

    public static final String CREATE = "/create";

    @Autowired
    private TenantUseCase tenantUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create tenant.",
            description = "Create tenant."
    )
    public ApiResponse create(
            @RequestBody TenantCreateRequest request) {
        return ApiResponse.success(
                tenantUseCase.create(request)
        );
    }
}
