package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programcosttour.ProgramCostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate.ProgramDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.ProgramTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.crm.ProgramController.PROGRAM;

@Slf4j
@Tag(name = "Program and Quotation - Program", description = "Program and Quotation Module")
@RestController
@RequestMapping(PROGRAM)
public class ProgramController {

    public static final String PROGRAM = "/programs";

    public static final String PROGRAM_ID = "program_id";
    public static final String PROGRAM_ITEM_ID = "program_item_id";
    public static final String PROGRAM_DATE_ID = "program_date_id";
    public static final String PROGRAM_COST_TOUR_ID = "program_cost_tour_id";
    public static final String TOUR_ID = "tour_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROGRAM_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROGRAM_ID + "}";
    public static final String DELETE = "/delete/{" + PROGRAM_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_PROGRAM_ITEM = "/{" + PROGRAM_ID + "}/program/add";
    public static final String ADD_PROGRAM_ITEM_MULTIPLE = "/{" + PROGRAM_ID + "}/program-multiple/add";
    public static final String ADD_PROGRAM_TOUR_ITEM = "/{" + PROGRAM_ID + "}/program-tour/add";
    public static final String EDIT_PROGRAM_ITEM = "/{" + PROGRAM_ID + "}/program/edit/{" + PROGRAM_ITEM_ID + "}";
    public static final String DELETE_PROGRAM_ITEM = "/{" + PROGRAM_ID + "}/program/delete/{" + PROGRAM_ITEM_ID + "}";
    public static final String FIND_PROGRAM_ITEM = "/{" + PROGRAM_ID + "}/program/find/{" + PROGRAM_ITEM_ID + "}";
    public static final String FIND_ALL_PROGRAM_ITEM = "/{" + PROGRAM_ID + "}/program-item/find-all";
    public static final String FIND_ALL_PROGRAM_DATE = "/{" + PROGRAM_ID + "}/program-date/find-all";
    public static final String FIND_ALL_PROGRAM_ITEM_MODEL_VIEW = "/{" + PROGRAM_ID + "}/program-item/find-all-model-view";

    public static final String EDIT_PROGRAM_DATE = "/{" + PROGRAM_ID + "}/program-date/edit/{" + PROGRAM_DATE_ID + "}";
    public static final String FIND_PROGRAM_DATE = "/{" + PROGRAM_ID + "}/program-date/find/{" + PROGRAM_DATE_ID + "}";

    public static final String CHANGE_DATE_PROGRAM_DAY_BY_PROGRAM = "/{" + PROGRAM_ID + "}/change-date-program-day";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    public static final String ADD_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-cost-tour/add";
    public static final String EDIT_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-cost-tour/edit/{" + PROGRAM_COST_TOUR_ID + "}";
    public static final String DELETE_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-cost-tour/delete/{" + PROGRAM_COST_TOUR_ID + "}";
    public static final String FIND_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-cost-tour/find/{" + PROGRAM_COST_TOUR_ID + "}";

    public static final String FIND_ALL_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-cost-tour/find-all";
    public static final String DELETE_TOUR_ALL_FOR_COST_AND_PROGRAM_ITINERARY = "/{" + PROGRAM_ID + "}/program-itinerary-cost-tour/{" + TOUR_ID + "}/delete";
    public static final String DELETE_TOUR_ALL_FOR_COST_AND_PROGRAM_ITINERARY_AND_PROGRAM_COST_TOUR = "/{" + PROGRAM_ID + "}/program-itinerary-cost-tour/{" + TOUR_ID + "}/{" + PROGRAM_COST_TOUR_ID + "}/delete";

    @Autowired
    private ProgramUseCase programUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create program.",
            description = "Create program."
    )
    public ApiResponse create(
            @RequestBody ProgramCreateRequest request) {
        return ApiResponse.success(
                programUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find program by id.",
            description = "Find program by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROGRAM_ID) String id) {
        return ApiResponse.success(
                programUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all programs.",
            description = "Find all programs."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                programUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit program.",
            description = "Edit program."
    )
    public ApiResponse edit(
            @PathVariable(PROGRAM_ID) String id,
            @RequestBody ProgramUpdateRequest request) {
        return ApiResponse.success(
                programUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete program.",
            description = "Delete program."
    )
    public ApiResponse deleteById(
           @PathVariable(PROGRAM_ID) String id) {
        programUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate programs.",
            description = "Search all programs."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProgramSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                programUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate programs.",
            description = "Search filter paginate programs."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProgramSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                programUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_PROGRAM_ITEM)
    @Operation(
            summary = "Find all program item by program.",
            description = "Find all program item by program."
    )
    public ApiResponse findAllProgramItemByProgram(
            @PathVariable(PROGRAM_ID) String idProgram
    ) {
        return ApiResponse.success(
                programUseCase.findAllProgramItemByProgram(idProgram)
        );
    }

    @GetMapping(FIND_ALL_PROGRAM_DATE)
    @Operation(
            summary = "Find all program date by program.",
            description = "Find all program date by program."
    )
    public ApiResponse findAllProgramDateByProgram(
            @PathVariable(PROGRAM_ID) String idProgram
    ) {
        return ApiResponse.success(
                programUseCase.findAllProgramDateByProgram(idProgram)
        );
    }

    @PostMapping(ADD_PROGRAM_ITEM)
    @Operation(
            summary = "add program item.",
            description = "Add program item."
    )
    public ApiResponse addTourItem(
            @PathVariable(PROGRAM_ID) String idProgram,
            @RequestBody ProgramItemCreateRequest request) {
        return ApiResponse.success(
                programUseCase.addProgram(request, idProgram)
        );
    }

    @PostMapping(ADD_PROGRAM_ITEM_MULTIPLE)
    @Operation(
            summary = "add program item multiple.",
            description = "Add program item multiple."
    )
    public ApiResponse addProgramItemMultiple(
            @PathVariable(PROGRAM_ID) String idProgram,
            @RequestBody ProgramItemCreateRequest request) {
        programUseCase.addProgramMultiple(request, idProgram);
        return ApiResponse.success();
    }

    @PostMapping(ADD_PROGRAM_TOUR_ITEM)
    @Operation(
            summary = "add program item.",
            description = "Add program item."
    )
    public ApiResponse addItineraryTourItem(
            @PathVariable(PROGRAM_ID) String idProgram,
            @RequestBody ProgramTourItemCreateRequest request) {
        programUseCase.addProgramWithTour(request, idProgram);
        return ApiResponse.success();
    }

    @PutMapping(EDIT_PROGRAM_ITEM)
    @Operation(
            summary = "Edit program item.",
            description = "Edit program item."
    )
    public ApiResponse editTourItem(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_ITEM_ID) String idProgramItem,
            @RequestBody ProgramItemUpdateRequest request) {
        return ApiResponse.success(
                programUseCase.editProgram(idProgramItem, request, idProgram)
        );
    }

    @DeleteMapping(DELETE_PROGRAM_ITEM)
    @Operation(
            summary = "Delete program item.",
            description = "Delete program item."
    )
    public ApiResponse deleteTourItem(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_ITEM_ID) String idProgramItem
    ) {
        programUseCase.deleteProgram(idProgramItem, idProgram);
        return ApiResponse.success();
    }

    @GetMapping(FIND_PROGRAM_ITEM)
    @Operation(
            summary = "Find item by id and program.",
            description = "Find item by id and program."
    )
    public ApiResponse findTourItemByIdAndTour(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_ITEM_ID) String idProgramItem
    ) {
        return ApiResponse.success(
                programUseCase.findByProgramItemIdAndProgram(idProgramItem, idProgram)
        );
    }

    @GetMapping(FIND_ALL_PROGRAM_ITEM_MODEL_VIEW)
    @Operation(
            summary = "Find all program item by tour (model view).",
            description = "Find all program item by tour (model view)."
    )
    public ApiResponse findAllProgramItemByProgramModelView(
            @PathVariable(PROGRAM_ID) String idProgram
    ) {
        return ApiResponse.success(
                programUseCase.findAllProgramDateWithProgramItemByProgramModelView(idProgram)
        );
    }

    @PutMapping(EDIT_PROGRAM_DATE)
    @Operation(
            summary = "Edit program date.",
            description = "Edit program."
    )
    public ApiResponse editProgramDate(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_DATE_ID) String idProgramDate,
            @RequestBody ProgramDateUpdateOnlyNameRequest request) {
        return ApiResponse.success(
                programUseCase.editProgramDate(idProgramDate, request, idProgram)
        );
    }

    @GetMapping(FIND_PROGRAM_DATE)
    @Operation(
            summary = "Find program date by id and program.",
            description = "Find program date by id and program."
    )
    public ApiResponse findProgramDateByIdAndProgram(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_DATE_ID) String idProgramDate
    ) {
        return ApiResponse.success(
                programUseCase.findByProgramDateIdAndProgram(idProgramDate, idProgram)
        );
    }

    @PostMapping(CHANGE_DATE_PROGRAM_DAY_BY_PROGRAM)
    @Operation(
            summary = "Change day program.",
            description = "Change day program."
    )
    public ApiResponse changeDayTour(
            @PathVariable(PROGRAM_ID) String id,
            @RequestBody ChangeProgramDayCrmRequest request) {
        programUseCase.changeDateProgramDay(id, request);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all idioms.",
            description = "Find all idioms."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                programUseCase.findAllSimple()
        );
    }

    @PostMapping(ADD_PROGRAM_COST_TOUR)
    @Operation(
            summary = "add program cost tour.",
            description = "Add program cost tour."
    )
    public ApiResponse addProgramCostTour(
            @PathVariable(PROGRAM_ID) String idProgram,
            @RequestBody ProgramCostTourCreateRequest request) {
        return ApiResponse.success(
                programUseCase.addProgramCostTour(request, idProgram)
        );
    }

    @PutMapping(EDIT_PROGRAM_COST_TOUR)
    @Operation(
            summary = "Edit program cost tour.",
            description = "Edit program cost tour."
    )
    public ApiResponse editProgramCostTour(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_COST_TOUR_ID) String idProgramCostTour,
            @RequestBody ProgramCostTourUpdateRequest request) {
        return ApiResponse.success(
                programUseCase.editProgramCostTour(idProgramCostTour, request, idProgram)
        );
    }

    @DeleteMapping(DELETE_PROGRAM_COST_TOUR)
    @Operation(
            summary = "Delete program cost tour.",
            description = "Delete program cost tour."
    )
    public ApiResponse deleteProgramCostTour(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_COST_TOUR_ID) String idProgramCostTour
    ) {
        programUseCase.deleteProgramCostTour(idProgramCostTour, idProgram);
        return ApiResponse.success();
    }

    @GetMapping(FIND_PROGRAM_COST_TOUR)
    @Operation(
            summary = "Find program cost tour by id and program.",
            description = "Find program cost tour by id and program."
    )
    public ApiResponse findCostTourByIdAndProgram(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(PROGRAM_COST_TOUR_ID) String idProgramCostTour
    ) {
        return ApiResponse.success(
                programUseCase.findByProgramCostTourAndProgram(idProgramCostTour, idProgram)
        );
    }

    @GetMapping(FIND_ALL_PROGRAM_COST_TOUR)
    @Operation(
            summary = "Find all program cost tour by program.",
            description = "Find all program cost tour by program."
    )
    public ApiResponse findAllProgramCostTourBuProgram(
            @PathVariable(PROGRAM_ID) String idProgram
    ) {
        return ApiResponse.success(
                programUseCase.findAllProgramCostTourByProgram(idProgram)
        );
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_PROGRAM_ITINERARY)
    @Operation(
            summary = "Delete tour in program date.",
            description = "Delete tour in program date."
    )
    public ApiResponse deleteTourByProgramItineraryAndCost(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(TOUR_ID) String idTour
    ) {
        programUseCase.removeToursAndProgramCostTourByProgram(idProgram, idTour);
        return ApiResponse.success();
    }

    @DeleteMapping(DELETE_TOUR_ALL_FOR_COST_AND_PROGRAM_ITINERARY_AND_PROGRAM_COST_TOUR)
    @Operation(
            summary = "Delete tour in program date.",
            description = "Delete tour in program date."
    )
    public ApiResponse deleteTourByProgramItineraryAndProgramCostTour(
            @PathVariable(PROGRAM_ID) String idProgram,
            @PathVariable(TOUR_ID) String idTour,
            @PathVariable(PROGRAM_COST_TOUR_ID) String idProgramCostTour
    ) {
        programUseCase.removeToursAndProgramCostTourByProgramAndTourAndProgramCostTour(idProgram, idTour,idProgramCostTour);
        return ApiResponse.success();
    }
}
