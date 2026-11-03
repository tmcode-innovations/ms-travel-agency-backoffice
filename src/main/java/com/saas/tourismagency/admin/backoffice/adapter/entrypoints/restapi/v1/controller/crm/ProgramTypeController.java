package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.ProgramTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.ProgramTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.ProgramTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.ProgramTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm.ProgramTypeController.PROGRAM_TYPE;

@Slf4j
@Tag(name = "Program and Quotation - Program Type", description = "Program and Quotation Module")
@RestController
@RequestMapping(PROGRAM_TYPE)
public class ProgramTypeController {

    public static final String PROGRAM_TYPE = "/program-types";

    public static final String PROGRAM_TYPE_ID = "program_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROGRAM_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROGRAM_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + PROGRAM_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private ProgramTypeUseCase programTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create program type.",
            description = "Create program type."
    )
    public ApiResponse create(
            @RequestBody ProgramTypeCreateRequest request) {
        return ApiResponse.success(
                programTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find program type by id.",
            description = "Find program type by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROGRAM_TYPE_ID) String id) {
        return ApiResponse.success(
                programTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all program types.",
            description = "Find all program types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                programTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit program type.",
            description = "Edit program type."
    )
    public ApiResponse edit(
            @PathVariable(PROGRAM_TYPE_ID) String id,
            @RequestBody ProgramTypeUpdateRequest request) {
        return ApiResponse.success(
                programTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete program type.",
            description = "Delete program type."
    )
    public ApiResponse deleteById(
           @PathVariable(PROGRAM_TYPE_ID) String id) {
        programTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate program types.",
            description = "Search all program types."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProgramTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                programTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate program types.",
            description = "Search filter paginate program types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProgramTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                programTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
