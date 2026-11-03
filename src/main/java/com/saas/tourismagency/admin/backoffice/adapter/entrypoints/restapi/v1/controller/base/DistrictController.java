package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.DistrictCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.DistrictSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.DistrictSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.DistrictUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.DistrictUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.DistrictController.DISTRICT;

@Slf4j
@Tag(name = "Base - District", description = "Base Module")
@RestController
@RequestMapping(DISTRICT)
public class DistrictController {

    public static final String DISTRICT = "/districts";

    public static final String DISTRICT_ID = "district_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DISTRICT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DISTRICT_ID + "}";
    public static final String DELETE = "/delete/{" + DISTRICT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_PROVINCE = "/search-by-province";

    @Autowired
    private DistrictUseCase districtUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create district.",
            description = "Create district."
    )
    public ApiResponse create(
            @RequestBody DistrictCreateRequest request) {
        return ApiResponse.success(
                districtUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find district by id.",
            description = "Find district by id."
    )
    public ApiResponse findBy(
            @PathVariable(DISTRICT_ID) String id) {
        return ApiResponse.success(
                districtUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all districts.",
            description = "Find all districts."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                districtUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit district.",
            description = "Edit district."
    )
    public ApiResponse edit(
            @PathVariable(DISTRICT_ID) String id,
            @RequestBody DistrictUpdateRequest request) {
        return ApiResponse.success(
                districtUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete district.",
            description = "Delete district."
    )
    public ApiResponse deleteById(
           @PathVariable(DISTRICT_ID) String id) {
        districtUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate districts.",
            description = "Search all districts."
    )
    public ApiResponse searchPaginate(
            @RequestBody DistrictSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                districtUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate districts.",
            description = "Search filter paginate districts."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DistrictSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                districtUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_BY_PROVINCE)
    @Operation(
            summary = "Search by province.",
            description = "Search by province."
    )
    public ApiResponse searchByProvince(
            @RequestBody BaseSearchRequestById searchRequestById
    ) {
        return ApiResponse.success(
                districtUseCase.searchAllByProvince(searchRequestById)
        );
    }

}
