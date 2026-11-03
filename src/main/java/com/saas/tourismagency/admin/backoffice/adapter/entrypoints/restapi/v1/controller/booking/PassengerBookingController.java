package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.PassengerBookingController.PASSENGER_BOOKING;

@Slf4j
@Tag(name = "Booking - Passenger Booking", description = "Booking Module")
@RestController
@RequestMapping(PASSENGER_BOOKING)
public class PassengerBookingController {

    public static final String PASSENGER_BOOKING = "/passenger-bookings";

    public static final String PASSENGER_BOOKING_ID = "passenger_booking_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PASSENGER_BOOKING_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PASSENGER_BOOKING_ID + "}";
    public static final String DELETE = "/delete/{" + PASSENGER_BOOKING_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PassengerBookingUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create passenger booking.",
            description = "Create passenger booking."
    )
    public ApiResponse create(
            @RequestBody PassengerBookingCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find passenger booking by id.",
            description = "Find passenger booking by id."
    )
    public ApiResponse findBy(
            @PathVariable(PASSENGER_BOOKING_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all passenger bookings.",
            description = "Find all passenger bookings."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit passenger booking.",
            description = "Edit passenger booking."
    )
    public ApiResponse edit(
            @PathVariable(PASSENGER_BOOKING_ID) String id,
            @RequestBody PassengerBookingUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete passenger booking.",
            description = "Delete passenger booking."
    )
    public ApiResponse deleteById(
           @PathVariable(PASSENGER_BOOKING_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate passenger bookings.",
            description = "Search all passenger bookings."
    )
    public ApiResponse searchPaginate(
            @RequestBody PassengerBookingSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate passenger bookings.",
            description = "Search filter paginate passenger bookings."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PassengerBookingSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
