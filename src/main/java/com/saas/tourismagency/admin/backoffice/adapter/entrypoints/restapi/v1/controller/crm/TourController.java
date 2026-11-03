package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.ChangeItineraryDayCrmRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.touitem.TourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tourdate.TourDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.usecase.crm.TourUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm.TourController.TOUR;

@Slf4j
@Tag(name = "Program and Quotation - Tour", description = "Program and Quotation Module")
@RestController
@RequestMapping(TOUR)
public class TourController {

    public static final String TOUR = "/tours";

    public static final String TOUR_ID = "tour_id";
    public static final String TOUR_ITEM_ID = "tour_item_id";
    public static final String TOUR_DATE_ID = "tour_date_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + TOUR_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + TOUR_ID + "}";
    public static final String DELETE = "/delete/{" + TOUR_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_TOUR_ITEM = "/{" + TOUR_ID + "}/tour/add";
    public static final String ADD_TOUR_ITEM_MULTIPLE = "/{" + TOUR_ID + "}/tour-multiple/add";
    public static final String EDIT_TOUR_ITEM = "/{" + TOUR_ID + "}/tour/edit/{" + TOUR_ITEM_ID + "}";
    public static final String DELETE_TOUR_ITEM = "/{" + TOUR_ID + "}/tour/delete/{" + TOUR_ITEM_ID + "}";
    public static final String FIND_TOUR_ITEM = "/{" + TOUR_ID + "}/tour/find/{" + TOUR_ITEM_ID + "}";
    public static final String FIND_ALL_TOUR_ITEM = "/{" + TOUR_ID + "}/tour-item/find-all";
    public static final String FIND_ALL_TOUR_DATE = "/{" + TOUR_ID + "}/tour-date/find-all";
    public static final String FIND_ALL_TOUR_ITEM_MODEL_VIEW = "/{" + TOUR_ID + "}/tour-item/find-all-model-view";

    public static final String EDIT_TOUR_DATE = "/{" + TOUR_ID + "}/tour-date/edit/{" + TOUR_DATE_ID + "}";
    public static final String FIND_TOUR_DATE = "/{" + TOUR_ID + "}/tour-date/find/{" + TOUR_DATE_ID + "}";

    public static final String CHANGE_DATE_TOUR_DAY_BY_TOUR = "/{" + TOUR_ID + "}/change-date-tour-day";


    @Autowired
    private TourUseCase tourUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create tour.",
            description = "Create tour."
    )
    public ApiResponse create(
            @RequestBody TourCreateRequest request) {
        return ApiResponse.success(
                tourUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find tour by id.",
            description = "Find tour by id."
    )
    public ApiResponse findBy(
            @PathVariable(TOUR_ID) String id) {
        return ApiResponse.success(
                tourUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all tours.",
            description = "Find all tours."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                tourUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit tour.",
            description = "Edit tour."
    )
    public ApiResponse edit(
            @PathVariable(TOUR_ID) String id,
            @RequestBody TourUpdateRequest request) {
        return ApiResponse.success(
                tourUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete tour.",
            description = "Delete tour."
    )
    public ApiResponse deleteById(
           @PathVariable(TOUR_ID) String id) {
        tourUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate tours.",
            description = "Search all tours."
    )
    public ApiResponse searchPaginate(
            @RequestBody TourSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                tourUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate tours.",
            description = "Search filter paginate tours."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody TourSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                tourUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_TOUR_ITEM)
    @Operation(
            summary = "Find all tour item by tour.",
            description = "Find all tour item by tour."
    )
    public ApiResponse findAllTourItemByTour(
            @PathVariable(TOUR_ID) String idTour
    ) {
        return ApiResponse.success(
                tourUseCase.findAllTourItemByTour(idTour)
        );
    }

    @GetMapping(FIND_ALL_TOUR_DATE)
    @Operation(
            summary = "Find all tour date by tour.",
            description = "Find all tour date by tour."
    )
    public ApiResponse findAllTourDateByTour(
            @PathVariable(TOUR_ID) String idTour
    ) {
        return ApiResponse.success(
                tourUseCase.findAllTourDateByTour(idTour)
        );
    }

    @PostMapping(ADD_TOUR_ITEM)
    @Operation(
            summary = "add tour item.",
            description = "Add tour item."
    )
    public ApiResponse addTourItem(
            @PathVariable(TOUR_ID) String idTour,
            @RequestBody TourItemCreateRequest request) {
        return ApiResponse.success(
                tourUseCase.addTour(request, idTour)
        );
    }

    @PostMapping(ADD_TOUR_ITEM_MULTIPLE)
    @Operation(
            summary = "add tour item multiple.",
            description = "Add tour item multiple."
    )
    public ApiResponse addProgramItemMultiple(
            @PathVariable(TOUR_ID) String idTour,
            @RequestBody TourItemCreateRequest request) {
        return ApiResponse.success(
                tourUseCase.addTourMultiple(request, idTour)
        );
    }

    @PutMapping(EDIT_TOUR_ITEM)
    @Operation(
            summary = "Edit tour item.",
            description = "Edit tour item."
    )
    public ApiResponse editTourItem(
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(TOUR_ITEM_ID) String idTourItem,
            @RequestBody TourItemUpdateRequest request) {
        return ApiResponse.success(
                tourUseCase.editTour(idTourItem, request, idTour)
        );
    }

    @DeleteMapping(DELETE_TOUR_ITEM)
    @Operation(
            summary = "Delete tour item.",
            description = "Delete tour item."
    )
    public ApiResponse deleteTourItem(
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(TOUR_ITEM_ID) String idTourItem
    ) {
        tourUseCase.deleteTour(idTourItem, idTour);
        return ApiResponse.success();
    }

    @GetMapping(FIND_TOUR_ITEM)
    @Operation(
            summary = "Find item by id and quotation.",
            description = "Find item by id and quotation."
    )
    public ApiResponse findTourItemByIdAndTour(
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(TOUR_ITEM_ID) String idTourItem
    ) {
        return ApiResponse.success(
                tourUseCase.findByTourItemIdAndTour(idTourItem, idTour)
        );
    }

    @GetMapping(FIND_ALL_TOUR_ITEM_MODEL_VIEW)
    @Operation(
            summary = "Find all tour item by tour (model view).",
            description = "Find all tour item by tour (model view)."
    )
    public ApiResponse findAllTourItemByTourModelView(
            @PathVariable(TOUR_ID) String idTour
    ) {
        return ApiResponse.success(
                tourUseCase.findAllTourDateWithTourItemByTourModelView(idTour)
        );
    }

    @PutMapping(EDIT_TOUR_DATE)
    @Operation(
            summary = "Edit tour date.",
            description = "Edit tour date."
    )
    public ApiResponse editTourDate(
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(TOUR_DATE_ID) String idTourDate,
            @RequestBody TourDateUpdateOnlyNameRequest request) {
        return ApiResponse.success(
                tourUseCase.editTourDate(idTourDate, request, idTour)
        );
    }

    @GetMapping(FIND_TOUR_DATE)
    @Operation(
            summary = "Find tour date by id and tour.",
            description = "Find tour date by id and tour."
    )
    public ApiResponse findTourDateByIdAndTour(
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(TOUR_DATE_ID) String idTourDate
    ) {
        return ApiResponse.success(
                tourUseCase.findByTourDateIdAndTour(idTourDate, idTour)
        );
    }

    @PostMapping(CHANGE_DATE_TOUR_DAY_BY_TOUR)
    @Operation(
            summary = "Change day tour.",
            description = "Change day tour."
    )
    public ApiResponse changeDayTour(
            @PathVariable(TOUR_ID) String id,
            @RequestBody ChangeTourDayCrmRequest request) {
        tourUseCase.changeDateTourDay(id, request);
        return ApiResponse.success();
    }
}
