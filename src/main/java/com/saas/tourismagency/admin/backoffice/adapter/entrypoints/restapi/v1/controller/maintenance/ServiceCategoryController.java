package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.ServiceCategoryCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.ServiceCategorySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.ServiceCategorySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.servicecategory.ServiceCategoryUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ServiceCategoryUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.ServiceCategoryController.SERVICE_CATEGORY;

@Slf4j
@Tag(name = "Maintenance - Service Category", description = "Maintenance Module")
@RestController
@RequestMapping(SERVICE_CATEGORY)
public class ServiceCategoryController {

    public static final String SERVICE_CATEGORY = "/service-categories";

    public static final String SERVICE_CATEGORY_ID = "service_category_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SERVICE_CATEGORY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SERVICE_CATEGORY_ID + "}";
    public static final String DELETE = "/delete/{" + SERVICE_CATEGORY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private ServiceCategoryUseCase serviceCategoryUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create service category.",
            description = "Create service category."
    )
    public ApiResponse create(
            @RequestBody ServiceCategoryCreateRequest request) {
        return ApiResponse.success(
                serviceCategoryUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find service category by id.",
            description = "Find service category by id."
    )
    public ApiResponse findBy(
            @PathVariable(SERVICE_CATEGORY_ID) String id) {
        return ApiResponse.success(
                serviceCategoryUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all service categories.",
            description = "Find all service categories."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                serviceCategoryUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit service category.",
            description = "Edit service category."
    )
    public ApiResponse edit(
            @PathVariable(SERVICE_CATEGORY_ID) String id,
            @RequestBody ServiceCategoryUpdateRequest request) {
        return ApiResponse.success(
                serviceCategoryUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete service category.",
            description = "Delete service category."
    )
    public ApiResponse deleteById(
           @PathVariable(SERVICE_CATEGORY_ID) String id) {
        serviceCategoryUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate service categories.",
            description = "Search all service categories."
    )
    public ApiResponse searchPaginate(
            @RequestBody ServiceCategorySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                serviceCategoryUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate service categories.",
            description = "Search filter paginate service categories."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ServiceCategorySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                serviceCategoryUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
