package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.servicepqttourist.ServicePqtTouristUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.ServicePqtTouristUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ServicePqtTouristController.SERVICE_PQT_TOURIST;

@Slf4j
@Tag(name = "Finance - Service Package Tourist", description = "Finance Module")
@RestController
@RequestMapping(SERVICE_PQT_TOURIST)
public class ServicePqtTouristController {

    public static final String SERVICE_PQT_TOURIST = "/service-pqt-tourists";

    public static final String SERVICE_PQT_TOURIST_ID = "service_pqt_tourist_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SERVICE_PQT_TOURIST_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SERVICE_PQT_TOURIST_ID + "}";
    public static final String DELETE = "/delete/{" + SERVICE_PQT_TOURIST_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private ServicePqtTouristUseCase servicePqtTouristUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create service pqt tourist.",
            description = "Create service pqt tourist."
    )
    public ApiResponse create(
            @RequestBody ServicePqtTouristCreateRequest request) {
        return ApiResponse.success(
                servicePqtTouristUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find service pqt tourist by id.",
            description = "Find service pqt tourist by id."
    )
    public ApiResponse findBy(
            @PathVariable(SERVICE_PQT_TOURIST_ID) String id) {
        return ApiResponse.success(
                servicePqtTouristUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all service pqt tourists.",
            description = "Find all service pqt tourists."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                servicePqtTouristUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit service pqt tourist.",
            description = "Edit service pqt tourist."
    )
    public ApiResponse edit(
            @PathVariable(SERVICE_PQT_TOURIST_ID) String id,
            @RequestBody ServicePqtTouristUpdateRequest request) {
        return ApiResponse.success(
                servicePqtTouristUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete service pqt tourist.",
            description = "Delete service pqt tourist."
    )
    public ApiResponse deleteById(
           @PathVariable(SERVICE_PQT_TOURIST_ID) String id) {
        servicePqtTouristUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate service pqt tourists.",
            description = "Search all service pqt tourists."
    )
    public ApiResponse searchPaginate(
            @RequestBody ServicePqtTouristSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                servicePqtTouristUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate service pqt tourists.",
            description = "Search filter paginate service pqt tourists."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ServicePqtTouristSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                servicePqtTouristUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
