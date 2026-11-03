package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.PassengerTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.PassengerTypeController.PASSENGER_TYPE;

@Slf4j
@Tag(name = "Maintenance - Passenger Type", description = "Maintenance Module")
@RestController
@RequestMapping(PASSENGER_TYPE)
public class PassengerTypeController {

    public static final String PASSENGER_TYPE = "/passenger-types";

    public static final String PASSENGER_TYPE_ID = "passenger_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PASSENGER_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PASSENGER_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + PASSENGER_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PassengerTypeUseCase passengerTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create passenger type.",
            description = "Create passenger type."
    )
    public ApiResponse create(
            @RequestBody PassengerTypeCreateRequest request) {
        return ApiResponse.success(
                passengerTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find passenger type by id.",
            description = "Find passenger type by id."
    )
    public ApiResponse findBy(
            @PathVariable(PASSENGER_TYPE_ID) String id) {
        return ApiResponse.success(
                passengerTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all passenger types.",
            description = "Find all passenger types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                passengerTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit passenger type.",
            description = "Edit passenger type."
    )
    public ApiResponse edit(
            @PathVariable(PASSENGER_TYPE_ID) String id,
            @RequestBody PassengerTypeUpdateRequest request) {
        return ApiResponse.success(
                passengerTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete passenger typen.",
            description = "Delete passenger type."
    )
    public ApiResponse deleteById(
           @PathVariable(PASSENGER_TYPE_ID) String id) {
        passengerTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate passenger types.",
            description = "Search all passenger types."
    )
    public ApiResponse searchPaginate(
            @RequestBody PassengerTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                passengerTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate passenger types.",
            description = "Search filter paginate passenger types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PassengerTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                passengerTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
