package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.usecase.booking.ItineraryItemUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.ItineraryItemController.ITINERARY_ITEM;

@Slf4j
@Tag(name = "Booking - Itinerary Item", description = "Booking Module")
@RestController
@RequestMapping(ITINERARY_ITEM)
public class ItineraryItemController {

    public static final String ITINERARY_ITEM = "/itinerary-items";

    public static final String ITINERARY_ITEM_ID = "itinerary_item_id";

    public static final String CHANGE_STATE = "/change-state/{" + ITINERARY_ITEM_ID + "}";
    public static final String CHANGE_SUPPLIER = "/change-supplier/{" + ITINERARY_ITEM_ID + "}";
    public static final String CHANGE_NOTE = "/change-note/{" + ITINERARY_ITEM_ID + "}";
    public static final String VERIFY_EXIST_ITINERARY_ITEM = "/exist-itinerary-item-by-id/{" + ITINERARY_ITEM_ID + "}";

    public static final String CHANGE_STATE_INVOICE = "/change-state-invoice/{" + ITINERARY_ITEM_ID + "}";
    public static final String CHANGE_NOTE_INVOICE = "/change-note-invoice/{" + ITINERARY_ITEM_ID + "}";
    public static final String CHANGE_DATE_INVOICE = "/change-date-invoice/{" + ITINERARY_ITEM_ID + "}";

    @Autowired
    private ItineraryItemUseCase useCase;

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state itinerary item.",
            description = "Change state itinerary item."
    )
    public ApiResponse changeStateItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeStateRequest request) {
        return ApiResponse.success(
                useCase.changeState(id, request)
        );
    }

    @PutMapping(CHANGE_SUPPLIER)
    @Operation(
            summary = "Change supplier itinerary item.",
            description = "Change supplier itinerary item."
    )
    public ApiResponse changeSupplierItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeSupplierRequest request) {
        return ApiResponse.success(
                useCase.changeSupplier(id, request)
        );
    }

    @PutMapping(CHANGE_NOTE)
    @Operation(
            summary = "Change note itinerary item.",
            description = "Change note itinerary item."
    )
    public ApiResponse changeNoteItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeNoteRequest request) {
        return ApiResponse.success(
                useCase.changeNote(id, request)
        );
    }

    @GetMapping(VERIFY_EXIST_ITINERARY_ITEM)
    @Operation(
            summary = "Exist itinerary item.",
            description = "Exist note itinerary item."
    )
    public ApiResponse existItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id) {
        return ApiResponse.success(
                useCase.existItineraryItemById(id)
        );
    }

    @PutMapping(CHANGE_STATE_INVOICE)
    @Operation(
            summary = "Change state invoice itinerary item.",
            description = "Change state invoice itinerary item."
    )
    public ApiResponse changeStateInvoiceItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeStateInvoiceRequest request) {
        return ApiResponse.success(
                useCase.changeStateInvoice(id, request)
        );
    }

    @PutMapping(CHANGE_NOTE_INVOICE)
    @Operation(
            summary = "Change note invoice itinerary item.",
            description = "Change note invoice itinerary item."
    )
    public ApiResponse changeNoteInvoiceItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeNoteInvoiceRequest request) {
        return ApiResponse.success(
                useCase.changeNoteInvoice(id, request)
        );
    }

    @PutMapping(CHANGE_DATE_INVOICE)
    @Operation(
            summary = "Change date invoice itinerary item.",
            description = "Change date invoice itinerary item."
    )
    public ApiResponse changeDateInvoiceItineraryItem(
            @PathVariable(ITINERARY_ITEM_ID) String id,
            @RequestBody ItineraryItemChangeDateInvoiceRequest request) {
        return ApiResponse.success(
                useCase.changeDateInvoice(id, request)
        );
    }
}
