package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemLodgerController.REGISTER_BOOKING_ITEM_LODGER;

@Slf4j
@Tag(name = "Booking - Register Booking Item Lodger", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_LODGER)
public class RegisterBookingItemLodgerController {

    public static final String REGISTER_BOOKING_ITEM_LODGER = "/register-booking-item-lodgers";

    public static final String REGISTER_BOOKING_ITEM_LODGER_ID = "register_booking_item_lodger_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemLodgerUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item lodger.",
            description = "Create register booking item lodger."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemLodgerCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item lodger by id.",
            description = "Find register booking item lodger by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item lodgers.",
            description = "Find all register booking item lodgers."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item lodger.",
            description = "Edit register booking item lodger."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String id,
            @RequestBody RegisterBookingItemLodgerUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item lodger.",
            description = "Delete register booking item lodger."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item lodgers.",
            description = "Search all register booking item lodgers."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemLodgerSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item lodgers.",
            description = "Search filter paginate register booking item lodgers."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemLodgerSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
