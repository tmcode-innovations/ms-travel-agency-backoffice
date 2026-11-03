package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeRoomBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingGlampingUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingGlampingController.REGISTER_BOOKING_GLAMPING;

@Slf4j
@Tag(name = "Booking - Register Booking Glamping", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_GLAMPING)
public class RegisterBookingGlampingController {

    public static final String REGISTER_BOOKING_GLAMPING = "/register-booking-glampings";

    public static final String REGISTER_BOOKING_GLAMPING_ID = "register_booking_glamping_id";
    public static final String REGISTER_BOOKING_ITEM_ID = "register_booking_item_id";
    public static final String PASSENGER_BOOKING_ID = "passenger_booking_id";
    public static final String PASSENGER_BOOKING_ROOM = "passenger_booking_room";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_BOOKING_GLAMPING_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_BOOKING_GLAMPING_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_BOOKING_GLAMPING_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_BOOKING_ITEM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/booking-item/add";
    public static final String EDIT_BOOKING_ITEM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/booking-item/edit/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String DELETE_BOOKING_ITEM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/booking-item/delete/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String FIND_BOOKING_ITEM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/booking-item/find/{" + REGISTER_BOOKING_ITEM_ID + "}";
    public static final String FIND_ALL_BOOKING_ITEM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/booking-item/find-all";

    public static final String ADD_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/add";
    public static final String ADD_PASSENGER_BOOKING_MULTIPLE = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking-multiple/add";
    public static final String DELETE_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/delete/{" + PASSENGER_BOOKING_ID + "}";
    public static final String FIND_ALL_PASSENGER_BOOKING = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room/{" + PASSENGER_BOOKING_ROOM + "}";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-swb";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-twin";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_MAT = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-mat";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TRP = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-trp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_CDP = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-cdp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_QTP = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-qtp";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SXTP = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-sxtp";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-swb-tc";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-twin-tc";

    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC_LOCAL = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-swb-tc-local";
    public static final String FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC_LOCAL = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/passenger-booking/find-all-by-type-room-twin-tc-local";

    public static final String EXPORT_REGISTER_BOOKING_CUPON = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/export-register-booking-cupon/pdf";
    public static final String EXPORT_REGISTER_BOOKING_ITEM_CUPON = "/{" + REGISTER_BOOKING_ITEM_ID + "}/export-register-booking-item-cupon/pdf";
    public static final String EXPORT_REGISTER_BOOKING = "/{" + REGISTER_BOOKING_GLAMPING_ID + "}/export-register-booking/pdf";

    public static final String CHANGE_STATE = "/change-state/{" + REGISTER_BOOKING_GLAMPING_ID + "}";
    public static final String CHANGE_SUPPLIER = "/change-supplier/{" + REGISTER_BOOKING_GLAMPING_ID + "}";

