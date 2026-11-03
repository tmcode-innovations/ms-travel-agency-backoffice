package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.ExportCuponTypePassengerBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterGroupUseCase;
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

import java.util.List;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.booking.RegisterGroupController.REGISTER_GROUP;

@Slf4j
@Tag(name = "Booking - Register Group", description = "Booking Module")
@RestController
@RequestMapping(REGISTER_GROUP)
public class RegisterGroupController {

    public static final String REGISTER_GROUP = "/register-groups";

    public static final String REGISTER_GROUP_ID = "register_group_id";
    public static final String REGISTER_GROUP_IDENTIFY = "register_group_identify";
    public static final String PASSENGER_GROUP_ID = "passenger_group_id";
    public static final String ITINERARY_ITEM_ID = "itinerary_item_id";
    public static final String ITINERARY_DATE_ID = "itinerary_date_id";
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String REGISTER_BOOKING_ID = "register_booking_id";
    public static final String TOUR_ID = "tour_id";
    public static final String COST_TOUR_ID = "cost_tour_id";
    public static final String COST_TOUR_ITEM_ID = "cost_tour_item_id";
    public static final String COST_TOUR_SERVICE_ITEM_ID = "cost_tour_service_item_id";
    public static final String SUPPLIER_TYPE_ID = "supplier_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_GROUP_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_GROUP_ID + "}";
    public static final String OPENED = "/opened-v0/{" + REGISTER_GROUP_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_GROUP_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";
    public static final String FIND_ALL_SIMPLE_APERTURE = "/find-all-simple-aperture";
    public static final String FIND_ALL_SIMPLE_APERTURE_DOCUMENT_FINANCE = "/find-all-simple-aperture-document-finance";
    public static final String FIND_ALL_SIMPLE_APERTURE_DOCUMENT_FINANCE_BY_GUIDE_TRANSFER_DRIVE = "/find-all-simple-aperture-document-finance-by-guide-transfer-drive/{" + "guide_id" + "}";

    public static final String FIND_ALL_SIMPLE_GUIDE_TRANSFER_DRIVE_REGISTER_GROUP = "/find-all-simple-guide-transfer-drive-register-group";
    public static final String FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS = "/find-all-simple-aperture-voucher-ingress";
    public static final String FIND_ALL_SIMPLE_APERTURE_VOUCHER_EGRESS = "/find-all-simple-aperture-voucher-egress";
    public static final String FIND_ALL_SIMPLE_APERTURE_REPORT = "/find-all-simple-aperture-report";
    public static final String FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS_BY_CUSTOMER = "/find-all-simple-aperture-voucher-ingress-by-customer/{" + CUSTOMER_ID + "}";
    public static final String FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS_BY_SUPPLIER = "/find-all-simple-aperture-voucher-ingress-by-supplier/{" + SUPPLIER_ID + "}";

    public static final String ADD_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/passenger-group/add";
    public static final String EDIT_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/passenger-group/edit/{" + PASSENGER_GROUP_ID + "}";
    public static final String DELETE_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/passenger-group/delete/{" + PASSENGER_GROUP_ID + "}";
    public static final String FIND_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/passenger-group/find/{" + PASSENGER_GROUP_ID + "}";
    public static final String FIND_ALL_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/passenger-group/find-all";

    public static final String ADD_ITINERARY_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary/add";
    public static final String ADD_ITINERARY_ITEM_MULTIPLE = "/{" + REGISTER_GROUP_ID + "}/itinerary-multiple/add";
    public static final String ADD_ITINERARY_ITEM_ADVANCED = "/{" + REGISTER_GROUP_ID + "}/itinerary-advanced/add";
    public static final String EDIT_ITINERARY_ITEM_ADVANCED = "/{" + REGISTER_GROUP_ID + "}/itinerary-advanced/edit";
    public static final String ADD_ITINERARY_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary-tour/add";
    public static final String ADD_ITINERARY_TOUR_SERVICE_ADDITIONAL_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary-tour-service-additional/add";
    public static final String EDIT_ITINERARY_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary/edit/{" + ITINERARY_ITEM_ID + "}";
    public static final String DELETE_ITINERARY_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary/delete/{" + ITINERARY_ITEM_ID + "}";
    public static final String FIND_ITINERARY_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary/find/{" + ITINERARY_ITEM_ID + "}";
    public static final String FIND_ALL_ITINERARY_ITEM = "/{" + REGISTER_GROUP_ID + "}/itinerary-item/find-all";
    public static final String FIND_ALL_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/find-all";
    public static final String FIND_ALL_ITINERARY_ITEM_MODEL_VIEW = "/{" + REGISTER_GROUP_ID + "}/itinerary-item/find-all-model-view";

    public static final String CHANGE_STATE = "/change-state/{" + REGISTER_GROUP_ID + "}";
    public static final String RESEND_QUOTATION_TO_BOOKING = "/resend-quotation-booking/{" + REGISTER_GROUP_IDENTIFY + "}";

    public static final String EDIT_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/edit/{" + ITINERARY_DATE_ID + "}";
    public static final String FIND_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/find/{" + ITINERARY_DATE_ID + "}";
    public static final String DELETE_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/delete/{" + ITINERARY_DATE_ID + "}";

    public static final String FIND_ALL_TOURS_BY_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/find-all-tours/{" + ITINERARY_DATE_ID + "}";
    public static final String DELETE_TOUR_BY_ITINERARY_DATE = "/{" + REGISTER_GROUP_ID + "}/itinerary-date/{" + ITINERARY_DATE_ID + "}/delete/{" + TOUR_ID + "}";

    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_OPERATOR_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-operator/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_RESTAURANT_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-restaurant/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_LODGING_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-lodging/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_LODGER_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-lodger/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_GLAMPING_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-glamping/find-all";
    public static final String FIND_ALL_TYPE_REGISTER_BOOKING_INSTITUTION_FOR_GENERATE = "/{" + REGISTER_GROUP_ID + "}/register-booking-generate-institution/find-all";

    public static final String GENERATE_REGISTER_BOOKING_LODGING = "/{" + REGISTER_GROUP_ID + "}/register-booking-lodging-generate/{" + SUPPLIER_ID + "}";
    public static final String GENERATE_REGISTER_BOOKING_LODGER = "/{" + REGISTER_GROUP_ID + "}/register-booking-lodger-generate/{" + SUPPLIER_ID + "}";
    public static final String GENERATE_REGISTER_BOOKING_OPERATOR = "/{" + REGISTER_GROUP_ID + "}/register-booking-operator-generate/{" + SUPPLIER_ID + "}";
    public static final String GENERATE_REGISTER_BOOKING_RESTAURANT = "/{" + REGISTER_GROUP_ID + "}/register-booking-restaurant-generate/{" + SUPPLIER_ID + "}";
    public static final String GENERATE_REGISTER_BOOKING_GLAMPING = "/{" + REGISTER_GROUP_ID + "}/register-booking-glamping-generate/{" + SUPPLIER_ID + "}";
    public static final String GENERATE_REGISTER_BOOKING_INSTITUTION = "/{" + REGISTER_GROUP_ID + "}/register-booking-institution-generate/{" + SUPPLIER_ID + "}";

    public static final String EXPORT_PASSENGER_GROUP = "/{" + REGISTER_GROUP_ID + "}/export-passenger-group/pdf";
    public static final String EXPORT_PASSENGER_GROUP_XLS = "/{" + REGISTER_GROUP_ID + "}/export-passenger-group/xls";

    public static final String EXPORT_ALL_CUPON = "/{" + REGISTER_GROUP_ID + "}/export-all-cupon/pdf";

    public static final String EXPORT_ALL_CUPON_ONLY_TC_LOCAL = "/{" + REGISTER_GROUP_ID + "}/export-all-cupon-only-tc-local/pdf";
    public static final String EXPORT_ALL_CUPON_WITHOUT_TC_LOCAL = "/{" + REGISTER_GROUP_ID + "}/export-all-cupon-without-tc-local/pdf";


    public static final String EXPORT_ALL_ITINERARY = "/{" + REGISTER_GROUP_ID + "}/export-all-itinerary/pdf";

    public static final String EXPORT_CANT_BY_RESPONSIBLE = "/export-cant-by-responsible/pdf";

    public static final String FIND_ALL_CUPON_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/all-cupon";

    public static final String FIND_ALL_CUPON_BY_REGISTER_GROUP_ONLY_TC_LOCAL = "/{" + REGISTER_GROUP_ID + "}/all-cupon-only-tc-local";
    public static final String FIND_ALL_CUPON_BY_REGISTER_GROUP_WITHOUT_TC_LOCAL = "/{" + REGISTER_GROUP_ID + "}/all-cupon-without-tc-local";

    public static final String FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_OPERATOR = "/{" + REGISTER_GROUP_ID + "}/passenger-group-operator/not-selected/{" + REGISTER_BOOKING_ID + "}";
    public static final String FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_RESTAURANT = "/{" + REGISTER_GROUP_ID + "}/passenger-group-restaurant/not-selected/{" + REGISTER_BOOKING_ID + "}";
    public static final String FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_LODGING = "/{" + REGISTER_GROUP_ID + "}/passenger-group-lodging/not-selected/{" + REGISTER_BOOKING_ID + "}";
    public static final String FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_GLAMPING = "/{" + REGISTER_GROUP_ID + "}/passenger-group-glamping/not-selected/{" + REGISTER_BOOKING_ID + "}";
    public static final String FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_INSTITUTION = "/{" + REGISTER_GROUP_ID + "}/passenger-group-institution/not-selected/{" + REGISTER_BOOKING_ID + "}";

    public static final String CHANGE_DATE_ITINERARY_DATE_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-date-itinerary-date";
    public static final String CHANGE_DATE_ITINERARY_DAY_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-date-itinerary-day";
    public static final String CHANGE_DATE_ITINERARY_DAY_INIT_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-date-itinerary-day-init";
    public static final String CHANGE_DATE_ITINERARY_DAY_MIDDLE_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-date-itinerary-day-middle";
    public static final String UPDATE_ORDER_ITINERARY_DATE_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/update-order-itinerary-date";
    public static final String UPDATE_ORDER_ITINERARY_ITEM_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/update-order-itinerary-item";
    public static final String UPDATE_ORDER_ITINERARY_DATE_TO_ITEM_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/update-order-itinerary-date-to-item";
    public static final String UPDATE_ORDER_ITINERARY_ITEM_TO_DATE_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/update-order-itinerary-item-to-date";
    public static final String GET_NEW_DATE_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/get-new-date";

    public static final String ADD_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/add";
    public static final String EDIT_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/edit/{" + COST_TOUR_ID + "}";
    public static final String DELETE_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/delete/{" + COST_TOUR_ID + "}";
    public static final String FIND_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/find/{" + COST_TOUR_ID + "}";
    public static final String FIND_ALL_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/find-all";

    public static final String ADD_COST_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/add";
    public static final String EDIT_COST_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/edit/{" + COST_TOUR_ITEM_ID + "}";
    public static final String DELETE_COST_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/delete/{" + COST_TOUR_ITEM_ID + "}";
    public static final String FIND_COST_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/find/{" + COST_TOUR_ITEM_ID + "}";
    public static final String FIND_ALL_COST_TOUR_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/find-all";

    public static final String DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY = "/{" + REGISTER_GROUP_ID + "}/itinerary-cost-tour/{" + TOUR_ID + "}/delete";
    public static final String DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY_AND_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/itinerary-cost-tour/{" + TOUR_ID + "}/{" + COST_TOUR_ID + "}/delete";

    public static final String GENERATE_REGISTER_BOOKING_ALL = "/{" + REGISTER_GROUP_ID + "}/register-booking-all-generate";

    public static final String ADD_COST_TOUR_SERVICE_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/add";
    public static final String EDIT_COST_TOUR_SERVICE_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/edit/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String DELETE_COST_TOUR_SERVICE_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/delete/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String FIND_COST_TOUR_SERVICE_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/find/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String FIND_ALL_COST_TOUR_SERVICE_ITEM = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/find-all";

    public static final String FIND_ALL_ITINERARY_DATE_FROM_COST_TOUR = "/{" + REGISTER_GROUP_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/itinerary-date/find-all";

    public static final String FIND_ALL_SUPPLIER_BY_REGISTER_GROUP_AND_SUPPLIER_TYPE = "/{" + REGISTER_GROUP_ID + "}/{" + SUPPLIER_TYPE_ID + "}/supplier/find-all";

    public static final String FIND_ALL_SERVICE_REQUEST = "/{" + REGISTER_GROUP_ID + "}/service-request/{" + SUPPLIER_ID + "}";
    public static final String FIND_ALL_SERVICE_REQUEST_BY_ID = "/{" + REGISTER_GROUP_ID + "}/service-request/itinerary-item/{" + ITINERARY_ITEM_ID + "}";


    public static final String CHANGE_STATE_ALL_BOOKING_ANNULLED = "/change-state-all-booking-annulled/{" + REGISTER_GROUP_ID + "}";

    public static final String CHANGE_CANT_PAX_ADULT_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-cant-pax-adult";
    public static final String CHANGE_CANT_PAX_CHILD_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-cant-pax-child";
    public static final String CHANGE_CANT_TC_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-cant-tc";
    public static final String CHANGE_CANT_TC_LOCAL_BY_REGISTER_GROUP = "/{" + REGISTER_GROUP_ID + "}/change-cant-tc-local";

    public static final String FIND_ITINERARY_ADVANCED = "/find-itinerary-advanced/{" + REGISTER_GROUP_ID + "}";

    public static final String FIND_ALL_REGISTER_GROUP_NOT_IN_COST_SHEET_TWO = "/find-all-register-group-not-in-cost-sheet-two";

    public static final String FIND_ALL_FOR_COST_SHEET_TWO = "/find-all-for-cost-sheet-two";

    public static final String FIND_DATA_USER_UPDATED = "/{" + REGISTER_GROUP_ID + "}/find-data-user-updated";

    public static final String FIND_ITINERARY_ITEM_VOUCHER_EGRESS = "/{" + REGISTER_GROUP_ID + "}/itinerary-voucher-egress/find/{" + ITINERARY_ITEM_ID + "}";

    @Autowired
    private RegisterGroupUseCase useCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create itinerary item.",
            description = "Create itinerary item."
    )
    public ApiResponse create(
            @RequestBody RegisterGroupCreateRequest request) {
        return ApiResponse.success(
                useCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register group by id.",
            description = "Find register group by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_GROUP_ID) String id) {
        return ApiResponse.success(
                useCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register groups.",
            description = "Find all register groups."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                useCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register group.",
            description = "Edit register group."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody RegisterGroupUpdateRequest request) {
        return ApiResponse.success(
                useCase.edit(id, request)
        );
    }

    @PutMapping(OPENED)
    @Operation(
            summary = "Opened register group.",
            description = "Opened register group."
    )
    public ApiResponse opened_v0(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody RegisterGroupUpdateRequest request) {
        return ApiResponse.success(
                useCase.opened_v0(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register group.",
            description = "Delete register group."
    )
    public ApiResponse deleteById(
           @PathVariable(REGISTER_GROUP_ID) String id) {
        useCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register groups.",
            description = "Search all register groups."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterGroupSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                useCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register groups.",
            description = "Search filter paginate register groups."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterGroupSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_PASSENGER_GROUP)
    @Operation(
            summary = "add passenger group.",
            description = "Add passenger group."
    )
    public ApiResponse addPassengerGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody PassengerGroupCreateRequest request) {
        return ApiResponse.success(
                useCase.addPassengerGroup(request, idRegisterGroup)
        );
    }

    @PutMapping(EDIT_PASSENGER_GROUP)
    @Operation(
            summary = "Edit passenger .",
            description = "Edit passenger group."
    )
    public ApiResponse editPassengerGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(PASSENGER_GROUP_ID) String idPassengerGroup,
            @RequestBody PassengerGroupUpdateRequest request) {
        return ApiResponse.success(
                useCase.editPassengerGroup(idPassengerGroup, request, idRegisterGroup)
        );
    }

    @DeleteMapping(DELETE_PASSENGER_GROUP)
    @Operation(
            summary = "Delete passenger group.",
            description = "Delete passenger group."
    )
    public ApiResponse deletePassengerGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(PASSENGER_GROUP_ID) String idPassengerGroup
    ) {
        useCase.deletePassengerGroup(idPassengerGroup, idRegisterGroup);
        return ApiResponse.success();
    }

    @GetMapping(FIND_PASSENGER_GROUP)
    @Operation(
            summary = "Find passenger group by id and quotation.",
            description = "Find passenger group by id and quotation."
    )
    public ApiResponse findPassengerByIdAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(PASSENGER_GROUP_ID) String idPassengerGroup
    ) {
        return ApiResponse.success(
                useCase.findByIdAndRegisterGroup(idPassengerGroup, idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER_GROUP)
    @Operation(
            summary = "Find all passenger group by quotation.",
            description = "Find all passenger group by quotation."
    )
    public ApiResponse findAllPassengerBuRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.findAllPassengerGroupByRegisterGroup(idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_ITEM)
    @Operation(
            summary = "Find all itinerary item by register group.",
            description = "Find all itinerary item by register group."
    )
    public ApiResponse findAllItineraryItemBuRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.findAllItineraryItemByRegisterGroup(idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_DATE)
    @Operation(
            summary = "Find all itinerary date by register group.",
            description = "Find all itinerary date by register group."
    )
    public ApiResponse findAllItineraryDateBuRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.findAllItineraryDateByRegisterGroup(idRegisterGroup)
        );
    }

    @PostMapping(ADD_ITINERARY_ITEM)
    @Operation(
            summary = "add itinerary item.",
            description = "Add itinerary item."
    )
    public ApiResponse addItineraryItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryItemCreateRequest request) {
        return ApiResponse.success(
                useCase.addItinerary(request, idRegisterGroup)
        );
    }

    @PostMapping(ADD_ITINERARY_ITEM_MULTIPLE)
    @Operation(
            summary = "add itinerary item multiple.",
            description = "Add itinerary item multiple."
    )
    public ApiResponse addItineraryItemMultiple(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryItemCreateRequest request) {
        useCase.addItineraryMultiple(request, idRegisterGroup);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_ITEM_ADVANCED)
    @Operation(
            summary = "add itinerary item advanced.",
            description = "Add itinerary item advanced."
    )
    public ApiResponse addItineraryItemAdvaced(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryServiceAdvancedCreateRequest request) {
        useCase.addItineraryAdvanced(request, idRegisterGroup);
        return ApiResponse.success();
    }

    @PutMapping(EDIT_ITINERARY_ITEM_ADVANCED)
    @Operation(
            summary = "Edit itinerary item advanced.",
            description = "Edit itinerary item advanced."
    )
    public ApiResponse editItineraryItemAdvaced(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryServiceAdvancedUpdateRequest request) {
        useCase.editItineraryAdvanced(request, idRegisterGroup);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_TOUR_ITEM)
    @Operation(
            summary = "add itinerary item.",
            description = "Add itinerary item."
    )
    public ApiResponse addItineraryTourItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryTourItemCreateRequest request) {
        useCase.addItineraryWithTour(request, idRegisterGroup);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_TOUR_SERVICE_ADDITIONAL_ITEM)
    @Operation(
            summary = "add itinerary item tour service additional.",
            description = "Add itinerary item tour service additional."
    )
    public ApiResponse addItineraryTourServiceAdditionalItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody ItineraryCostTourServiceItemCreateRequest request) {
        useCase.addItineraryWithTourServiceAdditional(request, idRegisterGroup);
        return ApiResponse.success();
    }

    @PutMapping(EDIT_ITINERARY_ITEM)
    @Operation(
            summary = "Edit itinerary item.",
            description = "Edit itinerary item."
    )
    public ApiResponse editItineraryItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem,
            @RequestBody ItineraryItemUpdateRequest request) {
        return ApiResponse.success(
                useCase.editItinerary(idItineraryItem, request, idRegisterGroup)
        );
    }

    @DeleteMapping(DELETE_ITINERARY_ITEM)
    @Operation(
            summary = "Delete itinerary item.",
            description = "Delete itinerary item."
    )
    public ApiResponse deleteItineraryItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem
    ) {
        useCase.deleteItinerary(idItineraryItem, idRegisterGroup);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ITINERARY_ITEM)
    @Operation(
            summary = "Find passenger by id and register group.",
            description = "Find passenger by id and register group."
    )
    public ApiResponse findItineraryItemByIdAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem
    ) {
        return ApiResponse.success(
                useCase.findByItineraryItemIdAndRegisterGroup(idItineraryItem, idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_ITEM_MODEL_VIEW)
    @Operation(
            summary = "Find all itinerary item by register group (model view).",
            description = "Find all itinerary item by register group (model view)."
    )
    public ApiResponse findAllItineraryItemByRegisterGroupModelView(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.findAllItineraryDateWithItineraryItemByRegisterGroupModelView(idRegisterGroup)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state register group.",
            description = "Change state register group."
    )
    public ApiResponse changeStateRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody RegisterGroupChangeStateRequest request) {
        return ApiResponse.success(
                useCase.changeState(id, request)
        );
    }

    @PutMapping(RESEND_QUOTATION_TO_BOOKING)
    @Operation(
            summary = "Resend qotation to booking.",
            description = "Resend qotation to booking."
    )
    public ApiResponse resendQuotationToBooking(
            @PathVariable(REGISTER_GROUP_IDENTIFY) String identify) {
        return ApiResponse.success(
                useCase.resendQuotationToBooking(identify)
        );
    }

    @PutMapping(EDIT_ITINERARY_DATE)
    @Operation(
            summary = "Edit itinerary date.",
            description = "Edit itinerary date."
    )
    public ApiResponse editItineraryDate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate,
            @RequestBody ItineraryDateUpdateOnlyNameRequest request) {
        return ApiResponse.success(
                useCase.editItineraryDate(idItineraryDate, request, idRegisterGroup)
        );
    }

    @GetMapping(FIND_ITINERARY_DATE)
    @Operation(
            summary = "Find itinerary date by id and quotation.",
            description = "Find itinerary date by id and quotation."
    )
    public ApiResponse findItineraryDateByIdAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        return ApiResponse.success(
                useCase.findByItineraryDateIdAndRegisterGroup(idItineraryDate, idRegisterGroup)
        );
    }

    @DeleteMapping(DELETE_ITINERARY_DATE)
    @Operation(
            summary = "Delete itinerary date.",
            description = "Delete itinerary date."
    )
    public ApiResponse deleteItineraryDate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        useCase.deleteItineraryDate(idItineraryDate, idRegisterGroup);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ALL_TOURS_BY_ITINERARY_DATE)
    @Operation(
            summary = "Find all tours by itinerary date and register group.",
            description = "Find all tours by itinerary date and register group."
    )
    public ApiResponse findAllToursByItineraryDateByIdAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        return ApiResponse.success(
                useCase.findAllToursByItineraryDate(idRegisterGroup, idItineraryDate)
        );
    }

    @DeleteMapping(DELETE_TOUR_BY_ITINERARY_DATE)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryDate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate,
            @PathVariable(TOUR_ID) String idTour
    ) {
        useCase.removeToursByItineraryDate(idRegisterGroup, idItineraryDate, idTour);
        return ApiResponse.success();
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_FOR_GENERATE)
    @Operation(
            summary = "Find register booking for generate.",
            description = "Find register booking for generate."
    )
    public ApiResponse findAllRegisterBookingGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_OPERATOR_FOR_GENERATE)
    @Operation(
            summary = "Find register booking operator for generate.",
            description = "Find register booking operator for generate."
    )
    public ApiResponse findAllRegisterBookingOperatorGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingOperatorGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_RESTAURANT_FOR_GENERATE)
    @Operation(
            summary = "Find register booking restaurant for generate.",
            description = "Find register booking restaurant for generate."
    )
    public ApiResponse findAllRegisterBookingRestaurantGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingRestaurantGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_LODGING_FOR_GENERATE)
    @Operation(
            summary = "Find register booking lodging for generate.",
            description = "Find register booking lodging for generate."
    )
    public ApiResponse findAllRegisterBookingLodgingGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingLodgingGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_LODGER_FOR_GENERATE)
    @Operation(
            summary = "Find register booking lodger for generate.",
            description = "Find register booking lodger for generate."
    )
    public ApiResponse findAllRegisterBookingLodgerGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingLodgerGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_GLAMPING_FOR_GENERATE)
    @Operation(
            summary = "Find register booking glamping for generate.",
            description = "Find register booking glamping for generate."
    )
    public ApiResponse findAllRegisterBookingGlampingGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingGlampingGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_TYPE_REGISTER_BOOKING_INSTITUTION_FOR_GENERATE)
    @Operation(
            summary = "Find register booking institution for generate.",
            description = "Find register booking institution for generate."
    )
    public ApiResponse findAllRegisterBookingInstitutionGenerate(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapSearchRegisterBookingRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterBookingInstitutionGenerate(idRegisterGroup, request)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_LODGING)
    @Operation(
            summary = "Generate register booking lodging.",
            description = "Generate register booking lodging."
    )
    public ApiResponse generateRegisterBookingLodging(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingLodging(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_LODGER)
    @Operation(
            summary = "Generate register booking lodger.",
            description = "Generate register booking lodger."
    )
    public ApiResponse generateRegisterBookingLodger(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingLodger(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_OPERATOR)
    @Operation(
            summary = "Generate register booking operator.",
            description = "Generate register booking operator."
    )
    public ApiResponse generateRegisterBookingOperator(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingOperator(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_RESTAURANT)
    @Operation(
            summary = "Generate register booking restaurant.",
            description = "Generate register booking restaurant."
    )
    public ApiResponse generateRegisterBookingRestaurant(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingRestaurant(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_GLAMPING)
    @Operation(
            summary = "Generate register booking glamping.",
            description = "Generate register booking glamping."
    )
    public ApiResponse generateRegisterBookingGlamping(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingGlamping(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_INSTITUTION)
    @Operation(
            summary = "Generate register booking institution.",
            description = "Generate register booking institution."
    )
    public ApiResponse generateRegisterBookingInstitution(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.generateRegisterBookingInstitution(idRegisterGroup, idSupplier)
        );
    }

    @GetMapping(EXPORT_PASSENGER_GROUP)
    @Operation(
            summary = "Export passenger group.",
            description = "Export passenger group."
    )
    public ResponseEntity<Resource> exportPassengerGroupPdf(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        byte[] reportContent = useCase.exportPassengerGroupPdf(idRegisterGroup);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("passenger_register_group_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_PASSENGER_GROUP_XLS)
    @Operation(
            summary = "Export passenger group xls.",
            description = "Export passenger group xls."
    )
    public ResponseEntity<Resource> exportPassengerGroupXls(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        byte[] reportContent = useCase.exportPassengerGroupXls(idRegisterGroup);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("passenger_register_group_report.xlsx").build().toString())
                .body(resource);
    }

    @PostMapping(FIND_ALL_CUPON_BY_REGISTER_GROUP)
    @Operation(
            summary = "Find all cupon by register group.",
            description = "Find all cupon by register group."
    )
    public ApiResponse findAllCuponByRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody RegisterGroupAllCuponSearchRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllCuponByRegisterGroup(idRegisterGroup, request)
        );
    }

    @PostMapping(FIND_ALL_CUPON_BY_REGISTER_GROUP_ONLY_TC_LOCAL)
    @Operation(
            summary = "Find all cupon by register group.",
            description = "Find all cupon by register group."
    )
    public ApiResponse findAllCuponByRegisterGroupOnlyTcLocal(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody RegisterGroupAllCuponSearchRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllCuponByRegisterGroup(idRegisterGroup, request, ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName())
        );
    }

    @PostMapping(FIND_ALL_CUPON_BY_REGISTER_GROUP_WITHOUT_TC_LOCAL)
    @Operation(
            summary = "Find all cupon by register group.",
            description = "Find all cupon by register group."
    )
    public ApiResponse findAllCuponByRegisterGroupWithoutTcLocal(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody RegisterGroupAllCuponSearchRequest request
    ) {
        return ApiResponse.success(
                useCase.findAllCuponByRegisterGroup(idRegisterGroup, request, ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName())
        );
    }

    @PostMapping(EXPORT_ALL_CUPON)
    @Operation(
            summary = "Export all cupon.",
            description = "Export all cupon."
    )
    public ResponseEntity<Resource> exportAllCuponPdf(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapCuponAllRequest request
    ) {
        byte[] reportContent = useCase.exportAllCuponPdf(idRegisterGroup, request);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("all_cupon_in_group_report.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_ALL_CUPON_ONLY_TC_LOCAL)
    @Operation(
            summary = "Export all cupon.",
            description = "Export all cupon."
    )
    public ResponseEntity<Resource> exportAllCuponPdfOnlyTcLocal(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapCuponAllRequest request
    ) {
        byte[] reportContent = useCase.exportAllCuponPdf(idRegisterGroup, request, ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName());
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("all_cupon_in_group_report.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_ALL_CUPON_WITHOUT_TC_LOCAL)
    @Operation(
            summary = "Export all cupon.",
            description = "Export all cupon."
    )
    public ResponseEntity<Resource> exportAllCuponPdfWithoutTcLocal(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody WrapCuponAllRequest request
    ) {
        byte[] reportContent = useCase.exportAllCuponPdf(idRegisterGroup, request, ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName());
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("all_cupon_in_group_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_ALL_ITINERARY)
    @Operation(
            summary = "Export all itinerary.",
            description = "Export all itinerary."
    )
    public ResponseEntity<Resource> exportAllItineraryPdf(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        byte[] reportContent = useCase.exportAllItineraryPdf(idRegisterGroup);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("itinerary_group_report.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_CANT_BY_RESPONSIBLE)
    @Operation(
            summary = "Export register group cant by responsible.",
            description = "Export register group cant by responsible."
    )
    public ResponseEntity<Resource> exportCantByResponsiblePdf(
            @RequestBody RegisterGroupSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.exportCantByResponsiblePdf(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("register_group_cant_by_responsible.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_OPERATOR)
    @Operation(
            summary = "Find not selected passenger group by register group and register bookng operator.",
            description = "Find not selected passenger group by register group and register bookng operator."
    )
    public ApiResponse findNotSelectedPassengerBuRegisterGroupAndRegisterBookingOperator(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findNotSelectedPassengerGroupByRegisterGroupAndBookingOperator(idRegisterGroup, idRegisterBooking)
        );
    }

    @GetMapping(FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_RESTAURANT)
    @Operation(
            summary = "Find not selected passenger group by register group and register bookng restaurant.",
            description = "Find not selected passenger group by register group and register bookng restaurant."
    )
    public ApiResponse findNotSelectedPassengerBuRegisterGroupAndRegisterBookingRestaurant(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findNotSelectedPassengerGroupByRegisterGroupAndBookingRestaurant(idRegisterGroup, idRegisterBooking)
        );
    }

    @GetMapping(FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_LODGING)
    @Operation(
            summary = "Find not selected passenger group by register group and register bookng lodging.",
            description = "Find not selected passenger group by register group and register bookng lodging."
    )
    public ApiResponse findNotSelectedPassengerBuRegisterGroupAndRegisterBookingLodging(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findNotSelectedPassengerGroupByRegisterGroupAndBookingLodging(idRegisterGroup, idRegisterBooking)
        );
    }

    @GetMapping(FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_GLAMPING)
    @Operation(
            summary = "Find not selected passenger group by register group and register bookng glamping.",
            description = "Find not selected passenger group by register group and register bookng glamping."
    )
    public ApiResponse findNotSelectedPassengerBuRegisterGroupAndRegisterBookingGlamping(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findNotSelectedPassengerGroupByRegisterGroupAndBookingGlamping(idRegisterGroup, idRegisterBooking)
        );
    }

    @GetMapping(FIND_NOT_SELECTED_PASSENGER_GROUP_BY_BOOKING_INSTITUTION)
    @Operation(
            summary = "Find not selected passenger group by register group and register bookng institution.",
            description = "Find not selected passenger group by register group and register bookng institution."
    )
    public ApiResponse findNotSelectedPassengerBuRegisterGroupAndRegisterBookingInstitution(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_ID) String idRegisterBooking
    ) {
        return ApiResponse.success(
                useCase.findNotSelectedPassengerGroupByRegisterGroupAndBookingInstitution(idRegisterGroup, idRegisterBooking)
        );
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DATE_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change date itinerary date register group.",
            description = "Change date itinerary date register group."
    )
    public ApiResponse changeDateItineraryDate(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeItineraryDateRequest request) {
        useCase.changeDateItineraryDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change date itinerary register group.",
            description = "Change date itinerary register group."
    )
    public ApiResponse changeDateItineraryDay(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeItineraryDayRequest request) {
        useCase.changeDateItineraryDay(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_INIT_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change days init itinerary register group.",
            description = "Change days init itinerary register group."
    )
    public ApiResponse changeDateItineraryDayInit(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeItineraryDayRequest request) {
        useCase.changeDateItineraryDayInit(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_MIDDLE_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change days middle itinerary register group.",
            description = "Change days middle itinerary register group."
    )
    public ApiResponse changeDateItineraryDayMiddle(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeItineraryDayRequest request) {
        useCase.changeDateItineraryDayMiddle(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_DATE_BY_REGISTER_GROUP)
    @Operation(
            summary = "Update order itinerary date register group.",
            description = "Update order itinerary date register group."
    )
    public ApiResponse updateOrderItineraryDate(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody UpdateOrderItineraryDateRequest request) {
        useCase.updateOrderItineraryDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_ITEM_BY_REGISTER_GROUP)
    @Operation(
            summary = "Update order itinerary item register group.",
            description = "Update order itinerary item register group."
    )
    public ApiResponse updateOrderItineraryItem(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody UpdateOrderItineraryItemRequest request) {
        useCase.updateOrderItineraryItem(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_DATE_TO_ITEM_BY_REGISTER_GROUP)
    @Operation(
            summary = "Update order itinerary date to item register group.",
            description = "Update order itinerary date to item register group."
    )
    public ApiResponse updateOrderItineraryDateToItem(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody UpdateOrderItineraryDateToItemRequest request) {
        useCase.updateOrderItineraryDateToItem(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_ITEM_TO_DATE_BY_REGISTER_GROUP)
    @Operation(
            summary = "Update order itinerary item to date register group.",
            description = "Update order itinerary item to date register group."
    )
    public ApiResponse updateOrderItineraryItemToDate(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody UpdateOrderItineraryItemToDateRequest request) {
        useCase.updateOrderItineraryItemToDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(GET_NEW_DATE_BY_REGISTER_GROUP)
    @Operation(
            summary = "Get new date register group.",
            description = "Get new date register group."
    )
    public ApiResponse getNewDateRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody NewDateRequest request) {
        return ApiResponse.success(
                useCase.getNewDateRegisterGroup(idRegisterGroup, request)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all register groups.",
            description = "Find all register groups."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                useCase.findAllSimple()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE)
    @Operation(
            summary = "Find all register groups aperture.",
            description = "Find all register groups aperture."
    )
    public ApiResponse findAllSimpleAperture(
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleAperture()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_DOCUMENT_FINANCE)
    @Operation(
            summary = "Find all register groups aperture document finance.",
            description = "Find all register groups aperture document finance."
    )
    public ApiResponse findAllSimpleApertureDocumentFinance(
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureDocumentFinance()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_DOCUMENT_FINANCE_BY_GUIDE_TRANSFER_DRIVE)
    @Operation(
            summary = "Find all register groups aperture document finance by guide transfer drive.",
            description = "Find all register groups aperture document finance by guide transfer drive."
    )
    public ApiResponse findAllSimpleApertureDocumentFinanceByGuideTransferDrive(
            @PathVariable("guide_id") String idGuideTransferDrive
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureDocumentFinanceByGuideTransferDrive(idGuideTransferDrive)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_GUIDE_TRANSFER_DRIVE_REGISTER_GROUP)
    @Operation(
            summary = "Find all guide transfer drive register group mapping.",
            description = "Find all guide transfer drive register group mapping."
    )
    public ApiResponse findAllGuideTransferDriveRegisterGroup(
    ) {
        return ApiResponse.success(
                useCase.findAllGuideTransferDriveRegisterGroup()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS)
    @Operation(
            summary = "Find all register groups aperture voucher ingress.",
            description = "Find all register groups aperture voucher ingress."
    )
    public ApiResponse findAllSimpleApertureVoucherIngress(
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureVoucherIngress()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_VOUCHER_EGRESS)
    @Operation(
            summary = "Find all register groups aperture voucher egress.",
            description = "Find all register groups aperture voucher egress."
    )
    public ApiResponse findAllSimpleApertureVoucherEgress(
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureVoucherEgress()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_REPORT)
    @Operation(
            summary = "Find all register groups aperture report.",
            description = "Find all register groups aperture report."
    )
    public ApiResponse findAllSimpleApertureReport(
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureReport()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS_BY_CUSTOMER)
    @Operation(
            summary = "Find all register groups aperture voucher ingress by customer.",
            description = "Find all register groups aperture voucher ingress by customer."
    )
    public ApiResponse findAllSimpleApertureVoucherIngressByCustomer(
            @PathVariable(CUSTOMER_ID) String idCustomer
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureVoucherIngressByCustomer(idCustomer)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_APERTURE_VOUCHER_INGRESS_BY_SUPPLIER)
    @Operation(
            summary = "Find all register groups aperture voucher ingress by supplier.",
            description = "Find all register groups aperture voucher ingress by supplier."
    )
    public ApiResponse findAllSimpleApertureVoucherIngressBySupplier(
            @PathVariable(SUPPLIER_ID) String idSupplier
    ) {
        return ApiResponse.success(
                useCase.findAllSimpleApertureVoucherIngressBySupplier(idSupplier)
        );
    }

    @PostMapping(ADD_COST_TOUR)
    @Operation(
            summary = "add cost tour.",
            description = "Add cost tour."
    )
    public ApiResponse addCostTour(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @RequestBody CostTourCreateRequest request) {
        return ApiResponse.success(
                useCase.addCostTour(request, idRegisterGroup)
        );
    }

    @PutMapping(EDIT_COST_TOUR)
    @Operation(
            summary = "Edit cost tour.",
            description = "Edit cost tour."
    )
    public ApiResponse editCostTour(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourUpdateRequest request) {
        return ApiResponse.success(
                useCase.editCostTour(idCostTour, request, idRegisterGroup)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR)
    @Operation(
            summary = "Delete cost tour.",
            description = "Delete cost tour."
    )
    public ApiResponse deleteCostTour(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        useCase.deleteCostTour(idCostTour, idRegisterGroup);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR)
    @Operation(
            summary = "Find cost tour by id and register group.",
            description = "Find cost tour by id and register group."
    )
    public ApiResponse findCostTourByIdAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                useCase.findByCostTourAndRegisterGroup(idCostTour, idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR)
    @Operation(
            summary = "Find all cost tour by register group.",
            description = "Find all cost tour by register group."
    )
    public ApiResponse findAllCostTourBuRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.findAllCostTourByQuotation(idRegisterGroup)
        );
    }

    @PostMapping(ADD_COST_TOUR_ITEM)
    @Operation(
            summary = "add cost tour item.",
            description = "Add cost tour item."
    )
    public ApiResponse addCostTourItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourItemCreateRequest request) {
        return ApiResponse.success(
                useCase.addCostTourItem(request, idRegisterGroup, idCostTour)
        );
    }

    @PutMapping(EDIT_COST_TOUR_ITEM)
    @Operation(
            summary = "Edit cost tour item.",
            description = "Edit cost tour item."
    )
    public ApiResponse editCostTourItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem,
            @RequestBody CostTourItemUpdateRequest request) {
        return ApiResponse.success(
                useCase.editCostTourItem(idCostTourItem, request, idRegisterGroup, idCostTour)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR_ITEM)
    @Operation(
            summary = "Delete cost tour item.",
            description = "Delete cost tour item."
    )
    public ApiResponse deleteCostTourItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem
    ) {
        useCase.deleteCostTourItem(idCostTourItem, idRegisterGroup, idCostTour);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR_ITEM)
    @Operation(
            summary = "Find cost tour item by id and cost tour and register group.",
            description = "Find cost tour item by id and cost tour and register group."
    )
    public ApiResponse findCostTourByCostTourAndCostTourItemAndRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem
    ) {
        return ApiResponse.success(
                useCase.findByCostTourItemAndRegisterGroupAndCostTour(idCostTourItem, idRegisterGroup, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR_ITEM)
    @Operation(
            summary = "Find all cost tour item by cost tour and register group.",
            description = "Find all cost tour item by cost tour and register group."
    )
    public ApiResponse findAllCostTourBuRegisterGroup(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                useCase.findAllCostTourItemByRegisterGroupAndCostTour(idRegisterGroup, idCostTour)
        );
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryAndCost(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(TOUR_ID) String idTour
    ) {
        useCase.removeToursAndCostTourByRegisterGroup(idRegisterGroup, idTour);
        return ApiResponse.success();
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY_AND_COST_TOUR)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryAndCostTour(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        useCase.removeToursAndCostTourByRegisterGroupAndCostTour(idRegisterGroup, idTour, idCostTour);
        return ApiResponse.success();
    }

    @PostMapping(GENERATE_REGISTER_BOOKING_ALL)
    @Operation(
            summary = "generate register booking all.",
            description = "generate register booking all."
    )
    public ApiResponse generateRegisterBookingAll(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup) {
        useCase.generateAllRegisterBooking(idRegisterGroup);
        return ApiResponse.success();
    }

    @PostMapping(ADD_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "add cost tour service item.",
            description = "Add cost tour service item."
    )
    public ApiResponse addCostTourServiceItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourServiceItemCreateRequest request) {
        return ApiResponse.success(
                useCase.addCostTourServiceItem(request, idRegisterGroup, idCostTour)
        );
    }

    @PutMapping(EDIT_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Edit cost tour service item.",
            description = "Edit cost tour service item."
    )
    public ApiResponse editCostTourServiceItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem,
            @RequestBody CostTourServiceItemUpdateRequest request) {
        return ApiResponse.success(
                useCase.editCostTourServiceItem(idCostTourServiceItem, request, idRegisterGroup, idCostTour)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Delete cost tour service item.",
            description = "Delete cost tour service item."
    )
    public ApiResponse deleteCostTourServiceItem(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem
    ) {
        useCase.deleteCostTourServiceItem(idCostTourServiceItem, idRegisterGroup, idCostTour);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Find cost tour service item by id and cost tour and quotation.",
            description = "Find cost tour service item by id and cost tour and quotation."
    )
    public ApiResponse findCostTourByCostTourAndCostTourServiceItemAndQuotation(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem
    ) {
        return ApiResponse.success(
                useCase.findByCostTourServiceItemAndRegisterGroupAndCostTour(idCostTourServiceItem, idRegisterGroup, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Find all cost tour service item by cost tour and quotation.",
            description = "Find all cost tour service item by cost tour and quotation."
    )
    public ApiResponse findAllCostTourServiceItemBuQuotation(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                useCase.findAllCostTourServiceItemByRegisterGroupAndCostTour(idRegisterGroup, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_DATE_FROM_COST_TOUR)
    @Operation(
            summary = "Find all itinerary date by quotation from cost tour.",
            description = "Find all itinerary date by quotation from cost tour."
    )
    public ApiResponse findAllItineraryDateBuRegisterGroupFromCostTour(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                useCase.findAllItineraryDateByRegisterGroupFromCostTour(idRegisterGroup, idCostTour)
        );
    }

    @PostMapping(FIND_ALL_SUPPLIER_BY_REGISTER_GROUP_AND_SUPPLIER_TYPE)
    @Operation(
            summary = "Find supplier.",
            description = "Find supplier."
    )
    public ApiResponse findAllSupplierByRegisterGroupAndSupplierType(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_TYPE_ID) String idSupplierType
    ) {
        return ApiResponse.success(
                useCase.findAllSupplierByRegisterGroupAndSupplierType(idRegisterGroup, idSupplierType)
        );
    }

    @PostMapping(FIND_ALL_SERVICE_REQUEST)
    @Operation(
            summary = "Find all service request.",
            description = "Find all service request."
    )
    public ApiResponse findAllServiceRequest(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(SUPPLIER_ID) String idSupplier) {
        return ApiResponse.success(
                useCase.findAllServiceRequest(idRegisterGroup, idSupplier)
        );
    }

    @PostMapping(FIND_ALL_SERVICE_REQUEST_BY_ID)
    @Operation(
            summary = "Find all service request by id.",
            description = "Find all service request by id."
    )
    public ApiResponse findAllServiceRequestById(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem) {
        return ApiResponse.success(
                useCase.findAllServiceRequestByItineraryItem(idRegisterGroup, idItineraryItem)
        );
    }

    @PutMapping(CHANGE_STATE_ALL_BOOKING_ANNULLED)
    @Operation(
            summary = "Change all state booking to annulled.",
            description = "Change all state booking to annulled."
    )
    public ApiResponse changeStateAllBookingToAnnulled(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        useCase.changeStateAllBookingToAnnulled(idRegisterGroup);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_CANT_PAX_ADULT_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change cant pax adult register group.",
            description = "Change cant pax adult register group."
    )
    public ApiResponse changeCantPaxAdult(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeCantPaxRequest request) {
        useCase.changeCantPaxAdult(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_CANT_PAX_CHILD_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change cant pax child register group.",
            description = "Change cant pax child register group."
    )
    public ApiResponse changeCantPaxChild(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeCantPaxRequest request) {
        useCase.changeCantPaxChild(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_CANT_TC_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change cant tc register group.",
            description = "Change cant tc register group."
    )
    public ApiResponse changeCantTc(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeCantPaxRequest request) {
        useCase.changeCantTc(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_CANT_TC_LOCAL_BY_REGISTER_GROUP)
    @Operation(
            summary = "Change cant tc local register group.",
            description = "Change cant tc local register group."
    )
    public ApiResponse changeCantTcLocal(
            @PathVariable(REGISTER_GROUP_ID) String id,
            @RequestBody ChangeCantPaxRequest request) {
        useCase.changeCantTcLocal(id, request);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ITINERARY_ADVANCED)
    @Operation(
            summary = "Find itinerary advanced.",
            description = "Find itinerary advanced."
    )
    public ApiResponse getItineraryAdvancedToEdit(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup
    ) {
        return ApiResponse.success(
                useCase.getItineraryAdvancedToEdit(idRegisterGroup)
        );
    }

    @GetMapping(FIND_ALL_REGISTER_GROUP_NOT_IN_COST_SHEET_TWO)
    @Operation(
            summary = "Find all register group not in cost sheet two.",
            description = "Find all register group not in cost sheet two."
    )
    public ApiResponse findAllRegisterGroupNotInCostSheetTwo() {
        return ApiResponse.success(
                useCase.findAllRegisterGroupNotInCostSheetTwo()
        );
    }

    @GetMapping(FIND_ALL_FOR_COST_SHEET_TWO)
    @Operation(
            summary = "Find all for cost sheet two.",
            description = "Find all for cost sheet two."
    )
    public ApiResponse findAllForCostSheetTwo(
    ) {
        return ApiResponse.success(
                useCase.findAllRegisterGroupForCostSheetTwo()
        );
    }

    @GetMapping(FIND_DATA_USER_UPDATED)
    @Operation(
            summary = "Find data user updated by register group.",
            description = "Find data user updated by register group."
    )
    public ApiResponse findDataUserUpdated(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup) {
        return ApiResponse.success(
                useCase.getRegisterGroupModificationUserData(idRegisterGroup)
        );
    }

    @GetMapping(FIND_ITINERARY_ITEM_VOUCHER_EGRESS)
    @Operation(
            summary = "Find passenger by id and register group.",
            description = "Find passenger by id and register group."
    )
    public ApiResponse findItineraryItemByIdAndRegisterGroupVoucherEgress(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem
    ) {
        return ApiResponse.success(
                useCase.findByItineraryItemIdAndRegisterGroupVoucherEgress(idItineraryItem, idRegisterGroup)
        );
    }
}
