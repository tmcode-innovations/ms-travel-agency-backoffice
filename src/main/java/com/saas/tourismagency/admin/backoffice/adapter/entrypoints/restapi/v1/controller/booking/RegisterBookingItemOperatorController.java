package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemOperatorUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingItemOperatorController.REGISTER_BOOKING_ITEM_OPERATOR;

@Slf4j
@Tag(name = "Booking - Register Booking Item Operator", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ITEM_OPERATOR)
public class RegisterBookingItemOperatorController {

    public static final String REGISTER_BOOKING_ITEM_OPERATOR = "/register-booking-item-operators";

    public static final String REGISTER_BOOKING_ITEM_OPERATOR_ID = "register_booking_item_operator_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_ITEM_OPERATOR_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_ITEM_OPERATOR_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_ITEM_OPERATOR_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterBookingItemOperatorUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking item operator.",
            description = "Create register booking item operator."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingItemOperatorCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking item operator by id.",
            description = "Find register booking item operator by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_ITEM_OPERATOR_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking item operators.",
            description = "Find all register booking item operators."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking item operator.",
            description = "Edit register booking item operator."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_ITEM_OPERATOR_ID) String id,
            @RequestBody RegisterBookingItemOperatorUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking item operator.",
            description = "Delete register booking item operator."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_ITEM_OPERATOR_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking item operators.",
            description = "Search all register booking item operators."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingItemOperatorSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking item operators.",
            description = "Search filter paginate register booking item operators."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingItemOperatorSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

}
