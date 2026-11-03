package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.EntryCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.EntrySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.EntrySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.entry.EntryUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.EntryUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.EntryController.ENTRY;

@Slf4j
@Tag(name = "Maintenance - Entry (Rubro)", description = "Maintenance Module")
@RestController
@RequestMapping(ENTRY)
public class EntryController {

    public static final String ENTRY = "/entries";

    public static final String ENTRY_ID = "entry_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + ENTRY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + ENTRY_ID + "}";
    public static final String DELETE = "/delete/{" + ENTRY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";
    public static final String FIND_ALL_SIMPLE_DOCUMENT_FINANCE = "/find-all-simple-document-finance";


    @Autowired
    private EntryUseCase entryUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create entry.",
            description = "Create entry."
    )
    public ApiResponse create(
            @RequestBody EntryCreateRequest request) {
        return ApiResponse.success(
                entryUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find entry by id.",
            description = "Find entry by id."
    )
    public ApiResponse findBy(
            @PathVariable(ENTRY_ID) String id) {
        return ApiResponse.success(
                entryUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all entries.",
            description = "Find all entries."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                entryUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit entry.",
            description = "Edit entry."
    )
    public ApiResponse edit(
            @PathVariable(ENTRY_ID) String id,
            @RequestBody EntryUpdateRequest request) {
        return ApiResponse.success(
                entryUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete entry.",
            description = "Delete entry."
    )
    public ApiResponse deleteById(
           @PathVariable(ENTRY_ID) String id) {
        entryUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate entries.",
            description = "Search all entries."
    )
    public ApiResponse searchPaginate(
            @RequestBody EntrySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                entryUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate entries.",
            description = "Search filter paginate entries."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody EntrySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                entryUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all entry.",
            description = "Find all entry."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                entryUseCase.findAllSimple()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_DOCUMENT_FINANCE)
    @Operation(
            summary = "Find all entry for document finance.",
            description = "Find all entry for document finance."
    )
    public ApiResponse findAllSimpleApertureDocumentFinance(
    ) {
        return ApiResponse.success(
                entryUseCase.findAllSimpleDocumentFinance()
        );
    }
}
