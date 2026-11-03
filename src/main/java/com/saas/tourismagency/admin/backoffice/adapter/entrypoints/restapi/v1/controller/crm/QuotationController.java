package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.*;
import com.saas.tourismagency.admin.backoffice.usecase.crm.QuotationUseCase;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm.QuotationController.QUOTATION;

@Slf4j
@Tag(name = "Program and Quotation - Quotation", description = "Program and Quotation Module")
@RestController
@RequestMapping(QUOTATION)
public class QuotationController {

    public static final String QUOTATION = "/quotations";

    public static final String QUOTATION_ID = "quotation_id";
    public static final String QUOTATION_FILE_FILENAME = "quotation_file_filename";
    public static final String PASSENGER_ID = "passenger_id";
    public static final String ITINERARY_ITEM_ID = "itinerary_item_id";
    public static final String ITINERARY_DATE_ID = "itinerary_date_id";
    public static final String FILE_ITINERARY_ID = "file_itinerary_id";
    public static final String FILE_QUOTATION_ID = "file_quotation_id";
    public static final String TOUR_ID = "tour_id";
    public static final String COST_TOUR_ID = "cost_tour_id";
    public static final String COST_TOUR_ITEM_ID = "cost_tour_item_id";
    public static final String COST_TOUR_SERVICE_ITEM_ID = "cost_tour_service_item_id";
    public static final String REGISTER_GROUP_ID = "register_group_id";

    public static final String CREATE = "/create";
    public static final String CREATE_V0 = "/create-v0";
    public static final String FIND_BY = "/find-by/{" + QUOTATION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + QUOTATION_ID + "}";
    public static final String EDIT_V0 = "/edit-v0/{" + QUOTATION_ID + "}";
    public static final String DELETE = "/delete/{" + QUOTATION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_PASSENGER = "/{" + QUOTATION_ID + "}/passenger/add";
    public static final String EDIT_PASSENGER = "/{" + QUOTATION_ID + "}/passenger/edit/{" + PASSENGER_ID + "}";
    public static final String DELETE_PASSENGER = "/{" + QUOTATION_ID + "}/passenger/delete/{" + PASSENGER_ID + "}";
    public static final String FIND_PASSENGER = "/{" + QUOTATION_ID + "}/passenger/find/{" + PASSENGER_ID + "}";
    public static final String FIND_ALL_PASSENGER = "/{" + QUOTATION_ID + "}/passenger/find-all";

    public static final String ADD_ITINERARY_ITEM = "/{" + QUOTATION_ID + "}/itinerary/add";
    public static final String ADD_ITINERARY_ITEM_MULTIPLE = "/{" + QUOTATION_ID + "}/itinerary-multiple/add";
    public static final String ADD_ITINERARY_ITEM_ADVANCED = "/{" + QUOTATION_ID + "}/itinerary-advanced/add";
    public static final String ADD_ITINERARY_TOUR_ITEM = "/{" + QUOTATION_ID + "}/itinerary-tour/add";
    public static final String ADD_ITINERARY_TOUR_SERVICE_ADDITIONAL_ITEM = "/{" + QUOTATION_ID + "}/itinerary-tour-service-additional/add";
    public static final String EDIT_ITINERARY_ITEM = "/{" + QUOTATION_ID + "}/itinerary/edit/{" + ITINERARY_ITEM_ID + "}";
    public static final String DELETE_ITINERARY_ITEM = "/{" + QUOTATION_ID + "}/itinerary/delete/{" + ITINERARY_ITEM_ID + "}";
    public static final String FIND_ITINERARY_ITEM = "/{" + QUOTATION_ID + "}/itinerary/find/{" + ITINERARY_ITEM_ID + "}";
    public static final String FIND_ALL_ITINERARY_ITEM = "/{" + QUOTATION_ID + "}/itinerary-item/find-all";
    public static final String FIND_ALL_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/find-all";
    public static final String FIND_ALL_ITINERARY_ITEM_MODEL_VIEW = "/{" + QUOTATION_ID + "}/itinerary-item/find-all-model-view";

