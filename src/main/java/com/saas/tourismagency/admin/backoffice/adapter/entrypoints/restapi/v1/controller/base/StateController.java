package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.StateCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.StateSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.StateSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.StateUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.StateUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.StateController.STATE;

@Slf4j
@Tag(name = "Base - State", description = "Base Module")
@RestController
@RequestMapping(STATE)
public class StateController {

    public static final String STATE = "/states";

    public static final String STATE_ID = "state_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + STATE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + STATE_ID + "}";
    public static final String DELETE = "/delete/{" + STATE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_COUNTRY = "/search-by-country";

    @Autowired
    private StateUseCase stateUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create state.",
            description = "Create state."
    )
    public ApiResponse create(
            @RequestBody StateCreateRequest request) {
        return ApiResponse.success(
                stateUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find state by id.",
            description = "Find state by id."
    )
    public ApiResponse findBy(
            @PathVariable(STATE_ID) String id) {
        return ApiResponse.success(
                stateUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all states.",
            description = "Find all states."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                stateUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit state.",
            description = "Edit state."
    )
    public ApiResponse edit(
            @PathVariable(STATE_ID) String id,
            @RequestBody StateUpdateRequest request) {
        return ApiResponse.success(
                stateUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete state.",
            description = "Delete state."
    )
    public ApiResponse deleteById(
           @PathVariable(STATE_ID) String id) {
        stateUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate states.",
            description = "Search all states."
    )
    public ApiResponse searchPaginate(
            @RequestBody StateSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                stateUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate states.",
            description = "Search filter paginate states."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody StateSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                stateUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_BY_COUNTRY)
    @Operation(
            summary = "Search by country.",
            description = "Search by country."
    )
    public ApiResponse searchByCountry(
            @RequestBody BaseSearchRequestById searchRequestById
    ) {
        return ApiResponse.success(
                stateUseCase.searchAllByCountry(searchRequestById)
        );
    }
}
