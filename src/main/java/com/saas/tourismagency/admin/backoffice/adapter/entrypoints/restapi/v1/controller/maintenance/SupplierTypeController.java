package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.SupplierTypeController.SUPPLIER_TYPE;

@Slf4j
@Tag(name = "Maintenance - Supplier Type", description = "Maintenance Module")
@RestController
@RequestMapping(SUPPLIER_TYPE)
public class SupplierTypeController {

    public static final String SUPPLIER_TYPE = "/supplier-types";

    public static final String SUPPLIER_TYPE_ID = "supplier_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SUPPLIER_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SUPPLIER_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + SUPPLIER_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_OPERATION_ORDER_SERVICE = "/find-all-operation-order-service";

    @Autowired
    private SupplierTypeUseCase supplierTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create supplier type.",
            description = "Create supplier type."
    )
    public ApiResponse create(
            @RequestBody SupplierTypeCreateRequest request) {
        return ApiResponse.success(
                supplierTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find supplier type by id.",
            description = "Find supplier type by id."
    )
    public ApiResponse findBy(
            @PathVariable(SUPPLIER_TYPE_ID) String id) {
        return ApiResponse.success(
                supplierTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all supplier types.",
            description = "Find all supplier types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                supplierTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit supplier type.",
            description = "Edit supplier type."
    )
    public ApiResponse edit(
            @PathVariable(SUPPLIER_TYPE_ID) String id,
            @RequestBody SupplierTypeUpdateRequest request) {
        return ApiResponse.success(
                supplierTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete supplier type.",
            description = "Delete supplier type."
    )
    public ApiResponse deleteById(
           @PathVariable(SUPPLIER_TYPE_ID) String id) {
        supplierTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate supplier types.",
            description = "Search all supplier types."
    )
    public ApiResponse searchPaginate(
            @RequestBody SupplierTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                supplierTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate supplier types.",
            description = "Search filter paginate supplier types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody SupplierTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                supplierTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_OPERATION_ORDER_SERVICE)
    @Operation(
            summary = "Find all operation order service supplier types.",
            description = "Find all operation order service supplier types."
    )
    public ApiResponse findAllOperationOrderService(
    ) {
        return ApiResponse.success(
                supplierTypeUseCase.findAllFromOperationOrderService()
        );
    }
}
