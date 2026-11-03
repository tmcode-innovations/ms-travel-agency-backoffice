package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerGroupUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.PassengerGroupController.PASSENGER_GROUP;

@Slf4j
@Tag(name = "Booking - Passenger Group", description = "Booking Module")
@RestController
@RequestMapping(PASSENGER_GROUP)
public class PassengerGroupController {

    public static final String PASSENGER_GROUP = "/passenger-groups";

    public static final String PASSENGER_GROUP_ID = "passenger_group_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PASSENGER_GROUP_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PASSENGER_GROUP_ID + "}";
    public static final String DELETE = "/delete/{" + PASSENGER_GROUP_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PassengerGroupUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create passenger group.",
            description = "Create passenger group."
    )
    public ApiResponse create(
            @RequestBody PassengerGroupCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find passenger group by id.",
            description = "Find passenger group by id."
    )
    public ApiResponse findBy(
            @PathVariable(PASSENGER_GROUP_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all passenger groups.",
            description = "Find all passenger groups."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit passenger group.",
            description = "Edit passenger group."
    )
    public ApiResponse edit(
            @PathVariable(PASSENGER_GROUP_ID) String id,
            @RequestBody PassengerGroupUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete passenger group.",
            description = "Delete passenger group."
    )
    public ApiResponse deleteById(
           @PathVariable(PASSENGER_GROUP_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate passenger groups.",
            description = "Search all passenger groups."
    )
    public ApiResponse searchPaginate(
            @RequestBody PassengerGroupSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate passenger groups.",
            description = "Search filter paginate passenger groups."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PassengerGroupSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
