package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingRestaurantUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingRestaurantController.REGISTER_BOOKING_RESTAURANT;

@Slf4j
@Tag(name = "Booking - Register Booking Restaurant", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_RESTAURANT)
public class RegisterBookingRestaurantController {

    public static final String REGISTER_BOOKING_RESTAURANT = "/register-booking-restaurants";

    public static final String REGISTER_BOOKING_RESTAURANT_ID = "register_booking_restaurant_id";
    public static final String REGISTER_BOOKING_ITEM_ID = "register_booking_item_id";
    public static final String PASSENGER_BOOKING_ID = "passenger_booking_id";
    public static final String PASSENGER_BOOKING_TYPE = "passenger_booking_type";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_RESTAURANT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_RESTAURANT_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_RESTAURANT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_BOOKING_ITEM = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/booking-item/add";
    public static final String EDIT_BOOKING_ITEM = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/booking-item/edit/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String DELETE_BOOKING_ITEM = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/booking-item/delete/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String FIND_BOOKING_ITEM = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/booking-item/find/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String FIND_ALL_BOOKING_ITEM = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/booking-item/find-all";

    public static final String ADD_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/add";
    public static final String ADD_PASSENGER_BOOKING_MULTIPLE = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking-multiple/add";
    public static final String DELETE_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/delete/{" + PASSENGER_BOOKING_ID + "}";
    public static final String FIND_ALL_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger/{" + PASSENGER_BOOKING_TYPE + "}";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_ADULT = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-adult";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_CHILD = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-child";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_FREE = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-free";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-tc";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC_LOCAL = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-tc-local";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_GUIDE = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/passenger-booking/find-all-by-type-passenger-guide";

    public static final String EXPORT_REGISTER_BOOKING_CUPON = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/export-register-booking-cupon/pdf";
    public static final String EXPORT_REGISTER_BOOKING = "/{" + REGISTER_BOOKING_RESTAURANT_ID + "}/export-register-booking/pdf";

    public static final String CHANGE_STATE = "/change-state/{" + REGISTER_BOOKING_RESTAURANT_ID + "}";
    public static final String CHANGE_SUPPLIER = "/change-supplier/{" + REGISTER_BOOKING_RESTAURANT_ID + "}";

