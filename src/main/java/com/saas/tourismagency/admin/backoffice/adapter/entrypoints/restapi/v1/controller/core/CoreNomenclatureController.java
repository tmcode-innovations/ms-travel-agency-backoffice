package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core;

import com.saas.tourismagency.admin.backoffice.usecase.core.CoreNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core.CoreNomenclatureController.CORE_NOMENCLATURE;

@Slf4j
@Tag(name = "Core - Nomenclature", description = "Core Module")
@RestController
@RequestMapping(CORE_NOMENCLATURE)
public class CoreNomenclatureController {

    public static final String CORE_NOMENCLATURE = "/core-nomenclatures";

    public static final String SEARCH_GENERAL_CONFIGURATION = "/search-general-configurations";
    public static final String SEARCH_QUOTATION_TYPE = "/search-quotation-types";
    public static final String SEARCH_REGISTER_GROUP_TYPE = "/search-register-group-types";

    @Autowired
    private CoreNomenclatureUseCase coreNomenclatureUseCase;

    @PostMapping(SEARCH_GENERAL_CONFIGURATION)
    @Operation(
            summary = "Search general configurations.",
            description = "Search all general configurations."
    )
    public ApiResponse searchAllGeneralConfiguration() {
        return ApiResponse.success(
                coreNomenclatureUseCase.searchAllGeneralConfiguration()
        );
    }

    @PostMapping(SEARCH_QUOTATION_TYPE)
    @Operation(
            summary = "Search quotation type.",
            description = "Search all quotation type."
    )
    public ApiResponse searchAllQuotationType() {
        return ApiResponse.success(
                coreNomenclatureUseCase.searchAllQuotationType()
        );
    }

    @PostMapping(SEARCH_REGISTER_GROUP_TYPE)
    @Operation(
            summary = "Search register group type.",
            description = "Search all register group type."
    )
    public ApiResponse searchAllRegisterGroupType() {
        return ApiResponse.success(
                coreNomenclatureUseCase.searchAllRegisterGroupType()
        );
    }

}
