package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.ServiceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.ServiceController.SERVICE;

@Slf4j
@Tag(name = "Maintenance - Service", description = "Maintenance Module")
@RestController
@RequestMapping(SERVICE)
public class ServiceController {

    public static final String SERVICE = "/services";

    public static final String SERVICE_ID = "service_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SERVICE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SERVICE_ID + "}";
    public static final String DELETE = "/delete/{" + SERVICE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private ServiceUseCase serviceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create service.",
            description = "Create service."
    )
    public ApiResponse create(
            @RequestBody ServiceCreateRequest request) {
        return ApiResponse.success(
                serviceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find service by id.",
            description = "Find service by id."
    )
    public ApiResponse findBy(
            @PathVariable(SERVICE_ID) String id) {
        return ApiResponse.success(
                serviceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all services.",
            description = "Find all services."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                serviceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit service.",
            description = "Edit service."
    )
    public ApiResponse edit(
            @PathVariable(SERVICE_ID) String id,
            @RequestBody ServiceUpdateRequest request) {
        return ApiResponse.success(
                serviceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete service.",
            description = "Delete service."
    )
    public ApiResponse deleteById(
           @PathVariable(SERVICE_ID) String id) {
        serviceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate services.",
            description = "Search all services."
    )
    public ApiResponse searchPaginate(
            @RequestBody ServiceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                serviceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate services.",
            description = "Search filter paginate services."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ServiceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                serviceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all suppliers.",
            description = "Find all suppliers."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                serviceUseCase.findAllSimple()
        );
    }
}
