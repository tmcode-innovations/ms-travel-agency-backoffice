package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.GuideUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.GuideUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.GuideController.GUIDE;

@Slf4j
@Tag(name = "Maintenance - Guide", description = "Maintenance Module")
@RestController
@RequestMapping(GUIDE)
public class GuideController {

    public static final String GUIDE = "/guides";

    public static final String GUIDE_ID = "guide_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + GUIDE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + GUIDE_ID + "}";
    public static final String DELETE = "/delete/{" + GUIDE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String SEARCH_BY_GUIDE_AND_TRANSFER_DRIVE = "/search-by-guide-and-transfer-drive";
    public static final String SEARCH_BY_DRIVE = "/search-by-drive";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private GuideUseCase guideUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create guide.",
            description = "Create guide."
    )
    public ApiResponse create(
            @RequestBody GuideCreateRequest request) {
        return ApiResponse.success(
                guideUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find guide by id.",
            description = "Find guide by id."
    )
    public ApiResponse findBy(
            @PathVariable(GUIDE_ID) String id) {
        return ApiResponse.success(
                guideUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all guides.",
            description = "Find all guides."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                guideUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit guide.",
            description = "Edit guide."
    )
    public ApiResponse edit(
            @PathVariable(GUIDE_ID) String id,
            @RequestBody GuideUpdateRequest request) {
        return ApiResponse.success(
                guideUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete guide.",
            description = "Delete guide."
    )
    public ApiResponse deleteById(
           @PathVariable(GUIDE_ID) String id) {
        guideUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate guides.",
            description = "Search all guides."
    )
    public ApiResponse searchPaginate(
            @RequestBody GuideSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                guideUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate guides.",
            description = "Search filter paginate guides."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody GuideSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                guideUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_BY_GUIDE_AND_TRANSFER_DRIVE)
    @Operation(
            summary = "Search by guide and transfer drive.",
            description = "Search by guide and transfer drive."
    )
    public ApiResponse searchByGuideAndTransferDrive(
            @RequestBody BaseSearchRequestById searchRequestById
    ) {
        return ApiResponse.success(
                guideUseCase.searchAllByGuideAndTransferDrive(searchRequestById)
        );
    }

    @PostMapping(SEARCH_BY_DRIVE)
    @Operation(
            summary = "Search by drive.",
            description = "Search by drive."
    )
    public ApiResponse searchByDrive(
            @RequestBody BaseSearchRequestById searchRequestById
    ) {
        return ApiResponse.success(
                guideUseCase.searchAllByDrive(searchRequestById)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all guide.",
            description = "Find all guide."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                guideUseCase.findAllSimple()
        );
    }

}
