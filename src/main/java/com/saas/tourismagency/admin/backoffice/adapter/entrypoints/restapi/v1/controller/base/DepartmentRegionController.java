package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.DepartmentRegionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.DepartmentRegionController.DEPARTMENT_REGION;

@Slf4j
@Tag(name = "Base - Department Region", description = "Base Module")
@RestController
@RequestMapping(DEPARTMENT_REGION)
public class DepartmentRegionController {

    public static final String DEPARTMENT_REGION = "/department-regions";

    public static final String DEPARTMENT_REGION_ID = "department_region_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DEPARTMENT_REGION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DEPARTMENT_REGION_ID + "}";
    public static final String DELETE = "/delete/{" + DEPARTMENT_REGION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_COUNTRY = "/search-by-country";

    @Autowired
    private DepartmentRegionUseCase departmentRegionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create department region.",
            description = "Create department region."
    )
    public ApiResponse create(
            @RequestBody DepartmentRegionCreateRequest request) {
        return ApiResponse.success(
                departmentRegionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find department region by id.",
            description = "Find department region by id."
    )
    public ApiResponse findBy(
            @PathVariable(DEPARTMENT_REGION_ID) String id) {
        return ApiResponse.success(
                departmentRegionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all department regions.",
            description = "Find all department regions."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                departmentRegionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit department region.",
            description = "Edit department region."
    )
    public ApiResponse edit(
            @PathVariable(DEPARTMENT_REGION_ID) String id,
            @RequestBody DepartmentRegionUpdateRequest request) {
        return ApiResponse.success(
                departmentRegionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete department region.",
            description = "Delete department region."
    )
    public ApiResponse deleteById(
           @PathVariable(DEPARTMENT_REGION_ID) String id) {
        departmentRegionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate department regions.",
            description = "Search all department regions."
    )
    public ApiResponse searchPaginate(
            @RequestBody DepartmentRegionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                departmentRegionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate department regions.",
            description = "Search filter paginate department regions."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DepartmentRegionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                departmentRegionUseCase.searchFilterAll(searchFilterRequest)
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
                departmentRegionUseCase.searchAllByCountry(searchRequestById)
        );
    }

}
