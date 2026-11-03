package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingBaseUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingBaseController.REGISTER_BOOKING_BASE;

@Slf4j
@Tag(name = "Booking - Register Booking", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_BASE)
public class RegisterBookingBaseController {

    public static final String REGISTER_BOOKING_BASE = "/register-booking-bases";

    public static final String REGISTER_BOOKING_BASE_ID = "register_booking_base_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_BASE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_BASE_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_BASE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingBaseUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking base.",
            description = "Create register booking base."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingBaseCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking base by id.",
            description = "Find register booking base by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_BASE_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking bases.",
            description = "Find all register booking bases."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking base.",
            description = "Edit register booking base."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_BASE_ID) String id,
            @RequestBody RegisterBookingBaseUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking base.",
            description = "Delete register booking base."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_BASE_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking bases.",
            description = "Search all register booking bases."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingBaseSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking bases.",
            description = "Search filter paginate register booking bases."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingBaseSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
