package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategorySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategorySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.lodgingcategory.LodgingCategoryUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.LodgingCategoryUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.LodgingCategoryController.LODGING_CATEGORY;

@Slf4j
@Tag(name = "Maintenance - Lodging Category", description = "Maintenance Module")
@RestController
@RequestMapping(LODGING_CATEGORY)
public class LodgingCategoryController {

    public static final String LODGING_CATEGORY = "/lodging-categories";

    public static final String LODGING_CATEGORY_ID = "lodging_category_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + LODGING_CATEGORY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + LODGING_CATEGORY_ID + "}";
    public static final String DELETE = "/delete/{" + LODGING_CATEGORY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private LodgingCategoryUseCase lodgingCategoryUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create lodging category.",
            description = "Create lodging category."
    )
    public ApiResponse create(
            @RequestBody LodgingCategoryCreateRequest request) {
        return ApiResponse.success(
                lodgingCategoryUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find lodging category by id.",
            description = "Find lodging category by id."
    )
    public ApiResponse findBy(
            @PathVariable(LODGING_CATEGORY_ID) String id) {
        return ApiResponse.success(
                lodgingCategoryUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all lodging categories.",
            description = "Find all lodging categories."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                lodgingCategoryUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit lodging category.",
            description = "Edit lodging category."
    )
    public ApiResponse edit(
            @PathVariable(LODGING_CATEGORY_ID) String id,
            @RequestBody LodgingCategoryUpdateRequest request) {
        return ApiResponse.success(
                lodgingCategoryUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete lodging category.",
            description = "Delete lodging category."
    )
    public ApiResponse deleteById(
           @PathVariable(LODGING_CATEGORY_ID) String id) {
        lodgingCategoryUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate lodging categories.",
            description = "Search all lodging categories."
    )
    public ApiResponse searchPaginate(
            @RequestBody LodgingCategorySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                lodgingCategoryUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate lodging categories.",
            description = "Search filter paginate lodging categories."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody LodgingCategorySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                lodgingCategoryUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
