package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.usecase.booking.BookingNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.BookingNomenclatureController.BOOKING_NOMENCLATURE;

@Slf4j
@Tag(name = "Booking - Nomenclature", description = "Booking Module")
@RestController
@RequestMapping(BOOKING_NOMENCLATURE)
public class BookingNomenclatureController {

    public static final String BOOKING_NOMENCLATURE = "/booking-nomenclatures";

    public static final String SEARCH_STATE_BOOKING = "/search-state-bookings";
    public static final String SEARCH_STATE_REGISTER_GROUP = "/search-state-register-groups";
    public static final String SEARCH_TYPE_FEEDING_BOOKING = "/search-type-feeding-bookings";
    public static final String SEARCH_TYPE_PASSENGER_TC_BOOKING = "/search-type-passenger-tc-bookings";
    public static final String SEARCH_STATE_SERVICE_INVOICE = "/search-state-service-invoice";
    public static final String SEARCH_STATE_SERVICE_INVOICE_WITHOUT_GENERATED = "/search-state-service-invoice-without-generated";

    @Autowired
    private BookingNomenclatureUseCase bookingNomenclatureUseCase;

    @PostMapping(SEARCH_STATE_BOOKING)
    @Operation(
            summary = "Search state bookings.",
            description = "Search all state bookings."
    )
    public ApiResponse searchAllStateBooking() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllStateBooking()
        );
    }

    @PostMapping(SEARCH_STATE_REGISTER_GROUP)
    @Operation(
            summary = "Search state register groups.",
            description = "Search all state register groups."
    )
    public ApiResponse searchAllStateRegisterGroup() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllStateRegisterGroup()
        );
    }

    @PostMapping(SEARCH_TYPE_FEEDING_BOOKING)
    @Operation(
            summary = "Search type feeding bookings.",
            description = "Search all type feeding bookings."
    )
    public ApiResponse searchAllTypeFeedingBooking() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllTypeFeedingBooking()
        );
    }

    @PostMapping(SEARCH_TYPE_PASSENGER_TC_BOOKING)
    @Operation(
            summary = "Search type passenger tc bookings.",
            description = "Search all type passenger tc bookings."
    )
    public ApiResponse searchAllTypePassengerTcBooking() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllTypePassengerTcBooking()
        );
    }

    @PostMapping(SEARCH_STATE_SERVICE_INVOICE)
    @Operation(
            summary = "Search state service invoice.",
            description = "Search all state service invoice."
    )
    public ApiResponse searchAllStateServiceInvoice() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllStateServiceInvoice()
        );
    }

    @PostMapping(SEARCH_STATE_SERVICE_INVOICE_WITHOUT_GENERATED)
    @Operation(
            summary = "Search state service invoice.",
            description = "Search all state service invoice."
    )
    public ApiResponse searchAllStateServiceInvoiceWithoutgenerated() {
        return ApiResponse.success(
                bookingNomenclatureUseCase.searchAllStateServiceInvoiceWithoutPending()
        );
    }

}
