package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.GroupBookingClassificationCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.GroupBookingClassificationSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.GroupBookingClassificationSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.GroupBookingClassificationUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.GroupBookingClassificationUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.GroupBookingClassificationController.GROUP_BOOKING_CLASSIFICATION;

@Slf4j
@Tag(name = "Maintenance - Group Booking Classification", description = "Maintenance Module")
@RestController
@RequestMapping(GROUP_BOOKING_CLASSIFICATION)
public class GroupBookingClassificationController {

    public static final String GROUP_BOOKING_CLASSIFICATION = "/group-booking-classifications";

    public static final String GROUP_BOOKING_CLASSIFICATION_ID = "group_booking_classification_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + GROUP_BOOKING_CLASSIFICATION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + GROUP_BOOKING_CLASSIFICATION_ID + "}";
    public static final String DELETE = "/delete/{" + GROUP_BOOKING_CLASSIFICATION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private GroupBookingClassificationUseCase groupBookingClassificationUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create group booking classification.",
            description = "Create group booking classification."
    )
    public ApiResponse create(
            @RequestBody GroupBookingClassificationCreateRequest request) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find group booking classification by id.",
            description = "Find group booking classification by id."
    )
    public ApiResponse findBy(
            @PathVariable(GROUP_BOOKING_CLASSIFICATION_ID) String id) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all group booking classifications.",
            description = "Find all group booking classifications."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit group booking classification.",
            description = "Edit group booking classification."
    )
    public ApiResponse edit(
            @PathVariable(GROUP_BOOKING_CLASSIFICATION_ID) String id,
            @RequestBody GroupBookingClassificationUpdateRequest request) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete group booking classification.",
            description = "Delete group booking classification."
    )
    public ApiResponse deleteById(
           @PathVariable(GROUP_BOOKING_CLASSIFICATION_ID) String id) {
        groupBookingClassificationUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate group booking classifications.",
            description = "Search all group booking classifications."
    )
    public ApiResponse searchPaginate(
            @RequestBody GroupBookingClassificationSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate group booking classifications.",
            description = "Search filter paginate group booking classifications."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody GroupBookingClassificationSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                groupBookingClassificationUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
