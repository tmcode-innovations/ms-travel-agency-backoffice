package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgingUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemLodgingController.REGISTER_BOOKING_ITEM_LODGING;

@Slf4j
@Tag(name = "Booking - Register Booking Item Lodging", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_LODGING)
public class RegisterBookingItemLodgingController {

    public static final String REGISTER_BOOKING_ITEM_LODGING = "/register-booking-item-lodgings";

    public static final String REGISTER_BOOKING_ITEM_LODGING_ID = "register_booking_item_lodging_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_LODGING_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_LODGING_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_LODGING_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemLodgingUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item lodging.",
            description = "Create register booking item lodging."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemLodgingCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item lodging by id.",
            description = "Find register booking item lodging by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item lodgings.",
            description = "Find all register booking item lodgings."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item lodging.",
            description = "Edit register booking item lodging."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_ID) String id,
            @RequestBody RegisterBookingItemLodgingUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item lodging.",
            description = "Delete register booking item lodging."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item lodgings.",
            description = "Search all register booking item lodgings."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemLodgingSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item lodgings.",
            description = "Search filter paginate register booking item lodgings."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemLodgingSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
