package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.changetype.*;
import com.saas.tourismagency.admin.backoffice.usecase.account.ChangeTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ChangeTypeController.CHANGE_TYPE;

@Slf4j
@Tag(name = "Finance - Change Type", description = "Finance Module")
@RestController
@RequestMapping(CHANGE_TYPE)
public class ChangeTypeController {

    public static final String CHANGE_TYPE = "/change-types";

    public static final String CHANGE_TYPE_ID = "change_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CHANGE_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CHANGE_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + CHANGE_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_BY_DATE = "/find-by-date";

    @Autowired
    private ChangeTypeUseCase changeTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create change type.",
            description = "Create change type."
    )
    public ApiResponse create(
            @RequestBody ChangeTypeCreateRequest request) {
        return ApiResponse.success(
                changeTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find change type by id.",
            description = "Find change type by id."
    )
    public ApiResponse findBy(
            @PathVariable(CHANGE_TYPE_ID) String id) {
        return ApiResponse.success(
                changeTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all change types.",
            description = "Find all change types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                changeTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit change type.",
            description = "Edit change type."
    )
    public ApiResponse edit(
            @PathVariable(CHANGE_TYPE_ID) String id,
            @RequestBody ChangeTypeUpdateRequest request) {
        return ApiResponse.success(
                changeTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete change type.",
            description = "Delete change type."
    )
    public ApiResponse deleteById(
           @PathVariable(CHANGE_TYPE_ID) String id) {
        changeTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate change types.",
            description = "Search all change types."
    )
    public ApiResponse searchPaginate(
            @RequestBody ChangeTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                changeTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate change types.",
            description = "Search filter paginate change types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ChangeTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                changeTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(FIND_BY_DATE)
    @Operation(
            summary = "Find change type by date.",
            description = "Find change type by date."
    )
    public ApiResponse findByDate(
            @RequestBody ChangeTypeFindByDateRequest request) {
        return ApiResponse.success(
                changeTypeUseCase.findByDate(request)
        );
    }
}