    public static final String CHANGE_STATE = "/change-state/{" + QUOTATION_ID + "}";
    public static final String RESEND_FILE_TO_SALE = "/resend-file-sale/{" + REGISTER_GROUP_ID + "}";

    public static final String EDIT_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/edit/{" + ITINERARY_DATE_ID + "}";
    public static final String FIND_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/find/{" + ITINERARY_DATE_ID + "}";
    public static final String DELETE_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/delete/{" + ITINERARY_DATE_ID + "}";

    public static final String FIND_ALL_TOURS_BY_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/find-all-tours/{" + ITINERARY_DATE_ID + "}";
    public static final String DELETE_TOUR_BY_ITINERARY_DATE = "/{" + QUOTATION_ID + "}/itinerary-date/{" + ITINERARY_DATE_ID + "}/delete/{" + TOUR_ID + "}";

    public static final String DOWNLOAD_QUOTATION_DOCUMENT = "/download-document-quotation/{" + QUOTATION_ID + "}/{"+QUOTATION_FILE_FILENAME+"}";
    public static final String DOWNLOAD_ITINERARY_DOCUMENT = "/download-document-itinerary/{" + QUOTATION_ID + "}/{"+QUOTATION_FILE_FILENAME+"}";

    public static final String FIND_ALL_FILE_ITINERARY = "/{" + QUOTATION_ID + "}/file-itinerary/find-all";
    public static final String FIND_ALL_FILE_QUOTATION = "/{" + QUOTATION_ID + "}/file-quotation/find-all";

    public static final String DOWNLOAD_QUOTATION_FILE = "/download-document-file-quotation/{" + QUOTATION_ID + "}/{"+FILE_QUOTATION_ID+"}/{"+QUOTATION_FILE_FILENAME+"}";
    public static final String DOWNLOAD_ITINERARY_FILE = "/download-document-file-itinerary/{" + QUOTATION_ID + "}/{"+FILE_ITINERARY_ID+"}/{"+QUOTATION_FILE_FILENAME+"}";


    public static final String EXPORT_QUOTATION_TYPE_GURU = "/{" + QUOTATION_ID + "}/export-quotation-type-guru/pdf";

    public static final String CHANGE_DATE_ITINERARY_DATE_BY_QUOTATION = "/{" + QUOTATION_ID + "}/change-date-itinerary-date";
    public static final String CHANGE_DATE_ITINERARY_DAY_BY_QUOTATION = "/{" + QUOTATION_ID + "}/change-date-itinerary-day";
    public static final String CHANGE_DATE_ITINERARY_DAY_INIT_BY_QUOTATION = "/{" + QUOTATION_ID + "}/change-date-itinerary-day-init";
    public static final String CHANGE_DATE_ITINERARY_DAY_MIDDLE_BY_QUOTATION = "/{" + QUOTATION_ID + "}/change-date-itinerary-day-middle";
    public static final String UPDATE_ORDER_ITINERARY_DATE_BY_QUOTATION = "/{" + QUOTATION_ID + "}/update-order-itinerary-date";
    public static final String UPDATE_ORDER_ITINERARY_ITEM_BY_QUOTATION = "/{" + QUOTATION_ID + "}/update-order-itinerary-item";
    public static final String UPDATE_ORDER_ITINERARY_DATE_TO_ITEM_BY_QUOTATION = "/{" + QUOTATION_ID + "}/update-order-itinerary-date-to-item";
    public static final String UPDATE_ORDER_ITINERARY_ITEM_TO_DATE_BY_QUOTATION = "/{" + QUOTATION_ID + "}/update-order-itinerary-item-to-date";
    public static final String GET_NEW_DATE_BY_QUOTATION = "/{" + QUOTATION_ID + "}/get-new-date";
    public static final String FIND_DATA_UPDATED = "/{" + QUOTATION_ID + "}/find-data-updated";
    public static final String FIND_DATA_USER_UPDATED = "/{" + QUOTATION_ID + "}/find-data-user-updated";

