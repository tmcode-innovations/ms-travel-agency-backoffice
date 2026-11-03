package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.ExportCuponTypePassengerBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeRoomBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingLodgerUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingLodgerController.REGISTER_BOOKING_LODGER;

@Slf4j
@Tag(name = "Booking - Register Booking Lodger", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_LODGER)
public class RegisterBookingLodgerController {

    public static final String REGISTER_BOOKING_LODGER = "/register-booking-lodgers";

    public static final String REGISTER_BOOKING_LODGER_ID = "register_booking_lodger_id";
    public static final String REGISTER_BOOKING_ITEM_LODGER_ID = "register_booking_item_lodger_id";
    public static final String REGISTER_BOOKING_ITEM_LODGER_LODGING_ID = "register_booking_item_lodger_lodging_id";

    public static final String PASSENGER_BOOKING_ID = "passenger_booking_id";
    public static final String PASSENGER_BOOKING_TYPE = "passenger_booking_type";
    public static final String PASSENGER_BOOKING_ROOM = "passenger_booking_room";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_LODGER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_LODGER_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_LODGER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_BOOKING_ITEM_LODGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger/add";
    public static final String EDIT_BOOKING_ITEM_LODGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger/edit/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";
    public static final String DELETE_BOOKING_ITEM_LODGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger/delete/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";
    public static final String FIND_BOOKING_ITEM_LODGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger/find/{" + REGISTER_BOOKING_ITEM_LODGER_ID + "}";
    public static final String FIND_ALL_BOOKING_ITEM_LODGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger/find-all";

