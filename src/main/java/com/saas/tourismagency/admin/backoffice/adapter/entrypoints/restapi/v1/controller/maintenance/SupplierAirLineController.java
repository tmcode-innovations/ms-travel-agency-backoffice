package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierAirLineCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierAirLineUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierSearchRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierAirLineUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.SupplierAirLineController.SUPPLIER_AIR_LINE;

@Slf4j
@Tag(name = "Maintenance - Supplier Air Line", description = "Maintenance Module")
@RestController
@RequestMapping(SUPPLIER_AIR_LINE)
public class SupplierAirLineController {

    public static final String SUPPLIER_AIR_LINE = "/supplier-air-lines";

    public static final String SUPPLIER_AIR_LINE_ID = "supplier_air_line_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SUPPLIER_AIR_LINE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SUPPLIER_AIR_LINE_ID + "}";
    public static final String DELETE = "/delete/{" + SUPPLIER_AIR_LINE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";
    public static final String FIND_ALL_SIMPLE_VOUCHER_EGRESS = "/find-all-simple-voucher-egress";

    @Autowired
    private SupplierAirLineUseCase supplierUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create supplier.",
            description = "Create supplier."
    )
    public ApiResponse create(
            @RequestBody SupplierAirLineCreateRequest request) {
        return ApiResponse.success(
                supplierUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find supplier by id.",
            description = "Find supplier by id."
    )
    public ApiResponse findBy(
            @PathVariable(SUPPLIER_AIR_LINE_ID) String id) {
        return ApiResponse.success(
                supplierUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all suppliers.",
            description = "Find all suppliers."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                supplierUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit supplier.",
            description = "Edit supplier."
    )
    public ApiResponse edit(
            @PathVariable(SUPPLIER_AIR_LINE_ID) String id,
            @RequestBody SupplierAirLineUpdateRequest request) {
        return ApiResponse.success(
                supplierUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete supplier.",
            description = "Delete supplier."
    )
    public ApiResponse deleteById(
           @PathVariable(SUPPLIER_AIR_LINE_ID) String id) {
        supplierUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate suppliers.",
            description = "Search all suppliers."
    )
    public ApiResponse searchPaginate(
            @RequestBody SupplierSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                supplierUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate suppliers.",
            description = "Search filter paginate suppliers."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody SupplierSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                supplierUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all suppliers.",
            description = "Find all suppliers."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                supplierUseCase.findAllSimple()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_VOUCHER_EGRESS)
    @Operation(
            summary = "Find all suppliers.",
            description = "Find all suppliers."
    )
    public ApiResponse findAllSimpleVoucherEgress(
    ) {
        return ApiResponse.success(
                supplierUseCase.findAllSimpleVoucherEgress()
        );
    }
}