    public static final String ADD_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/add";
    public static final String EDIT_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/edit/{" + COST_TOUR_ID + "}";
    public static final String DELETE_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/delete/{" + COST_TOUR_ID + "}";
    public static final String FIND_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/find/{" + COST_TOUR_ID + "}";
    public static final String FIND_ALL_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/find-all";

    public static final String ADD_COST_TOUR_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/add";
    public static final String EDIT_COST_TOUR_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/edit/{" + COST_TOUR_ITEM_ID + "}";
    public static final String DELETE_COST_TOUR_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/delete/{" + COST_TOUR_ITEM_ID + "}";
    public static final String FIND_COST_TOUR_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/find/{" + COST_TOUR_ITEM_ID + "}";
    public static final String FIND_ALL_COST_TOUR_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-item/find-all";

    public static final String DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY = "/{" + QUOTATION_ID + "}/itinerary-cost-tour/{" + TOUR_ID + "}/delete";
    public static final String DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY_AND_COST_TOUR = "/{" + QUOTATION_ID + "}/itinerary-cost-tour/{" + TOUR_ID + "}/{" + COST_TOUR_ID + "}/delete";

    public static final String ADD_COST_TOUR_SERVICE_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/add";
    public static final String EDIT_COST_TOUR_SERVICE_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/edit/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String DELETE_COST_TOUR_SERVICE_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/delete/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String FIND_COST_TOUR_SERVICE_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/find/{" + COST_TOUR_SERVICE_ITEM_ID + "}";
    public static final String FIND_ALL_COST_TOUR_SERVICE_ITEM = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/cost-tour-service-item/find-all";

    public static final String FIND_ALL_ITINERARY_DATE_FROM_COST_TOUR = "/{" + QUOTATION_ID + "}/cost-tour/{" + COST_TOUR_ID + "}/itinerary-date/find-all";

    public static final String UPDATE_PRICE_QUOTATION = "/update-price-quotation/{" + QUOTATION_ID + "}";

