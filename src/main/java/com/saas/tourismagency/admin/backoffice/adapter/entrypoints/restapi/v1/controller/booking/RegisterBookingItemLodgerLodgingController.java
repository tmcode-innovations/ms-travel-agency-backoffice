package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodSearchFilterReq;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerLodgingUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemLodgerLodgingController.REGISTER_BOOKING_ITEM_LODGER_LODGING;

@Slf4j
@Tag(name = "Booking - Register Booking Item Lodger Lodging", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_LODGER_LODGING)
public class RegisterBookingItemLodgerLodgingController {

    public static final String REGISTER_BOOKING_ITEM_LODGER_LODGING = "/register-booking-item-lodger-lodgings";

    public static final String REGISTER_BOOKING_ITEM_LODGER_LODGING_ID = "register_booking_item_lodger_lodging_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemLodgerLodgingUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item lodger lodging.",
            description = "Create register booking base."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemLodgerLodgingCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item lodger lodging by id.",
            description = "Find register booking item lodger lodging by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item lodger lodgings.",
            description = "Find all register booking item lodger lodgings."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item lodger lodging.",
            description = "Edit register booking item lodger lodging."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String id,
            @RequestBody RegisterBookingItemLodgerLodgingUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item lodger lodging.",
            description = "Delete register booking item lodger lodging."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item lodger lodgings.",
            description = "Search all register booking item lodger lodgings."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemLodgerLodgingSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item lodger lodgings.",
            description = "Search filter paginate register booking item lodger lodgings."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemLodgerLodSearchFilterReq searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