    @Autowired
    private RegisterBookingGlampingUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register booking glamping.",
            description = "Create register booking glamping."
    )
    public ApiResponse create(
            @RequestBody RegisterBookingGlampingCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register booking glamping by id.",
            description = "Find register booking glamping by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register booking glampings.",
            description = "Find all register booking glampings."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register booking glamping.",
            description = "Edit register booking glamping."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String id,
            @RequestBody RegisterBookingGlampingUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register booking glamping.",
            description = "Delete register booking glamping."
    )
    public ApiResponse deleteById(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register booking glampings.",
            description = "Search all register booking glampings."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterBookingGlampingSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register booking glampings.",
            description = "Search filter paginate register booking glampings."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterBookingGlampingSearchFilterRequest searchFilterRequest
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
            @RequestBody RegisterBookingItemGlampingCreateRequest request) {
        return ApiResponse.success(
                useCase.addRegisterBookingItemGlamping(request, idRegisterBooking)
        );
    }

    @PutMapping(EDIT_BOOKING_ITEM)
    @Operation(
            summary = "Edit booking item .",
            description = "Edit booking item."
    )
    public ApiResponse editBookingItem(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem,
            @RequestBody RegisterBookingItemGlampingUpdateRequest request) {
        return ApiResponse.success(
                useCase.editRegisterBookingItemGlamping(idRegisterBookingItem, request, idRegisterBooking)
        );
    }

    @DeleteMapping(DELETE_BOOKING_ITEM)
    @Operation(
            summary = "Delete booking item.",
            description = "Delete booking item."
    )
    public ApiResponse deleteBookingItem(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem
    ) {
        useCase.deleteRegisterBookingItemGlamping(idRegisterBookingItem, idRegisterBooking);
        return ApiResponse.success();
    }

    @GetMapping(FIND_BOOKING_ITEM)
    @Operation(
            summary = "Find booking item by booking.",
            description = "Find booking item by booking."
    )
    public ApiResponse findBookingItemByIdAndBooking(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingItemGlampingByBooking(idRegisterBooking)
        );
    }

    @PostMapping(ADD_PASSENGER_BOOKING)
    @Operation(
            summary = "add passenger booking.",
            description = "Add passenger booking."
    )
    public ApiResponse addPassengerBooking(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBooking(idRegisterBooking)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM)
    @Operation(
            summary = "Find all passenger booking by register booking and type room.",
            description = "Find all passenger booking by register booking and type room."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoom(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking,
            @PathVariable(PASSENGER_BOOKING_ROOM) String typeRoomBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, typeRoomBooking)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb.",
            description = "Find all passenger booking by register booking and type room swb."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwb(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin.",
            description = "Find all passenger booking by register booking and type room twin."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwin(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_MAT)
    @Operation(
            summary = "Find all passenger booking by register booking and type room mat.",
            description = "Find all passenger booking by register booking and type room mat."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomMat(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.MAT.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TRP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room trp.",
            description = "Find all passenger booking by register booking and type room trp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTrp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TRP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_CDP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room cdp.",
            description = "Find all passenger booking by register booking and type room cdp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomCdp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.CDP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_QTP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room qtp.",
            description = "Find all passenger booking by register booking and type room qtp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomQtp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.QTP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SXTP)
    @Operation(
            summary = "Find all passenger booking by register booking and type room sxtp.",
            description = "Find all passenger booking by register booking and type room sxtp."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSxtp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SXTP.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb tc.",
            description = "Find all passenger booking by register booking and type room swb tc."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwbTc(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin tc.",
            description = "Find all passenger booking by register booking and type room twin tc."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwinTc(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_SWB_TC_LOCAL)
    @Operation(
            summary = "Find all passenger booking by register booking and type room swb tc local.",
            description = "Find all passenger booking by register booking and type room swb tc local."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomSwbTcLocal(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_BOOKING_TYPE_ROOM_TWIN_TC_LOCAL)
    @Operation(
            summary = "Find all passenger booking by register booking and type room twin tc local.",
            description = "Find all passenger booking by register booking and type room twin tc local."
    )
    public ApiResponse findAllPassengerByRegisterBookingAndTypeRoomTwinTcLocal(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())
        );
    }

    @GetMapping(EXPORT_REGISTER_BOOKING_CUPON)
    @Operation(
            summary = "Export register booking cupon.",
            description = "Export register booking cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingCuponPdf(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingGlampingCuponPdf(idRegisterBooking);
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
            summary = "Export register booking item cupon.",
            description = "Export register booking item cupon."
    )
    public ResponseEntity<Resource> exportRegisterBookingItemCuponPdf(
            @PathVariable(REGISTER_BOOKING_ITEM_ID) String idRegisterBookingItem
    ) {
        byte[] reportContent = useCase.exportRegisterBookingGlampingItemCuponPdf(idRegisterBookingItem);
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
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        byte[] reportContent = useCase.exportRegisterBookingGlampingPdf(idRegisterBooking);
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
            summary = "Change state register booking glamping.",
            description = "Change state register booking glamping."
    )
    public ApiResponse changeStateRegisterBooking(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String id,
            @RequestBody RegisterBookingChangeStateRequest request) {
        return ApiResponse.success(
                useCase.changeState(id, request)
        );
    }

    @PutMapping(CHANGE_SUPPLIER)
    @Operation(
            summary = "Change supplier register booking glamping.",
            description = "Change supplier register booking glamping."
    )
    public ApiResponse changeSupplierRegisterBooking(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String id,
            @RequestBody RegisterBookingChangeSupplierRequest request) {
        return ApiResponse.success(
                useCase.changeSupplier(id, request)
        );
    }
}