    @Autowired
    private QuotationUseCase quotationUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create quotation.",
            description = "Create quotation."
    )
    public ApiResponse create(
            @RequestBody QuotationCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.create(request)
        );
    }

    @PostMapping(
            value = CREATE_V0,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = "application/json;charset=UTF-8"
    )
    @Operation(
            summary = "Create quotation.",
            description = "Create quotation."
    )
    public ApiResponse create_v0(
            @RequestParam(value = "files") MultipartFile[] multipartFile) {
        return ApiResponse.success(quotationUseCase.create_v0(multipartFile));
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find quotation by id.",
            description = "Find quotation by id."
    )
    public ApiResponse findBy(
            @PathVariable(QUOTATION_ID) String id) {
        return ApiResponse.success(
                quotationUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all quotations.",
            description = "Find all quotations."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                quotationUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit quotation.",
            description = "Edit quotation."
    )
    public ApiResponse edit(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody QuotationUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.edit(id, request)
        );
    }

    @PutMapping(value = EDIT_V0, consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = "application/json;charset=UTF-8")
    @Operation(
            summary = "Edit quotation.",
            description = "Edit quotation."
    )
    public ApiResponse edit_v0(
            @PathVariable(QUOTATION_ID) String id,
            @RequestParam(value = "files") MultipartFile[] multipartFile) {
        return ApiResponse.success(
                quotationUseCase.edit_v0(id, multipartFile)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete quotation.",
            description = "Delete quotation."
    )
    public ApiResponse deleteById(
           @PathVariable(QUOTATION_ID) String id) {
        quotationUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate quotations.",
            description = "Search all quotations."
    )
    public ApiResponse searchPaginate(
            @RequestBody QuotationSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                quotationUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate quotations.",
            description = "Search filter paginate quotations."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody QuotationSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                quotationUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_PASSENGER)
    @Operation(
            summary = "add passenger.",
            description = "Add passenger."
    )
    public ApiResponse addPassenger(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody PassengerCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.addPassenger(request, idQuotation)
        );
    }

    @PutMapping(EDIT_PASSENGER)
    @Operation(
            summary = "Edit passenger.",
            description = "Edit passenger."
    )
    public ApiResponse editPassenger(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(PASSENGER_ID) String idPassenger,
            @RequestBody PassengerUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.editPassenger(idPassenger, request, idQuotation)
        );
    }

    @DeleteMapping(DELETE_PASSENGER)
    @Operation(
            summary = "Delete passenger.",
            description = "Delete passenger."
    )
    public ApiResponse deletePassenger(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(PASSENGER_ID) String idPassenger
    ) {
        quotationUseCase.deletePassenger(idPassenger, idQuotation);
        return ApiResponse.success();
    }

    @GetMapping(FIND_PASSENGER)
    @Operation(
            summary = "Find passenger by id and quotation.",
            description = "Find passenger by id and quotation."
    )
    public ApiResponse findPassengerByIdAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(PASSENGER_ID) String idPassenger
    ) {
        return ApiResponse.success(
                quotationUseCase.findByIdAndQuotation(idPassenger, idQuotation)
        );
    }

    @GetMapping(FIND_ALL_PASSENGER)
    @Operation(
            summary = "Find all passenger by quotation.",
            description = "Find all passenger by quotation."
    )
    public ApiResponse findAllPassengerBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllPassengerByQuotation(idQuotation)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_ITEM)
    @Operation(
            summary = "Find all itinerary item by quotation.",
            description = "Find all itinerary item by quotation."
    )
    public ApiResponse findAllItineraryItemBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllItineraryItemByQuotation(idQuotation)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_DATE)
    @Operation(
            summary = "Find all itinerary date by quotation.",
            description = "Find all itinerary date by quotation."
    )
    public ApiResponse findAllItineraryDateBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllItineraryDateByQuotation(idQuotation)
        );
    }

    @PostMapping(ADD_ITINERARY_ITEM)
    @Operation(
            summary = "add itinerary item.",
            description = "Add itinerary item."
    )
    public ApiResponse addItineraryItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody ItineraryItemCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.addItinerary(request, idQuotation)
        );
    }

    @PostMapping(ADD_ITINERARY_ITEM_MULTIPLE)
    @Operation(
            summary = "add itinerary item multiple.",
            description = "Add itinerary item multiple."
    )
    public ApiResponse addItineraryItemMultiple(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody ItineraryItemCreateRequest request) {
        quotationUseCase.addItineraryMultiple(request, idQuotation);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_ITEM_ADVANCED)
    @Operation(
            summary = "add itinerary item advanced.",
            description = "Add itinerary item advanced."
    )
    public ApiResponse addItineraryItemAdvanced(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody ItineraryServiceAdvancedCreateRequest request) {
        quotationUseCase.addItineraryAdvanced(request, idQuotation);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_TOUR_ITEM)
    @Operation(
            summary = "add itinerary item.",
            description = "Add itinerary item."
    )
    public ApiResponse addItineraryTourItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody ItineraryTourItemCreateRequest request) {
        quotationUseCase.addItineraryWithTour(request, idQuotation);
        return ApiResponse.success();
    }

    @PostMapping(ADD_ITINERARY_TOUR_SERVICE_ADDITIONAL_ITEM)
    @Operation(
            summary = "add itinerary item tour service additional.",
            description = "Add itinerary item tour service additional."
    )
    public ApiResponse addItineraryTourServiceAdditionalItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody ItineraryCostTourServiceItemCreateRequest request) {
        quotationUseCase.addItineraryWithTourServiceAdditional(request, idQuotation);
        return ApiResponse.success();
    }

    @PutMapping(EDIT_ITINERARY_ITEM)
    @Operation(
            summary = "Edit itinerary item.",
            description = "Edit itinerary item."
    )
    public ApiResponse editItineraryItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem,
            @RequestBody ItineraryItemUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.editItinerary(idItineraryItem, request, idQuotation)
        );
    }

    @DeleteMapping(DELETE_ITINERARY_ITEM)
    @Operation(
            summary = "Delete itinerary item.",
            description = "Delete itinerary item."
    )
    public ApiResponse deleteItineraryItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem
    ) {
        quotationUseCase.deleteItinerary(idItineraryItem, idQuotation);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ITINERARY_ITEM)
    @Operation(
            summary = "Find passenger by id and quotation.",
            description = "Find passenger by id and quotation."
    )
    public ApiResponse findItineraryItemByIdAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem
    ) {
        return ApiResponse.success(
                quotationUseCase.findByItineraryItemIdAndQuotation(idItineraryItem, idQuotation)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_ITEM_MODEL_VIEW)
    @Operation(
            summary = "Find all itinerary item by quotation (model view).",
            description = "Find all itinerary item by quotation (model view)."
    )
    public ApiResponse findAllItineraryItemBuQuotationModelView(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllItineraryDateWithItineraryItemByQuotationModelView(idQuotation)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state quotation.",
            description = "Change state quotation."
    )
    public ApiResponse changeQuotation(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody QuotationChangeStateRequest request) {
        return ApiResponse.success(
                quotationUseCase.changeState(id, request)
        );
    }

    @PutMapping(RESEND_FILE_TO_SALE)
    @Operation(
            summary = "Resend file to sale.",
            description = "Resend file to sale."
    )
    public ApiResponse resendFileToSale(
            @PathVariable(REGISTER_GROUP_ID) String id) {
        return ApiResponse.success(
                quotationUseCase.resendFileToSale(id)
        );
    }

    @PutMapping(EDIT_ITINERARY_DATE)
    @Operation(
            summary = "Edit itinerary date.",
            description = "Edit itinerary date."
    )
    public ApiResponse editItineraryDate(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate,
            @RequestBody ItineraryDateUpdateOnlyNameRequest request) {
        return ApiResponse.success(
                quotationUseCase.editItineraryDate(idItineraryDate, request, idQuotation)
        );
    }

    @GetMapping(FIND_ITINERARY_DATE)
    @Operation(
            summary = "Find itinerary date by id and quotation.",
            description = "Find itinerary date by id and quotation."
    )
    public ApiResponse findItineraryDateByIdAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        return ApiResponse.success(
                quotationUseCase.findByItineraryDateIdAndQuotation(idItineraryDate, idQuotation)
        );
    }

    @DeleteMapping(DELETE_ITINERARY_DATE)
    @Operation(
            summary = "Delete itinerary date.",
            description = "Delete itinerary date."
    )
    public ApiResponse deleteItineraryDate(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        quotationUseCase.deleteItineraryDate(idItineraryDate, idQuotation);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ALL_TOURS_BY_ITINERARY_DATE)
    @Operation(
            summary = "Find all tours by itinerary date and quotation.",
            description = "Find all tours by itinerary date and quotation."
    )
    public ApiResponse findAllToursByItineraryDateByIdAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllToursByItineraryDate(idQuotation, idItineraryDate)
        );
    }

    @DeleteMapping(DELETE_TOUR_BY_ITINERARY_DATE)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryDate(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(ITINERARY_DATE_ID) String idItineraryDate,
            @PathVariable(TOUR_ID) String idTour
    ) {
        quotationUseCase.removeToursByItineraryDate(idQuotation, idItineraryDate, idTour);
        return ApiResponse.success();
    }

    @GetMapping(value = DOWNLOAD_QUOTATION_DOCUMENT)
    @Operation(summary = "Download quotation document")
    public ResponseEntity<Resource> downloadDocumentQuotation(
            @PathVariable(value = QUOTATION_ID) String quotationId,
            @PathVariable(value = QUOTATION_FILE_FILENAME) String filename) throws IOException {

        ByteArrayResource resource = quotationUseCase.downloadDocumentQuotation(quotationId);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    @GetMapping(value = DOWNLOAD_ITINERARY_DOCUMENT)
    @Operation(summary = "Download itinerary document")
    public ResponseEntity<Resource> downloadDocumentItinerary(
            @PathVariable(value = QUOTATION_ID) String quotationId,
            @PathVariable(value = QUOTATION_FILE_FILENAME) String filename) throws IOException {

        ByteArrayResource resource = quotationUseCase.downloadDocumentItinerary(quotationId);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    @GetMapping(FIND_ALL_FILE_ITINERARY)
    @Operation(
            summary = "Find all file itinerary by quotation.",
            description = "Find all file itinerary by quotation."
    )
    public ApiResponse findAllFileItineraryByQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllFileItineraryByQuotation(idQuotation)
        );
    }

    @GetMapping(FIND_ALL_FILE_QUOTATION)
    @Operation(
            summary = "Find all file quotation by quotation.",
            description = "Find all file quotation by quotation."
    )
    public ApiResponse findAllFileQuotationByQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllFileQuotationByQuotation(idQuotation)
        );
    }

    @GetMapping(value = DOWNLOAD_ITINERARY_FILE)
    @Operation(summary = "Download itinerary file document")
    public ResponseEntity<Resource> downloadDocumentFileItinerary(
            @PathVariable(value = QUOTATION_ID) String quotationId,
            @PathVariable(value = FILE_ITINERARY_ID) String id,
            @PathVariable(value = QUOTATION_FILE_FILENAME) String filename) throws IOException {

        ByteArrayResource resource = quotationUseCase.downloadDocumentQuotationByFileItinerary(quotationId, id);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    @GetMapping(value = DOWNLOAD_QUOTATION_FILE)
    @Operation(summary = "Download itinerary file document")
    public ResponseEntity<Resource> downloadDocumentFileQuotation(
            @PathVariable(value = QUOTATION_ID) String quotationId,
            @PathVariable(value = FILE_QUOTATION_ID) String id,
            @PathVariable(value = QUOTATION_FILE_FILENAME) String filename) throws IOException {

        ByteArrayResource resource = quotationUseCase.downloadDocumentQuotationByFileQuotation(quotationId, id);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_QUOTATION_TYPE_GURU)
    @Operation(
            summary = "Export quotation guru explorer.",
            description = "Export quotation guru explorer."
    )
    public ResponseEntity<Resource> exportQuotationTypeGuruPdf(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        byte[] reportContent = quotationUseCase.exportQuotationTypeGuruPdf(idQuotation);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("quotation_type_guru_report.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DATE_BY_QUOTATION)
    @Operation(
            summary = "Change date itinerary date quotation.",
            description = "Change date itinerary date quotation."
    )
    public ApiResponse changeDateItineraryDate(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody ChangeItineraryDateCrmRequest request) {
        quotationUseCase.changeDateItineraryDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_BY_QUOTATION)
    @Operation(
            summary = "Change days itinerary quotation.",
            description = "Change days itinerary quotation."
    )
    public ApiResponse changeDateItineraryDay(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody ChangeItineraryDayCrmRequest request) {
        quotationUseCase.changeDateItineraryDay(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_INIT_BY_QUOTATION)
    @Operation(
            summary = "Change days init itinerary quotation.",
            description = "Change days init itinerary quotation."
    )
    public ApiResponse changeDateItineraryDayInit(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody ChangeItineraryDayCrmRequest request) {
        quotationUseCase.changeDateItineraryDayInit(id, request);
        return ApiResponse.success();
    }

    @PostMapping(CHANGE_DATE_ITINERARY_DAY_MIDDLE_BY_QUOTATION)
    @Operation(
            summary = "Change days middle itinerary quotation.",
            description = "Change days middle itinerary quotation."
    )
    public ApiResponse changeDateItineraryDayMiddle(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody ChangeItineraryDayCrmRequest request) {
        quotationUseCase.changeDateItineraryDayMiddle(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_DATE_BY_QUOTATION)
    @Operation(
            summary = "Update order itinerary date quotation.",
            description = "Update order itinerary date quotation."
    )
    public ApiResponse updateOrderItineraryDate(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody UpdateOrderItineraryDateCrmRequest request) {
        quotationUseCase.updateOrderItineraryDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_ITEM_BY_QUOTATION)
    @Operation(
            summary = "Update order itinerary item quotation.",
            description = "Update order itinerary item quotation."
    )
    public ApiResponse updateOrderItineraryItem(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody UpdateOrderItineraryItemCrmRequest request) {
        quotationUseCase.updateOrderItineraryItem(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_DATE_TO_ITEM_BY_QUOTATION)
    @Operation(
            summary = "Update order itinerary date to item quotation.",
            description = "Update order itinerary date to item quotation."
    )
    public ApiResponse updateOrderItineraryDateToItem(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody UpdateOrderItineraryDateToItemCrmRequest request) {
        quotationUseCase.updateOrderItineraryDateToItem(id, request);
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_ORDER_ITINERARY_ITEM_TO_DATE_BY_QUOTATION)
    @Operation(
            summary = "Update order itinerary item to date quotation.",
            description = "Update order itinerary item to date quotation."
    )
    public ApiResponse updateOrderItineraryItemToDate(
            @PathVariable(QUOTATION_ID) String id,
            @RequestBody UpdateOrderItineraryItemToDateCrmRequest request) {
        quotationUseCase.updateOrderItineraryItemToDate(id, request);
        return ApiResponse.success();
    }

    @PostMapping(GET_NEW_DATE_BY_QUOTATION)
    @Operation(
            summary = "Get new date quotation.",
            description = "Get new date quotation."
    )
    public ApiResponse getNewDateQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody NewDateCrmRequest request) {
        return ApiResponse.success(
                quotationUseCase.getNewDateQuotation(idQuotation, request)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by quotation.",
            description = "Find data updated by quotation."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(QUOTATION_ID) String idQuotation) {
        return ApiResponse.success(
                quotationUseCase.getQuotationModificationData(idQuotation)
        );
    }

    @GetMapping(FIND_DATA_USER_UPDATED)
    @Operation(
            summary = "Find data user updated by quotation.",
            description = "Find data user updated by quotation."
    )
    public ApiResponse findDataUserUpdated(
            @PathVariable(QUOTATION_ID) String idQuotation) {
        return ApiResponse.success(
                quotationUseCase.getQuotationModificationUserData(idQuotation)
        );
    }

    @PostMapping(ADD_COST_TOUR)
    @Operation(
            summary = "add cost tour.",
            description = "Add cost tour."
    )
    public ApiResponse addCostTour(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @RequestBody CostTourCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.addCostTour(request, idQuotation)
        );
    }

    @PutMapping(EDIT_COST_TOUR)
    @Operation(
            summary = "Edit cost tour.",
            description = "Edit cost tour."
    )
    public ApiResponse editCostTour(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.editCostTour(idCostTour, request, idQuotation)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR)
    @Operation(
            summary = "Delete cost tour.",
            description = "Delete cost tour."
    )
    public ApiResponse deleteCostTour(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        quotationUseCase.deleteCostTour(idCostTour, idQuotation);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR)
    @Operation(
            summary = "Find cost tour by id and quotation.",
            description = "Find cost tour by id and quotation."
    )
    public ApiResponse findCostTourByIdAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                quotationUseCase.findByCostTourAndQuotation(idCostTour, idQuotation)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR)
    @Operation(
            summary = "Find all cost tour by quotation.",
            description = "Find all cost tour by quotation."
    )
    public ApiResponse findAllCostTourBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllCostTourByQuotation(idQuotation)
        );
    }

    @PostMapping(ADD_COST_TOUR_ITEM)
    @Operation(
            summary = "add cost tour item.",
            description = "Add cost tour item."
    )
    public ApiResponse addCostTourItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourItemCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.addCostTourItem(request, idQuotation, idCostTour)
        );
    }

    @PutMapping(EDIT_COST_TOUR_ITEM)
    @Operation(
            summary = "Edit cost tour item.",
            description = "Edit cost tour item."
    )
    public ApiResponse editCostTourItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem,
            @RequestBody CostTourItemUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.editCostTourItem(idCostTourItem, request, idQuotation, idCostTour)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR_ITEM)
    @Operation(
            summary = "Delete cost tour item.",
            description = "Delete cost tour item."
    )
    public ApiResponse deleteCostTourItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem
    ) {
        quotationUseCase.deleteCostTourItem(idCostTourItem, idQuotation, idCostTour);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR_ITEM)
    @Operation(
            summary = "Find cost tour item by id and cost tour and quotation.",
            description = "Find cost tour item by id and cost tour and quotation."
    )
    public ApiResponse findCostTourByCostTourAndCostTourItemAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_ITEM_ID) String idCostTourItem
    ) {
        return ApiResponse.success(
                quotationUseCase.findByCostTourItemAndQuotationAndCostTour(idCostTourItem, idQuotation, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR_ITEM)
    @Operation(
            summary = "Find all cost tour item by cost tour and quotation.",
            description = "Find all cost tour item by cost tour and quotation."
    )
    public ApiResponse findAllCostTourItemBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllCostTourItemByQuotationAndCostTour(idQuotation, idCostTour)
        );
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryAndCost(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(TOUR_ID) String idTour
    ) {
        quotationUseCase.removeToursAndCostTourByQuotation(idQuotation, idTour);
        return ApiResponse.success();
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_ITINERARY_AND_COST_TOUR)
    @Operation(
            summary = "Delete tour in itinerary date.",
            description = "Delete tour in itinerary date."
    )
    public ApiResponse deleteTourByItineraryAndCostTour(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        quotationUseCase.removeToursAndCostTourByQuotationAndCostTour(idQuotation, idTour, idCostTour);
        return ApiResponse.success();
    }

    @PostMapping(ADD_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "add cost tour service item.",
            description = "Add cost tour service item."
    )
    public ApiResponse addCostTourServiceItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @RequestBody CostTourServiceItemCreateRequest request) {
        return ApiResponse.success(
                quotationUseCase.addCostTourServiceItem(request, idQuotation, idCostTour)
        );
    }

    @PutMapping(EDIT_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Edit cost tour service item.",
            description = "Edit cost tour service item."
    )
    public ApiResponse editCostTourServiceItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem,
            @RequestBody CostTourServiceItemUpdateRequest request) {
        return ApiResponse.success(
                quotationUseCase.editCostTourServiceItem(idCostTourServiceItem, request, idQuotation, idCostTour)
        );
    }

    @DeleteMapping(DELETE_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Delete cost tour service item.",
            description = "Delete cost tour service item."
    )
    public ApiResponse deleteCostTourServiceItem(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem
    ) {
        quotationUseCase.deleteCostTourServiceItem(idCostTourServiceItem, idQuotation, idCostTour);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Find cost tour service item by id and cost tour and quotation.",
            description = "Find cost tour service item by id and cost tour and quotation."
    )
    public ApiResponse findCostTourByCostTourAndCostTourServiceItemAndQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour,
            @PathVariable(COST_TOUR_SERVICE_ITEM_ID) String idCostTourServiceItem
    ) {
        return ApiResponse.success(
                quotationUseCase.findByCostTourServiceItemAndQuotationAndCostTour(idCostTourServiceItem, idQuotation, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_COST_TOUR_SERVICE_ITEM)
    @Operation(
            summary = "Find all cost tour service item by cost tour and quotation.",
            description = "Find all cost tour service item by cost tour and quotation."
    )
    public ApiResponse findAllCostTourServiceItemBuQuotation(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllCostTourServiceItemByQuotationAndCostTour(idQuotation, idCostTour)
        );
    }

    @GetMapping(FIND_ALL_ITINERARY_DATE_FROM_COST_TOUR)
    @Operation(
            summary = "Find all itinerary date by quotation from cost tour.",
            description = "Find all itinerary date by quotation from cost tour."
    )
    public ApiResponse findAllItineraryDateBuQuotationFromCostTour(
            @PathVariable(QUOTATION_ID) String idQuotation,
            @PathVariable(COST_TOUR_ID) String idCostTour
    ) {
        return ApiResponse.success(
                quotationUseCase.findAllItineraryDateByQuotationFromCostTour(idQuotation, idCostTour)
        );
    }

    @PutMapping(UPDATE_PRICE_QUOTATION)
    @Operation(
            summary = "Update price quotation.",
            description = "Update price quotation."
    )
    public ApiResponse updatePriceQuotation(
            @PathVariable(QUOTATION_ID) String id) {
        quotationUseCase.updatePriceQuotation(id);
        return ApiResponse.success();
    }
}
