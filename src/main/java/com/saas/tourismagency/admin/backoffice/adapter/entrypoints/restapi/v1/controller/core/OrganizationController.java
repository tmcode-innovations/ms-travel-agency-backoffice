package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core;

import com.saas.tourismagency.admin.backoffice.usecase.core.OrganizationUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core.OrganizationController.ORGANIZATION;

@Slf4j
@Tag(name = "Core - Organization", description = "Core Module")
@RestController
@RequestMapping(ORGANIZATION)
public class OrganizationController {

    public static final String ORGANIZATION = "/organizations";

    public static final String ORGANIZATION_ID = "organization_id";

    public static final String FIND_BY = "/find-by/{" + ORGANIZATION_ID + "}";
    public static final String FIND_ALL = "/find-all";

    @Autowired
    private OrganizationUseCase organizationUseCase;

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find organization by id.",
            description = "Find organization by id."
    )
    public ApiResponse findBy(
            @PathVariable(ORGANIZATION_ID) String id) {
        return ApiResponse.success(
                organizationUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all organizations.",
            description = "Find all organizations."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                organizationUseCase.findAll()
        );
    }

}
