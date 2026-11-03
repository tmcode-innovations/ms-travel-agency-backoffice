package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.TransportTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.TransportTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.TransportTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.transporttype.TransportTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.TransportTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.TransportTypeController.TRANSPORT_TYPE;

@Slf4j
@Tag(name = "Maintenance - Transport Type", description = "Maintenance Module")
@RestController
@RequestMapping(TRANSPORT_TYPE)
public class TransportTypeController {

    public static final String TRANSPORT_TYPE = "/transport-types";

    public static final String TRANSPORT_TYPE_ID = "transport_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + TRANSPORT_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + TRANSPORT_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + TRANSPORT_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private TransportTypeUseCase transportTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create transport type.",
            description = "Create transport type."
    )
    public ApiResponse create(
            @RequestBody TransportTypeCreateRequest request) {
        return ApiResponse.success(
                transportTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find transport type by id.",
            description = "Find transport type by id."
    )
    public ApiResponse findBy(
            @PathVariable(TRANSPORT_TYPE_ID) String id) {
        return ApiResponse.success(
                transportTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all transport types.",
            description = "Find all transport types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                transportTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit transport type.",
            description = "Edit transport type."
    )
    public ApiResponse edit(
            @PathVariable(TRANSPORT_TYPE_ID) String id,
            @RequestBody TransportTypeUpdateRequest request) {
        return ApiResponse.success(
                transportTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete transport type.",
            description = "Delete transport type."
    )
    public ApiResponse deleteById(
           @PathVariable(TRANSPORT_TYPE_ID) String id) {
        transportTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate transport types.",
            description = "Search all transport types."
    )
    public ApiResponse searchPaginate(
            @RequestBody TransportTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                transportTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate transport types.",
            description = "Search filter paginate transport types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody TransportTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                transportTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
