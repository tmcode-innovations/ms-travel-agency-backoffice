package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanyCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanyUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core.CompanyController.COMPANY;

@Slf4j
@Tag(name = "Core - Company", description = "Core Module")
@RestController
@RequestMapping(COMPANY)
public class CompanyController {

    public static final String COMPANY = "/companies";

    public static final String COMPANY_ID = "company_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + COMPANY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + COMPANY_ID + "}";
    public static final String DELETE = "/delete/{" + COMPANY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private CompanyUseCase companyUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create company.",
            description = "Create company."
    )
    public ApiResponse create(
            @RequestBody CompanyCreateRequest request) {
        return ApiResponse.success(
                companyUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find company by id.",
            description = "Find company by id."
    )
    public ApiResponse findBy(
            @PathVariable(COMPANY_ID) String id) {
        return ApiResponse.success(
                companyUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all companies.",
            description = "Find all companies."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                companyUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit company.",
            description = "Edit company."
    )
    public ApiResponse edit(
            @PathVariable(COMPANY_ID) String id,
            @RequestBody CompanyUpdateRequest request) {
        return ApiResponse.success(
                companyUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete company.",
            description = "Delete company."
    )
    public ApiResponse deleteById(
           @PathVariable(COMPANY_ID) String id) {
        companyUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate companies.",
            description = "Search all companies."
    )
    public ApiResponse searchPaginate(
            @RequestBody CompanySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                companyUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate companies.",
            description = "Search filter paginate companies."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CompanySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                companyUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
