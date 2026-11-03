package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.SectorUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SectorUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.SectorController.SECTOR;

@Slf4j
@Tag(name = "Maintenance - Sector", description = "Maintenance Module")
@RestController
@RequestMapping(SECTOR)
public class SectorController {

    public static final String SECTOR = "/sectors";

    public static final String SECTOR_ID = "sector_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SECTOR_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SECTOR_ID + "}";
    public static final String DELETE = "/delete/{" + SECTOR_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private SectorUseCase sectorUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create sector.",
            description = "Create sector."
    )
    public ApiResponse create(
            @RequestBody SectorCreateRequest request) {
        return ApiResponse.success(
                sectorUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find sector by id.",
            description = "Find sector by id."
    )
    public ApiResponse findBy(
            @PathVariable(SECTOR_ID) String id) {
        return ApiResponse.success(
                sectorUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all sectors.",
            description = "Find all sectors."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                sectorUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit sector.",
            description = "Edit sector."
    )
    public ApiResponse edit(
            @PathVariable(SECTOR_ID) String id,
            @RequestBody SectorUpdateRequest request) {
        return ApiResponse.success(
                sectorUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete sector.",
            description = "Delete sector."
    )
    public ApiResponse deleteById(
           @PathVariable(SECTOR_ID) String id) {
        sectorUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate sectors.",
            description = "Search all sectors."
    )
    public ApiResponse searchPaginate(
            @RequestBody SectorSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                sectorUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate sectors.",
            description = "Search filter paginate sectors."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody SectorSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                sectorUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
