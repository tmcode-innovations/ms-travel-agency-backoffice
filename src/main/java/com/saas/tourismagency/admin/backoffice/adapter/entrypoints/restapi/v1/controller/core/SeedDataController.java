package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core;

import com.saas.tourismagency.admin.backoffice.usecase.core.SeedDataUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core.SeedDataController.SEED_DATA;

@Slf4j
@Tag(name = "Core - Seed Data", description = "Core Module")
@RestController
@RequestMapping(SEED_DATA)
public class SeedDataController {

    public static final String SEED_DATA = "/seed-data";

    public static final String INIT = "/init";
    public static final String INIT_BASE = "/init-base";

    @Autowired
    private SeedDataUseCase seedDataUseCase;

    @GetMapping(INIT)
    @Operation(
            summary = "Init data.",
            description = "Init data."
    )
    public ApiResponse seedDataInit() {
        seedDataUseCase.seedData();
        return ApiResponse.success();
    }

    @GetMapping(INIT_BASE)
    @Operation(
            summary = "Init data base.",
            description = "Init data base."
    )
    public ApiResponse seedDataInitBase() {
        seedDataUseCase.seedDataBase();
        return ApiResponse.success();
    }

}
