package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.AreaUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.AreaController.AREA;

@Slf4j
@Tag(name = "Base - Area", description = "Base Module")
@RestController
@RequestMapping(AREA)
public class AreaController {

    public static final String AREA = "/areas";

    public static final String AREA_ID = "area_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + AREA_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + AREA_ID + "}";
    public static final String DELETE = "/delete/{" + AREA_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private AreaUseCase areaUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create area.",
            description = "Create area."
    )
    public ApiResponse create(
            @RequestBody AreaCreateRequest request) {
        return ApiResponse.success(
                areaUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find area by id.",
            description = "Find area by id."
    )
    public ApiResponse findBy(
            @PathVariable(AREA_ID) String id) {
        return ApiResponse.success(
                areaUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all areas.",
            description = "Find all areas."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                areaUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit area.",
            description = "Edit area."
    )
    public ApiResponse edit(
            @PathVariable(AREA_ID) String id,
            @RequestBody AreaUpdateRequest request) {
        return ApiResponse.success(
                areaUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete area.",
            description = "Delete area."
    )
    public ApiResponse deleteById(
           @PathVariable(AREA_ID) String id) {
        areaUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate areas.",
            description = "Search all areas."
    )
    public ApiResponse searchPaginate(
            @RequestBody AreaSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                areaUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate areas.",
            description = "Search filter paginate areas."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody AreaSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                areaUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