    public static final String ADD_BOOKING_ITEM_LODGER_LODGING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger-lodging/add";
    public static final String EDIT_BOOKING_ITEM_LODGER_LODGING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger-lodging/edit/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";
    public static final String DELETE_BOOKING_ITEM_LODGER_LODGING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger-lodging/delete/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";
    public static final String FIND_BOOKING_ITEM_LODGER_LODGING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger-lodging/find/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}";
    public static final String FIND_ALL_BOOKING_ITEM_LODGER_LODGING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/booking-item-lodger-lodging/find-all";


    public static final String ADD_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/add";
    public static final String ADD_PASSENGER_BOOKING_MULTIPLE = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking-multiple/add";
    public static final String DELETE_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/delete/{" + PASSENGER_BOOKING_ID + "}";
    public static final String FIND_ALL_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room/{" + PASSENGER_BOOKING_ROOM + "}";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger/{" + PASSENGER_BOOKING_TYPE + "}";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-swb";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-twin";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_MAT = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-mat";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TRP = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-trp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_CDP = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-cdp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_QTP = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-qtp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SXTP = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-sxtp";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_ADULT = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-adult";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_CHILD = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-child";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_FREE = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-free";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-tc";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_TC_LOCAL = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-tc-local";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_GUIDE = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-passenger-guide";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-swb-tc";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-twin-tc";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC_LOCAL = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-swb-tc-local";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC_LOCAL = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-twin-tc-local";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_GUIDE = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-swb-guide";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_GUIDE = "/{" + REGISTER_BOOKING_LODGER_ID + "}/passenger-booking/find-all-by-type-room-twin-guide";


    public static final String EXPORT_REGISTER_BOOKING_CUPON = "/{" + REGISTER_BOOKING_LODGER_ID + "}/export-register-booking-cupon/pdf";
    public static final String EXPORT_REGISTER_BOOKING_ITEM_CUPON = "/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}/export-register-booking-item-cupon/pdf";
    public static final String EXPORT_REGISTER_BOOKING_ITEM_CUPON_ONLY_TC_LOCAL = "/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}/export-register-booking-item-cupon-only-tc-local/pdf";
    public static final String EXPORT_REGISTER_BOOKING_ITEM_CUPON_WITHOUT_TC_LOCAL = "/{" + REGISTER_BOOKING_ITEM_LODGER_LODGING_ID + "}/export-register-booking-item-cupon-without-tc-local/pdf";
    public static final String EXPORT_REGISTER_BOOKING = "/{" + REGISTER_BOOKING_LODGER_ID + "}/export-register-booking/pdf";

    public static final String CHANGE_STATE = "/change-state/{" + REGISTER_BOOKING_LODGER_ID + "}";
    public static final String CHANGE_SUPPLIER = "/change-supplier/{" + REGISTER_BOOKING_LODGER_ID + "}";

    @Autowired
    private RegisterBookingLodgerUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking lodger.",
            description = "Create register booking lodger."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingLodgerCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking lodger by id.",
            description = "Find register booking lodger by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking lodgers.",
            description = "Find all register booking lodgers."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking lodger.",
            description = "Edit register booking lodger."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String id,
            @RequestBody RegisterBookingLodgerUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking lodger.",
            description = "Delete register booking lodger."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking lodgers.",
            description = "Search all register booking lodgers."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingLodgerSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking lodgers.",
            description = "Search filter paginate register booking lodgers."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingLodgerSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }



    @PostMapping(ADD_BOOKING_ITEM_LODGER)
    @Operation(
            summary = "add booking item lodger.",
            description = "Add booking item lodger."
    )
    public ApiResponse addBookingItemLodger(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @RequestBody RegisterBookingItemLodgerCreateRequest request) {
        return ApiResponse.success(
                useCase.addRegisterBookingItemLodger(request, idRegisterBooking)
        );
    }

    @PutMapping(EDIT_BOOKING_ITEM_LODGER)
    @Operation(
            summary = "Edit booking item lodger.",
            description = "Edit booking item lodger."
    )
    public ApiResponse editBookingItemLodger(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String idRegisterBookingItem,
            @RequestBody RegisterBookingItemLodgerUpdateRequest request) {
        return ApiResponse.success(
                useCase.editRegisterBookingItemLodger(idRegisterBookingItem, request, idRegisterBooking)
        );
    }

    @DeleteMapping(DELETE_BOOKING_ITEM_LODGER)
    @Operation(
            summary = "Delete booking item lodger.",
            description = "Delete booking item lodger."
    )
    public ApiResponse deleteBookingItemLodger(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String idRegisterBookingItem
    ) {
        useCase.deleteRegisterBookingItemLodger(idRegisterBookingItem, idRegisterBooking);
        return ApiResponse.success();
    }

    @GetMapping(FIND_BOOKING_ITEM_LODGER)
    @Operation(
            summary = "Find booking item lodger by booking.",
            description = "Find booking item lodger by booking."
    )
    public ApiResponse findBookingItemLodgerByIdAndBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_ID) String idRegisterBookingItem
    ) {
        return ApiResponse.success(
                useCase.findByIdLodgerAndBooking(idRegisterBookingItem, idRegisterBooking)
        );
    }

    @GetMapping(FIND_ALL_BOOKING_ITEM_LODGER)
    @Operation(
            summary = "Find all booking item lodger by booking.",
            description = "Find all booking item lodger by booking."
    )
    public ApiResponse findAllBookingItemLodgerByBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingItemLodgerByBooking(idRegisterBooking)
        );
    }


    @PostMapping(ADD_BOOKING_ITEM_LODGER_LODGING)
    @Operation(
            summary = "add booking item lodger lodging.",
            description = "Add booking item lodger lodging."
    )
    public ApiResponse addBookingItemLodgerLodging(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @RequestBody RegisterBookingItemLodgerLodgingCreateRequest request) {
        return ApiResponse.success(
                useCase.addRegisterBookingItemLodgerLodging(request, idRegisterBooking)
        );
    }

    @PutMapping(EDIT_BOOKING_ITEM_LODGER_LODGING)
    @Operation(
            summary = "Edit booking item lodger lodging.",
            description = "Edit booking item lodger lodging."
    )
    public ApiResponse editBookingItemLodgerLodging(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem,
            @RequestBody RegisterBookingItemLodgerLodgingUpdateRequest request) {
        return ApiResponse.success(
                useCase.editRegisterBookingItemLodgerLodging(idRegisterBookingItem, request, idRegisterBooking)
        );
    }

    @DeleteMapping(DELETE_BOOKING_ITEM_LODGER_LODGING)
    @Operation(
            summary = "Delete booking item lodger lodging.",
            description = "Delete booking item lodger lodging."
    )
    public ApiResponse deleteBookingItemLodgerLodging(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem
    ) {
        useCase.deleteRegisterBookingItemLodgerLodging(idRegisterBookingItem, idRegisterBooking);
        return ApiResponse.success();
    }

    @GetMapping(FIND_BOOKING_ITEM_LODGER_LODGING)
    @Operation(
            summary = "Find booking item lodger lodging by booking.",
            description = "Find booking item lodger lodging by booking."
    )
    public ApiResponse findBookingItemLodgerLodgingByIdAndBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem
    ) {
        return ApiResponse.success(
                useCase.findByIdLodgerLodgingAndBooking(idRegisterBookingItem, idRegisterBooking)
        );
    }

    @GetMapping(FIND_ALL_BOOKING_ITEM_LODGER_LODGING)
    @Operation(
            summary = "Find all booking item lodger lodging by booking.",
            description = "Find all booking item lodger lodging by booking."
    )
    public ApiResponse findAllBookingItemLodgerLodgingByBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingItemLodgerLodgingByBooking(idRegisterBooking)
        );
    }

    @PostMapping(ADD_PASSENGER_BOOKING)
    @Operation(
            summary = "add passenger booking.",
            description = "Add passenger booking."
    )
    public ApiResponse addPassengerBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(PASSENGER_BOOKING_TYPE) String passengerBookingType
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, passengerBookingType)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM)
    @Operation(
            summary = "Find all passenger booking by register booking and type room.",
            description = "Find all passenger booking by register booking and type room."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoom(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking,
            @PathVariable(PASSENGER_BOOKING_ROOM) String typeRoomBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, typeRoomBooking)
        );
    }



    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_PASSENGER_ADULT)
    @Operation(
            summary = "Find all passenger booking by register booking and type passenger adult.",
            description = "Find all passenger booking by register booking and type passenger adult."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypePassengerAdult(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
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
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(idRegisterBooking, TypePassengerTcBookingEnum.GUIDE.getName())
        );
    }



    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb.",
            description = "Find all passenger booking by register booking and type room swb."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwb(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin.",
            description = "Find all passenger booking by register booking and type room twin."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwin(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_MAT)
    @Operation(
            summary = "Find all passenger booking by register booking and type room mat.",
            description = "Find all passenger booking by register booking and type room mat."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomMat(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.MAT.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TRP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room trp.",
            description = "Find all passenger booking by register booking and type room trp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTrp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TRP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_CDP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room cdp.",
            description = "Find all passenger booking by register booking and type room cdp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomCdp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.CDP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_QTP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room qtp.",
            description = "Find all passenger booking by register booking and type room qtp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomQtp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.QTP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SXTP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room sxtp.",
            description = "Find all passenger booking by register booking and type room sxtp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSxtp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SXTP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb tc.",
            description = "Find all passenger booking by register booking and type room swb tc."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwbTc(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin tc.",
            description = "Find all passenger booking by register booking and type room twin tc."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwinTc(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC_LOCAL)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb tc local.",
            description = "Find all passenger booking by register booking and type room swb tc local."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwbTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC_LOCAL)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin tc local.",
            description = "Find all passenger booking by register booking and type room twin tc local."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwinTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_GUIDE)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb guide.",
            description = "Find all passenger booking by register booking and type room swb guide."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwbGuide(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_GUIDE.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_GUIDE)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin guide.",
            description = "Find all passenger booking by register booking and type room twin guide."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwinGuide(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_GUIDE.getName())
        );
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_CUPON)
    @Operation(
            summary = "Export register booking cupon.",
            description = "Export register booking cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingCuponPdf(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingLodgerCuponPdf(idRegisterBooking);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_cupon_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_ITEM_CUPON)
    @Operation(
            summary = "Export register booking item lodger lodging cupon.",
            description = "Export register booking item lodger lodging cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingItemCuponPdf(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem
    ) {
        byte[] reportContent = useCase.exportRegisterBookingLodgerLodgingItemCuponPdf(idRegisterBookingItem);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_item_cupon_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_ITEM_CUPON_ONLY_TC_LOCAL)
    @Operation(
            summary = "Export register booking item lodger lodging cupon.",
            description = "Export register booking item lodger lodging cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingItemCuponPdfOnly(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem
    ) {
        byte[] reportContent = useCase.exportRegisterBookingLodgerLodgingItemCuponPdf(idRegisterBookingItem, ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName());
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_item_cupon_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_ITEM_CUPON_WITHOUT_TC_LOCAL)
    @Operation(
            summary = "Export register booking item lodger lodging cupon.",
            description = "Export register booking item lodger lodging cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingItemCuponPdfWithout(
            @PathVariable(REGISTER_BOOKING_ITEM_LODGER_LODGING_ID) String idRegisterBookingItem
    ) {
        byte[] reportContent = useCase.exportRegisterBookingLodgerLodgingItemCuponPdf(idRegisterBookingItem, ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName());
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_booking_item_cupon_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_REGISTER_BOOKING)
    @Operation(
            summary = "Export register booking.",
            description = "Export register booking."
    )
    public ResponseEntity<Resource> exportRegisterBookingPdf(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingLodgerPdf(idRegisterBooking);
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
            summary = "Change state register booking lodger.",
            description = "Change state register booking lodger."
    )
    public ApiResponse changeStateRegisterBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String id,
            @RequestBody RegisterBookingChangeStateRequest request) {
        return ApiResponse.success(
                useCase.changeState(id, request)
        );
    }

    @PutMapping(CHANGE_SUPPLIER)
    @Operation(
            summary = "Change supplier register booking lodger.",
            description = "Change supplier register booking lodger."
    )
    public ApiResponse changeSupplierRegisterBooking(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String id,
            @RequestBody RegisterBookingChangeSupplierRequest request) {
        return ApiResponse.success(
                useCase.changeSupplier(id, request)
        );
    }
}