    @Autowired
    private RegisterBookingRestaurantUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking restaurant.",
            description = "Create register booking restaurant."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingRestaurantCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking restaurant by id.",
            description = "Find register booking restaurant by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking restaurants.",
            description = "Find all register booking restaurants."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking restaurant.",
            description = "Edit register booking restaurant."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String id,
            @RequestBody RegisterBookingRestaurantUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking restaurant.",
            description = "Delete register booking restaurant."
    )
    public ApiResponse deleteById(
           @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking restaurants.",
            description = "Search all register booking restaurants."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingRestaurantSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking restaurants.",
            description = "Search filter paginate register booking restaurants."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingRestaurantSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_BOOKING_ITEM)
    @Operation(
            summary = "add booking item.",
            description = "Add booking item."
    )
    public ApiResponse addBookingItem(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @RequestBody RegisterBookingItemRestaurantCreateRequest request) {
        return ApiResponse.success(
                useCase.addRegisterBookingItemRestaurant(request, idRegisterBooking)
        );
    }

    @PutMapping(EDIT_BOOKING_ITEM)
    @Operation(
            summary = "Edit booking item .",
            description = "Edit booking item."
    )
    public ApiResponse editBookingItem(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem,
            @RequestBody RegisterBookingItemRestaurantUpdateRequest request) {
        return ApiResponse.success(
                useCase.editRegisterBookingItemRestaurant(idRegisterBookingItem, request, idRegisterBooking)
        );
    }

    @DeleteMapping(DELETE_BOOKING_ITEM)
    @Operation(
            summary = "Delete booking item.",
            description = "Delete booking item."
    )
    public ApiResponse deleteBookingItem(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem
    ) {
        useCase.deleteRegisterBookingItemRestaurant(idRegisterBookingItem, idRegisterBooking);
        return ApiResponse.success();
    }

    @GetMapping(FIND_BOOKING_ITEM)
    @Operation(
            summary = "Find booking item by booking.",
            description = "Find booking item by booking."
    )
    public ApiResponse findBookingItemByIdAndBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem
    ) {
        return ApiResponse.success(
                useCase.findByIdAndBooking(idRegisterBookingItem, idRegisterBooking)
        );
    }

    @GetMapping(FIND_ALL_BOOKING_ITEM)
    @Operation(
            summary = "Find all booking item by booking.",
            description = "Find all booking item by booking."
    )
    public ApiResponse findAllBookingItemByBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingItemRestaurantByBooking(idRegisterBooking)
        );
    }

    @PostMapping(ADD_PASSENGER_BOOKING)
    @Operation(
            summary = "add passenger booking.",
            description = "Add passenger booking."
    )
    public ApiResponse addPassengerBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @RequestBody PassengerBookingCreateRequest request) {
        return ApiResponse.success(
                useCase.addPassengerBooking(request, idRegisterBooking)
        );
    }

    @PostMapping(ADD_PASSENGER_BOOKING_MULTIPLE)
    @Operation(
            summary = "add passenger booking multiple.",
            description = "Add passenger booking multiple."
    )
    public ApiResponse addPassengerBookingMultiple(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @RequestBody PassengerBookingCreateRequest request) {
        return ApiResponse.success(
                useCase.addPassengerBookingMultiple(request, idRegisterBooking)
        );
    }

    @DeleteMapping(DELETE_PASSENGER_BOOKING)
    @Operation(
            summary = "Delete passenger booking.",
            description = "Delete passenger booking."
    )
    public ApiResponse deletePassengerBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @PathVariable(PASSENGER_BOOKING_ID) String idPassengerBooking
    ) {
        useCase.deletePassengerBooking(idPassengerBooking, idRegisterBooking);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING)
    @Operation(
            summary = "Find all passenger booking by register booking.",
            description = "Find all passenger booking by register booking."
    )
    public ApiResponse findAllPassengerByRegisterBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBooking(idRegisterBooking)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger.",
            description = "Find all passenger booking by register booking and type passenger."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassenger(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking,
            @PathVariable(PASSENGER_BOOKING_TYPE) String passengerBookingType
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, passengerBookingType)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_ADULT)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger adult.",
            description = "Find all passenger booking by register booking and type passenger adult."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerAdult(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.ADULT.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_CHILD)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger child.",
            description = "Find all passenger booking by register booking and type passenger child."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerChild(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.CHILD.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_FREE)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger free.",
            description = "Find all passenger booking by register booking and type passenger free."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerFree(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.FREE.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger tc.",
            description = "Find all passenger booking by register booking and type passenger tc."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerTc(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.TC.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC_LOCAL)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger tc local.",
            description = "Find all passenger booking by register booking and type passenger tc local."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerTcLocal(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_GUIDE)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger guide.",
            description = "Find all passenger booking by register booking and type passenger guide."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerGuide(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.GUIDE.getName())
        );
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_CUPON)
    @Operation(
            summary = "Export register booking cupon.",
            description = "Export register booking cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingCuponPdf(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingRestaurantCuponPdf(idRegisterBooking);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_cupon_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_REGISTER_BOOKING)
    @Operation(
            summary = "Export register booking.",
            description = "Export register booking."
    )
    public ResponseEntity<Resource> exportRegisterBookingPdf(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingRestaurantPdf(idRegisterBooking);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_report.pdf").build().toString())
                .body(resource);
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state register booking restaurant.",
            description = "Change state register booking restaurant."
    )
    public ApiResponse changeStateRegisterBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String id,
            @RequestBody RegisterBookingChangeStateRequest request) {
        return ApiResponse.success(
                useCase.changeState(id, request)
        );
    }

    @PutMapping(CHANGE_SUPPLIER)
    @Operation(
            summary = "Change supplier register booking restaurant.",
            description = "Change supplier register booking restaurant."
    )
    public ApiResponse changeSupplierRegisterBooking(
            @PathVariable(REGISTER_BOOKING_RESTAURANT_ID) String id,
            @RequestBody RegisterBookingChangeSupplierRequest request) {
        return ApiResponse.success(
                useCase.changeSupplier(id, request)
        );
    }
}
