package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeRoomBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingRoomUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterBookingRoomController.REGISTER_BOOKING_ROOM;

@Slf4j
@Tag(name = "Booking - Register Booking Room", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_BOOKING_ROOM)
public class RegisterBookingRoomController {

    public static final String REGISTER_BOOKING_ROOM = "/register-booking-rooms";

    public static final String REGISTER_BOOKING_LODGING_ID = "register_booking_lodging_id";
    public static final String REGISTER_BOOKING_LODGER_ID = "register_booking_lodger_id";
    public static final String REGISTER_BOOKING_GLAMPING_ID = "register_booking_glamping_id";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-swb";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-twin";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_MAT = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-mat";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TRP = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-trp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_CDP = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-cdp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_QTP = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-qtp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SXTP = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-sxtp";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_TC = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-swb-tc";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_TC = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-twin-tc";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_TC_LOCAL = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-swb-tc-local";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_TC_LOCAL = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-twin-tc-local";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_GUIDE = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-swb-guide";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_GUIDE = "/lodging/{" + REGISTER_BOOKING_LODGING_ID + "}/find-all-by-type-room-twin-guide";


    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-swb";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-twin";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_MAT = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-mat";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TRP = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-trp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_CDP = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-cdp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_QTP = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-qtp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SXTP = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-sxtp";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_TC = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-swb-tc";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_TC = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-twin-tc";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_TC_LOCAL = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-swb-tc-local";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_TC_LOCAL = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-twin-tc-local";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_GUIDE = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-swb-guide";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_GUIDE = "/lodger/{" + REGISTER_BOOKING_LODGER_ID + "}/find-all-by-type-room-twin-guide";


    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-swb";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-twin";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_MAT = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-mat";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TRP = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-trp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_CDP = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-cdp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_QTP = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-qtp";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SXTP = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-sxtp";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB_TC = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-swb-tc";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN_TC = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-twin-tc";

    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB_TC_LOCAL = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-swb-tc-local";
    public static final String FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN_TC_LOCAL = "/glamping/{" + REGISTER_BOOKING_GLAMPING_ID + "}/find-all-by-type-room-twin-tc-local";


    @Autowired
    private RegisterBookingRoomUseCase useCase;

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room swb.",
            description = "Find all register booking room by register booking lodging and type room swb."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomSwb(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room twin.",
            description = "Find all register booking room by register booking lodging and type room twin."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomTwin(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_MAT)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room mat.",
            description = "Find all register booking room by register booking lodging and type room mat."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomMat(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.MAT.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TRP)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room trp.",
            description = "Find all register booking room by register booking lodging and type room trp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomTrp(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TRP.getName())
        );
    }


    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_CDP)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room cdp.",
            description = "Find all register booking room by register booking lodging and type room cdp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomCdp(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.CDP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_QTP)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room qtp.",
            description = "Find all register booking room by register booking lodging and type room qtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomQtp(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.QTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SXTP)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room sxtp.",
            description = "Find all register booking room by register booking lodging and type room sxtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomSxtp(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SXTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_TC)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room swb tc.",
            description = "Find all register booking room by register booking lodging and type room swb tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomSwbTc(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_TC)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room twin tc.",
            description = "Find all register booking room by register booking lodging and type room twin tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomTwinTc(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room swb tc local.",
            description = "Find all register booking room by register booking lodging and type room swb tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomSwbTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room twin tc local.",
            description = "Find all register booking room by register booking lodging and type room twin tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomTwinTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_SWB_GUIDE)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room swb guide.",
            description = "Find all register booking room by register booking lodging and type room swb guide."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomSwbGuide(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_GUIDE.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGING_TYPE_ROOM_TWIN_GUIDE)
    @Operation(
            summary = "Find all register booking room by register booking lodging and type room twin guide.",
            description = "Find all register booking room by register booking lodging and type room twin guide."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgingAndTypeRoomTwinGuide(
            @PathVariable(REGISTER_BOOKING_LODGING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_GUIDE.getName())
        );
    }


    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room swb.",
            description = "Find all register booking room by register booking lodger and type room swb."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomSwb(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room twin.",
            description = "Find all register booking room by register booking lodger and type room twin."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomTwin(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_MAT)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room mat.",
            description = "Find all register booking room by register booking lodger and type room mat."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomMat(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.MAT.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TRP)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room trp.",
            description = "Find all register booking room by register booking lodger and type room trp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomTrp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TRP.getName())
        );
    }


    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_CDP)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room cdp.",
            description = "Find all register booking room by register booking lodger and type room cdp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomCdp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.CDP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_QTP)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room qtp.",
            description = "Find all register booking room by register booking lodger and type room qtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomQtp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.QTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SXTP)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room sxtp.",
            description = "Find all register booking room by register booking lodger and type room sxtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomSxtp(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SXTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_TC)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room swb tc.",
            description = "Find all register booking room by register booking lodger and type room swb tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomSwbTc(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_TC)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room twin tc.",
            description = "Find all register booking room by register booking lodger and type room twin tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomTwinTc(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room swb tc local.",
            description = "Find all register booking room by register booking lodger and type room swb tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomSwbTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room twin tc local.",
            description = "Find all register booking room by register booking lodger and type room twin tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomTwinTcLocal(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_SWB_GUIDE)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room swb guide.",
            description = "Find all register booking room by register booking lodger and type room swb guide."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomSwbGuide(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_GUIDE.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_LODGER_TYPE_ROOM_TWIN_GUIDE)
    @Operation(
            summary = "Find all register booking room by register booking lodger and type room twin guide.",
            description = "Find all register booking room by register booking lodger and type room twin guide."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingLodgerAndTypeRoomTwinGuide(
            @PathVariable(REGISTER_BOOKING_LODGER_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingLodgerAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_GUIDE.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room swb.",
            description = "Find all register booking room by register booking glamping and type room swb."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomSwb(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room twin.",
            description = "Find all register booking room by register booking glamping and type room twin."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomTwin(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_MAT)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room mat.",
            description = "Find all register booking room by register booking glamping and type room mat."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomMat(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.MAT.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TRP)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room trp.",
            description = "Find all register booking room by register booking glamping and type room trp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomTrp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TRP.getName())
        );
    }


    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_CDP)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room cdp.",
            description = "Find all register booking room by register booking glamping and type room cdp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomCdp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.CDP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_QTP)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room qtp.",
            description = "Find all register booking room by register booking glamping and type room qtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomQtp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.QTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SXTP)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room sxtp.",
            description = "Find all register booking room by register booking glamping and type room sxtp."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomSxtp(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SXTP.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB_TC)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room swb tc.",
            description = "Find all register booking room by register booking glamping and type room swb tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomSwbTc(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN_TC)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room twin tc.",
            description = "Find all register booking room by register booking glamping and type room twin tc."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomTwinTc(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_SWB_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room swb tc local.",
            description = "Find all register booking room by register booking glamping and type room swb tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomSwbTcLocal(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.SWB_TC_LOCAL.getName())
        );
    }

    @GetMapping(FIND_ALL_REGISTER_BOOKING_ROOM_BY_GLAMPING_TYPE_ROOM_TWIN_TC_LOCAL)
    @Operation(
            summary = "Find all register booking room by register booking glamping and type room twin tc local.",
            description = "Find all register booking room by register booking glamping and type room twin tc local."
    )
    public ApiResponse findAllRegisterBookingRoomByRegisterBookingGlampingAndTypeRoomTwinTcLocal(
            @PathVariable(REGISTER_BOOKING_GLAMPING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.searchAllByBookingGlampingAndTypeRoomBooking(idRegisterBooking, TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())
        );
    }
}
