package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DocumentTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.DocumentTypeController.DOCUMENT_TYPE;

@Slf4j
@Tag(name = "Maintenance - Document Type", description = "Maintenance Module")
@RestController
@RequestMapping(DOCUMENT_TYPE)
public class DocumentTypeController {

    public static final String DOCUMENT_TYPE = "/document-types";

    public static final String DOCUMENT_TYPE_ID = "document_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DOCUMENT_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DOCUMENT_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + DOCUMENT_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private DocumentTypeUseCase documentTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create document type.",
            description = "Create document type."
    )
    public ApiResponse create(
            @RequestBody DocumentTypeCreateRequest request) {
        return ApiResponse.success(
                documentTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find document type by id.",
            description = "Find document type by id."
    )
    public ApiResponse findBy(
            @PathVariable(DOCUMENT_TYPE_ID) String id) {
        return ApiResponse.success(
                documentTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all document types.",
            description = "Find all document types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                documentTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit document type.",
            description = "Edit document type."
    )
    public ApiResponse edit(
            @PathVariable(DOCUMENT_TYPE_ID) String id,
            @RequestBody DocumentTypeUpdateRequest request) {
        return ApiResponse.success(
                documentTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete document type.",
            description = "Delete document type."
    )
    public ApiResponse deleteById(
           @PathVariable(DOCUMENT_TYPE_ID) String id) {
        documentTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate document types.",
            description = "Search all document types."
    )
    public ApiResponse searchPaginate(
            @RequestBody DocumentTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                documentTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate document types.",
            description = "Search filter paginate document types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DocumentTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                documentTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
