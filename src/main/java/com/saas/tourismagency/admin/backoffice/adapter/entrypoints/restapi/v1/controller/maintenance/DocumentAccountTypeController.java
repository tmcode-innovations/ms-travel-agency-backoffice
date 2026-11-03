package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documentaccounttype.DocAccountTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DocumentAccountTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.DocumentAccountTypeController.DOCUMENT_ACCOUNT_TYPE;

@Slf4j
@Tag(name = "Maintenance - Document Account Type", description = "Maintenance Module")
@RestController
@RequestMapping(DOCUMENT_ACCOUNT_TYPE)
public class DocumentAccountTypeController {

    public static final String DOCUMENT_ACCOUNT_TYPE = "/document-account-types";

    public static final String DOCUMENT_ACCOUNT_TYPE_ID = "document_account_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DOCUMENT_ACCOUNT_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DOCUMENT_ACCOUNT_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + DOCUMENT_ACCOUNT_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private DocumentAccountTypeUseCase documentAccountTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create document account type.",
            description = "Create document account type."
    )
    public ApiResponse create(
            @RequestBody DocAccountTypeCreateRequest request) {
        return ApiResponse.success(
                documentAccountTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find document account type by id.",
            description = "Find document account type by id."
    )
    public ApiResponse findBy(
            @PathVariable(DOCUMENT_ACCOUNT_TYPE_ID) String id) {
        return ApiResponse.success(
                documentAccountTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all document account types.",
            description = "Find all document account types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                documentAccountTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit document account type.",
            description = "Edit document account type."
    )
    public ApiResponse edit(
            @PathVariable(DOCUMENT_ACCOUNT_TYPE_ID) String id,
            @RequestBody DocAccountTypeUpdateRequest request) {
        return ApiResponse.success(
                documentAccountTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete document account type.",
            description = "Delete document account type."
    )
    public ApiResponse deleteById(
           @PathVariable(DOCUMENT_ACCOUNT_TYPE_ID) String id) {
        documentAccountTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate document account types.",
            description = "Search all document account types."
    )
    public ApiResponse searchPaginate(
            @RequestBody DocAccountTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                documentAccountTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate document account types.",
            description = "Search filter paginate document account types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DocAccountTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                documentAccountTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
