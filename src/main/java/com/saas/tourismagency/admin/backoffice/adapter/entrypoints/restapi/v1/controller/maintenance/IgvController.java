package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.igv.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.IgvUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.IgvController.IGV;

@Slf4j
@Tag(name = "Maintenance - Igv", description = "Maintenance Module")
@RestController
@RequestMapping(IGV)
public class IgvController {

    public static final String IGV = "/igvs";

    public static final String IGV_ID = "igv_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + IGV_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + IGV_ID + "}";
    public static final String DELETE = "/delete/{" + IGV_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_AVAILABLE = "/change-available/{" + IGV_ID + "}";

    public static final String FIND_AVAILABLE = "/find-available";

    @Autowired
    private IgvUseCase igvUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create igv.",
            description = "Create igv."
    )
    public ApiResponse create(
            @RequestBody IgvCreateRequest request) {
        return ApiResponse.success(
                igvUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find igv by id.",
            description = "Find igv by id."
    )
    public ApiResponse findBy(
            @PathVariable(IGV_ID) String id) {
        return ApiResponse.success(
                igvUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all igvs.",
            description = "Find all igvs."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                igvUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit igv.",
            description = "Edit igv."
    )
    public ApiResponse edit(
            @PathVariable(IGV_ID) String id,
            @RequestBody IgvUpdateRequest request) {
        return ApiResponse.success(
                igvUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete igv.",
            description = "Delete igv."
    )
    public ApiResponse deleteById(
           @PathVariable(IGV_ID) String id) {
        igvUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate igvs.",
            description = "Search all igvs."
    )
    public ApiResponse searchPaginate(
            @RequestBody IgvSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                igvUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate igvs.",
            description = "Search filter paginate igvs."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody IgvSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                igvUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_AVAILABLE)
    @Operation(
            summary = "Change available igv.",
            description = "Change available igv."
    )
    public ApiResponse changeAvailable(
            @PathVariable(IGV_ID) String id,
            @RequestBody IgvChangeAvailableRequest request) {
        return ApiResponse.success(
                igvUseCase.changeAvailable(id, request.getAvailable())
        );
    }

    @GetMapping(FIND_AVAILABLE)
    @Operation(
            summary = "Find available igv.",
            description = "Find available igv."
    )
    public ApiResponse FindAvailable() {
        return ApiResponse.success(
                igvUseCase.findAvailable()
        );
    }
}
