package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.AppSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.app.AppSearchRequest;
import com.saas.tourismagency.admin.backoffice.usecase.security.AppUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "App Controller", description = "App Controller")
@RestController
@RequestMapping(AppController.APP)
public class AppController {

    public static final String APP = "security/apps";

    public static final String APP_ID = "app_id";

    public static final String FIND_BY = "/find-by/{" + APP_ID + "}";
    public static final String FIND_ALL = "/find-all";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private AppUseCase appUseCase;

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find app by id.",
            description = "Find app by id."
    )
    public ApiResponse findBy(
            @PathVariable(APP_ID) String id) {
        return ApiResponse.success(
                appUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all profiles.",
            description = "Find all profiles."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                appUseCase.findAll()
        );
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate profiles.",
            description = "Search all profiles."
    )
    public ApiResponse searchPaginate(
            @RequestBody AppSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                appUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate profiles.",
            description = "Search filter paginate profiles."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody AppSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                appUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all profiles.",
            description = "Find all profiles."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                appUseCase.findAllSimple()
        );
    }

}
