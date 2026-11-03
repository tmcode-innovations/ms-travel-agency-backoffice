package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm;

import com.saas.tourismagency.admin.backoffice.usecase.crm.CrmNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm.CrmNomenclatureController.CRM_NOMENCLATURE;

@Slf4j
@Tag(name = "Program and Quotation - Nomenclature", description = "Program and Quotation Module")
@RestController
@RequestMapping(CRM_NOMENCLATURE)
public class CrmNomenclatureController {

    public static final String CRM_NOMENCLATURE = "/crm-nomenclatures";

    public static final String SEARCH_STATE_PROGRAM = "/search-state-programs";
    public static final String SEARCH_STATE_QUOTATION = "/search-state-quotations";

    @Autowired
    private CrmNomenclatureUseCase crmNomenclatureUseCase;

    @PostMapping(SEARCH_STATE_PROGRAM)
    @Operation(
            summary = "Search state programs.",
            description = "Search all state programs."
    )
    public ApiResponse searchAllStateProgram() {
        return ApiResponse.success(
                crmNomenclatureUseCase.searchAllStateProgram()
        );
    }

    @PostMapping(SEARCH_STATE_QUOTATION)
    @Operation(
            summary = "Search state quotations.",
            description = "Search all state quotations."
    )
    public ApiResponse searchAllStateQuotation() {
        return ApiResponse.success(
                crmNomenclatureUseCase.searchAllStateQuotation()
        );
    }
}
