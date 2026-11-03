package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.PositionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.PositionSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.PositionSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.PositionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.hr.PositionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr.PositionController.POSITION;

@Slf4j
@Tag(name = "Human Resources - Position (Cargo)", description = "Human Resources Module")
@RestController
@RequestMapping(POSITION)
public class PositionController {

    public static final String POSITION = "/positions";

    public static final String POSITION_ID = "position_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + POSITION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + POSITION_ID + "}";
    public static final String DELETE = "/delete/{" + POSITION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PositionUseCase positionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create position.",
            description = "Create position."
    )
    public ApiResponse create(
            @RequestBody PositionCreateRequest request) {
        return ApiResponse.success(
                positionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find position by id.",
            description = "Find position by id."
    )
    public ApiResponse findBy(
            @PathVariable(POSITION_ID) String id) {
        return ApiResponse.success(
                positionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all positions.",
            description = "Find all positions."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                positionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit position.",
            description = "Edit position."
    )
    public ApiResponse edit(
            @PathVariable(POSITION_ID) String id,
            @RequestBody PositionUpdateRequest request) {
        return ApiResponse.success(
                positionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete position.",
            description = "Delete position."
    )
    public ApiResponse deleteById(
           @PathVariable(POSITION_ID) String id) {
        positionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate positions.",
            description = "Search all positions."
    )
    public ApiResponse searchPaginate(
            @RequestBody PositionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                positionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate positions.",
            description = "Search filter paginate positions."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PositionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                positionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
