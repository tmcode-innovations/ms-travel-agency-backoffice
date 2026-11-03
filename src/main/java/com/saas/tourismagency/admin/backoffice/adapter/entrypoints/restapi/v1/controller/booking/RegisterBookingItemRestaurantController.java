package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemRestaurantUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemRestaurantController.REGISTER_BOOKING_ITEM_RESTAURANT;

@Slf4j
@Tag(name = "Booking - Register Booking Item Restaurant", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_RESTAURANT)
public class RegisterBookingItemRestaurantController {

    public static final String REGISTER_BOOKING_ITEM_RESTAURANT = "/register-booking-item-restaurants";

    public static final String REGISTER_BOOKING_ITEM_RESTAURANT_ID = "register_booking_item_restaurant_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_RESTAURANT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_RESTAURANT_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_RESTAURANT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemRestaurantUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item restaurant.",
            description = "Create register booking item restaurant."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemRestaurantCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item restaurant by id.",
            description = "Find register booking item restaurant by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_RESTAURANT_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item restaurants.",
            description = "Find all register booking item restaurants."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item restaurant.",
            description = "Edit register booking item restaurant."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_RESTAURANT_ID) String id,
            @RequestBody RegisterBookingItemRestaurantUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item restaurant.",
            description = "Delete register booking item restaurant."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_RESTAURANT_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item restaurants.",
            description = "Search all register booking item restaurants."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemRestaurantSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item restaurants.",
            description = "Search filter paginate register booking item restaurants."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemRestaurantSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
