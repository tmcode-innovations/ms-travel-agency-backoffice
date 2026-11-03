package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ProformaController.PROFORMA;

@Slf4j
@Tag(name = "Finance - Proforma", description = "Finance Module")
@RestController
@RequestMapping(PROFORMA)
public class ProformaController {

    public static final String PROFORMA = "/proformas";

    public static final String PROFORMA_ID = "proforma_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROFORMA_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROFORMA_ID + "}";
    public static final String DELETE = "/delete/{" + PROFORMA_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private ProformaUseCase proformaUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create proforma.",
            description = "Create proforma."
    )
    public ApiResponse create(
            @RequestBody ProformaCreateRequest request) {
        return ApiResponse.success(
                proformaUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find proforma by id.",
            description = "Find proforma by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROFORMA_ID) String id) {
        return ApiResponse.success(
                proformaUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all proformas.",
            description = "Find all proformas."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                proformaUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit proforma.",
            description = "Edit proforma."
    )
    public ApiResponse edit(
            @PathVariable(PROFORMA_ID) String id,
            @RequestBody ProformaUpdateRequest request) {
        return ApiResponse.success(
                proformaUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete proforma.",
            description = "Delete proforma."
    )
    public ApiResponse deleteById(
           @PathVariable(PROFORMA_ID) String id) {
        proformaUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate proformas.",
            description = "Search all proformas."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProformaSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                proformaUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate proformas.",
            description = "Search filter paginate proformas."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProformaSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                proformaUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
