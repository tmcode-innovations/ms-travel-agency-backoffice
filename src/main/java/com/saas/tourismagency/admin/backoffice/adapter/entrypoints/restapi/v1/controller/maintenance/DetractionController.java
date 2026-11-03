package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.detraction.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.BankUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.DetractionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.DetractionController.DETRACTION;

@Slf4j
@Tag(name = "Maintenance - Detraction", description = "Maintenance Module")
@RestController
@RequestMapping(DETRACTION)
public class DetractionController {

    public static final String DETRACTION = "/detractions";

    public static final String DETRACTION_ID = "detraction_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DETRACTION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DETRACTION_ID + "}";
    public static final String DELETE = "/delete/{" + DETRACTION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_AVAILABLE = "/change-available/{" + DETRACTION_ID + "}";

    @Autowired
    private DetractionUseCase detractionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create detraction.",
            description = "Create detraction."
    )
    public ApiResponse create(
            @RequestBody DetractionCreateRequest request) {
        return ApiResponse.success(
                detractionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find detraction by id.",
            description = "Find detraction by id."
    )
    public ApiResponse findBy(
            @PathVariable(DETRACTION_ID) String id) {
        return ApiResponse.success(
                detractionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all detractions.",
            description = "Find all detractions."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                detractionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit detraction.",
            description = "Edit detraction."
    )
    public ApiResponse edit(
            @PathVariable(DETRACTION_ID) String id,
            @RequestBody DetractionUpdateRequest request) {
        return ApiResponse.success(
                detractionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete bank.",
            description = "Delete bank."
    )
    public ApiResponse deleteById(
           @PathVariable(DETRACTION_ID) String id) {
        detractionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate banks.",
            description = "Search all banks."
    )
    public ApiResponse searchPaginate(
            @RequestBody DetractionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                detractionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate banks.",
            description = "Search filter paginate banks."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DetractionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                detractionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_AVAILABLE)
    @Operation(
            summary = "Change available detraction.",
            description = "Change available detraction."
    )
    public ApiResponse changeAvailable(
            @PathVariable(DETRACTION_ID) String id,
            @RequestBody DetractionChangeAvailableRequest request) {
        return ApiResponse.success(
                detractionUseCase.changeAvailable(id, request.getAvailable())
        );
    }

}
