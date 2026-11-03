package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.usecase.security.ObjectUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Object Controller", description = "Object Security Controller")
@RestController
@RequestMapping(ObjectController.OBJECT)
public class ObjectController {

    public static final String OBJECT = "security/objects";

    public static final String FIND_ALL = "/find-all";

    @Autowired
    private ObjectUseCase objectUseCase;

    @GetMapping(FIND_ALL)
    @Operation(summary = "Find all objects.", description = "Find all objects.")
    public ApiResponse findAll() {
        return ApiResponse.success(objectUseCase.findAll());
    }
}
