package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.TouristDestineUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.TouristDestineUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.TouristDestineController.TOURIST_DESTINE;

@Slf4j
@Tag(name = "Maintenance - Tourist Destine", description = "Maintenance Module")
@RestController
@RequestMapping(TOURIST_DESTINE)
public class TouristDestineController {

    public static final String TOURIST_DESTINE = "/tourist-destines";

    public static final String TOURIST_DESTINE_ID = "tourist_destine_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + TOURIST_DESTINE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + TOURIST_DESTINE_ID + "}";
    public static final String DELETE = "/delete/{" + TOURIST_DESTINE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private TouristDestineUseCase touristDestineUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create tourist destine.",
            description = "Create tourist destine."
    )
    public ApiResponse create(
            @RequestBody TouristDestineCreateRequest request) {
        return ApiResponse.success(
                touristDestineUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find tourist destine by id.",
            description = "Find tourist destine by id."
    )
    public ApiResponse findBy(
            @PathVariable(TOURIST_DESTINE_ID) String id) {
        return ApiResponse.success(
                touristDestineUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all tourist destines.",
            description = "Find all tourist destines."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                touristDestineUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit tourist destine.",
            description = "Edit tourist destine."
    )
    public ApiResponse edit(
            @PathVariable(TOURIST_DESTINE_ID) String id,
            @RequestBody TouristDestineUpdateRequest request) {
        return ApiResponse.success(
                touristDestineUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete tourist destine.",
            description = "Delete tourist destine."
    )
    public ApiResponse deleteById(
           @PathVariable(TOURIST_DESTINE_ID) String id) {
        touristDestineUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate tourist destines.",
            description = "Search all tourist destines."
    )
    public ApiResponse searchPaginate(
            @RequestBody TouristDestineSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                touristDestineUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate tourist destines.",
            description = "Search filter paginate tourist destines."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody TouristDestineSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                touristDestineUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
