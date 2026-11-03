package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.CostSheetTwoPercentUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.CostSheetTwoPercentController.COST_SHEET_TWO_PERCENT;

@Slf4j
@Tag(name = "Maintenance - Cost Sheet Two Percent", description = "Maintenance Module")
@RestController
@RequestMapping(COST_SHEET_TWO_PERCENT)
public class CostSheetTwoPercentController {

    public static final String COST_SHEET_TWO_PERCENT = "/cost-sheet-two-percents";

    public static final String COST_SHEET_TWO_PERCENT_ID = "cost_sheet_two_percent_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + COST_SHEET_TWO_PERCENT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + COST_SHEET_TWO_PERCENT_ID + "}";
    public static final String DELETE = "/delete/{" + COST_SHEET_TWO_PERCENT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_AVAILABLE = "/change-available/{" + COST_SHEET_TWO_PERCENT_ID + "}";

    public static final String FIND_AVAILABLE = "/find-available";

    @Autowired
    private CostSheetTwoPercentUseCase costSheetTwoPercentUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create cost sheet two percent.",
            description = "Create cost sheet two percent."
    )
    public ApiResponse create(
            @RequestBody CostSheetTwoPercentCreateRequest request) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find cost sheet two percent by id.",
            description = "Find cost sheet two percent by id."
    )
    public ApiResponse findBy(
            @PathVariable(COST_SHEET_TWO_PERCENT_ID) String id) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all cost sheet two percents.",
            description = "Find all cost sheet two percents."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit cost sheet two percent.",
            description = "Edit cost sheet two percent."
    )
    public ApiResponse edit(
            @PathVariable(COST_SHEET_TWO_PERCENT_ID) String id,
            @RequestBody CostSheetTwoPercentUpdateRequest request) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete cost sheet two percent.",
            description = "Delete cost sheet two percent."
    )
    public ApiResponse deleteById(
           @PathVariable(COST_SHEET_TWO_PERCENT_ID) String id) {
        costSheetTwoPercentUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate cost sheet two percents.",
            description = "Search all cost sheet two percents."
    )
    public ApiResponse searchPaginate(
            @RequestBody CostSheetTwoPercentSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate cost sheet two percents.",
            description = "Search filter paginate cost sheet two percents."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CostSheetTwoPercentSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_AVAILABLE)
    @Operation(
            summary = "Change available cost sheet two percent.",
            description = "Change available cost sheet two percent."
    )
    public ApiResponse changeAvailable(
            @PathVariable(COST_SHEET_TWO_PERCENT_ID) String id,
            @RequestBody CostSheetTwoPercentChangeAvailableRequest request) {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.changeAvailable(id, request.getAvailable())
        );
    }

    @GetMapping(FIND_AVAILABLE)
    @Operation(
            summary = "Find available cost sheet two percent.",
            description = "Find available cost sheet two percent."
    )
    public ApiResponse FindAvailable() {
        return ApiResponse.success(
                costSheetTwoPercentUseCase.findAvailable()
        );
    }
}
