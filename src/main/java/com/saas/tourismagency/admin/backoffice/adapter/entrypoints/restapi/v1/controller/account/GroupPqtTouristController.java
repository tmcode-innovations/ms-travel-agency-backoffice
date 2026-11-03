package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.GroupPqtTouristCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.GroupPqtTouristSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.GroupPqtTouristSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.grouppqttourist.GroupPqtTouristUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.GroupPqtTouristUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.GroupPqtTouristController.GROUP_PQT_TOURIST;

@Slf4j
@Tag(name = "Finance - Group Package Tourist", description = "Finance Module")
@RestController
@RequestMapping(GROUP_PQT_TOURIST)
public class GroupPqtTouristController {

    public static final String GROUP_PQT_TOURIST = "/group-pqt-tourists";

    public static final String GROUP_PQT_TOURIST_ID = "group_pqt_tourist_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + GROUP_PQT_TOURIST_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + GROUP_PQT_TOURIST_ID + "}";
    public static final String DELETE = "/delete/{" + GROUP_PQT_TOURIST_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private GroupPqtTouristUseCase groupPqtTouristUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create group pqt tourist.",
            description = "Create group pqt tourist."
    )
    public ApiResponse create(
            @RequestBody GroupPqtTouristCreateRequest request) {
        return ApiResponse.success(
                groupPqtTouristUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find group pqt tourist by id.",
            description = "Find group pqt tourist by id."
    )
    public ApiResponse findBy(
            @PathVariable(GROUP_PQT_TOURIST_ID) String id) {
        return ApiResponse.success(
                groupPqtTouristUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all group pqt tourists.",
            description = "Find all group pqt tourists."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                groupPqtTouristUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit group pqt tourist.",
            description = "Edit group pqt tourist."
    )
    public ApiResponse edit(
            @PathVariable(GROUP_PQT_TOURIST_ID) String id,
            @RequestBody GroupPqtTouristUpdateRequest request) {
        return ApiResponse.success(
                groupPqtTouristUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete group pqt tourist.",
            description = "Delete group pqt tourist."
    )
    public ApiResponse deleteById(
           @PathVariable(GROUP_PQT_TOURIST_ID) String id) {
        groupPqtTouristUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate group pqt tourists.",
            description = "Search all group pqt tourists."
    )
    public ApiResponse searchPaginate(
            @RequestBody GroupPqtTouristSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                groupPqtTouristUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate group pqt tourists.",
            description = "Search filter paginate group pqt tourists."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody GroupPqtTouristSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                groupPqtTouristUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
