package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.ProvinceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.ProvinceController.PROVINCE;

@Slf4j
@Tag(name = "Base - Province", description = "Base Module")
@RestController
@RequestMapping(PROVINCE)
public class ProvinceController {

    public static final String PROVINCE = "/provinces";

    public static final String PROVINCE_ID = "province_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROVINCE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROVINCE_ID + "}";
    public static final String DELETE = "/delete/{" + PROVINCE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_DEPARTMENT_REGION = "/search-by-department-region";

    @Autowired
    private ProvinceUseCase provinceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create province.",
            description = "Create province."
    )
    public ApiResponse create(
            @RequestBody ProvinceCreateRequest request) {
        return ApiResponse.success(
                provinceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find province by id.",
            description = "Find province by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROVINCE_ID) String id) {
        return ApiResponse.success(
                provinceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all provinces.",
            description = "Find all provinces."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                provinceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit province.",
            description = "Edit province."
    )
    public ApiResponse edit(
            @PathVariable(PROVINCE_ID) String id,
            @RequestBody ProvinceUpdateRequest request) {
        return ApiResponse.success(
                provinceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete province.",
            description = "Delete province."
    )
    public ApiResponse deleteById(
           @PathVariable(PROVINCE_ID) String id) {
        provinceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate provinces.",
            description = "Search all provinces."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProvinceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                provinceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate provinces.",
            description = "Search filter paginate provinces."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProvinceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                provinceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_BY_DEPARTMENT_REGION)
    @Operation(
            summary = "Search by department region.",
            description = "Search by department region."
    )
    public ApiResponse searchByDepartmentRegion(
            @RequestBody BaseSearchRequestById searchRequestById
    ) {
        return ApiResponse.success(
                provinceUseCase.searchAllByDepartmentRegion(searchRequestById)
        );
    }
}
