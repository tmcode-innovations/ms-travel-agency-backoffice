package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinance.DocumentFinanceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.DocumentFinanceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.DocumentFinanceController.DOCUMENT_FINANCE;

@Slf4j
@Tag(name = "Finance - Document Finance", description = "Finance Module")
@RestController
@RequestMapping(DOCUMENT_FINANCE)
public class DocumentFinanceController {

    public static final String DOCUMENT_FINANCE = "/document-finances";

    public static final String DOCUMENT_FINANCE_ID = "document_finance_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DOCUMENT_FINANCE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DOCUMENT_FINANCE_ID + "}";
    public static final String DELETE = "/delete/{" + DOCUMENT_FINANCE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private DocumentFinanceUseCase documentFinanceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create document finance.",
            description = "Create document finance."
    )
    public ApiResponse create(
            @RequestBody DocumentFinanceCreateRequest request) {
        return ApiResponse.success(
                documentFinanceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find document finance by id.",
            description = "Find document finance by id."
    )
    public ApiResponse findBy(
            @PathVariable(DOCUMENT_FINANCE_ID) String id) {
        return ApiResponse.success(
                documentFinanceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all document finances.",
            description = "Find all document finances."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                documentFinanceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit document finance.",
            description = "Edit document finance."
    )
    public ApiResponse edit(
            @PathVariable(DOCUMENT_FINANCE_ID) String id,
            @RequestBody DocumentFinanceUpdateRequest request) {
        return ApiResponse.success(
                documentFinanceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete document finance.",
            description = "Delete document finance."
    )
    public ApiResponse deleteById(
           @PathVariable(DOCUMENT_FINANCE_ID) String id) {
        documentFinanceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate document finances.",
            description = "Search all document finances."
    )
    public ApiResponse searchPaginate(
            @RequestBody DocumentFinanceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                documentFinanceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate document finances.",
            description = "Search filter paginate document finances."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DocumentFinanceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                documentFinanceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
