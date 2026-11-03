package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.RestaurantTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.RestaurantTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.RestaurantTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.RestaurantTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.RestaurantTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.RestaurantTypeController.RESTAURANT_TYPE;

@Slf4j
@Tag(name = "Maintenance - Restaurant Type", description = "Maintenance Module")
@RestController
@RequestMapping(RESTAURANT_TYPE)
public class RestaurantTypeController {

    public static final String RESTAURANT_TYPE = "/restaurants-types";

    public static final String RESTAURANT_TYPE_ID = "restaurant_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + RESTAURANT_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + RESTAURANT_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + RESTAURANT_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RestaurantTypeUseCase restaurantTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create restaurant type.",
            description = "Create restaurant type."
    )
    public ApiResponse create(
            @RequestBody RestaurantTypeCreateRequest request) {
        return ApiResponse.success(
                restaurantTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find restaurant type by id.",
            description = "Find restaurant type by id."
    )
    public ApiResponse findBy(
            @PathVariable(RESTAURANT_TYPE_ID) String id) {
        return ApiResponse.success(
                restaurantTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all restaurant types.",
            description = "Find all restaurant types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                restaurantTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit restaurant type.",
            description = "Edit restaurant type."
    )
    public ApiResponse edit(
            @PathVariable(RESTAURANT_TYPE_ID) String id,
            @RequestBody RestaurantTypeUpdateRequest request) {
        return ApiResponse.success(
                restaurantTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete restaurant type.",
            description = "Delete restaurant type."
    )
    public ApiResponse deleteById(
           @PathVariable(RESTAURANT_TYPE_ID) String id) {
        restaurantTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate restaurant types.",
            description = "Search all restaurant types."
    )
    public ApiResponse searchPaginate(
            @RequestBody RestaurantTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                restaurantTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate restaurant types.",
            description = "Search filter paginate restaurant types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RestaurantTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                restaurantTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
