package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedSearchFilterReq;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgingFeedingUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemLodgingFeedingController.REGISTER_BOOKING_ITEM_LODGING_FEEDING;

@Slf4j
@Tag(name = "Booking - Register Booking Item Lodging Feeding", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_LODGING_FEEDING)
public class RegisterBookingItemLodgingFeedingController {

    public static final String REGISTER_BOOKING_ITEM_LODGING_FEEDING = "/register-booking-item-lodging-feedings";

    public static final String REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID = "register_booking_item_lodging_feeding_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemLodgingFeedingUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item lodging feeding.",
            description = "Create register booking base."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemLodgingFeedingCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item lodging feeding by id.",
            description = "Find register booking item lodging feeding by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item lodging feedings.",
            description = "Find all register booking item lodging feedings."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item lodging feeding.",
            description = "Edit register booking item lodging feeding."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID) String id,
            @RequestBody RegisterBookingItemLodgingFeedingUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item lodging feeding.",
            description = "Delete register booking item lodging feeding."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGING_FEEDING_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item lodging feedings.",
            description = "Search all register booking item lodging feedings."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemLodgingFeedingSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item lodging feedings.",
            description = "Search filter paginate register booking item lodging feedings."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemLodgingFeedSearchFilterReq searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
