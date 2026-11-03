package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierGuideCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierGuideUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierSearchRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierGuideUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.SupplierGuideController.SUPPLIER_GUIDE;

@Slf4j
@Tag(name = "Maintenance - Supplier Guide", description = "Maintenance Module")
@RestController
@RequestMapping(SUPPLIER_GUIDE)
public class SupplierGuideController {

    public static final String SUPPLIER_GUIDE = "/supplier-guides";

    public static final String SUPPLIER_GUIDE_ID = "supplier_guide_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + SUPPLIER_GUIDE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + SUPPLIER_GUIDE_ID + "}";
    public static final String DELETE = "/delete/{" + SUPPLIER_GUIDE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";
    public static final String FIND_ALL_SIMPLE_VOUCHER_EGRESS = "/find-all-simple-voucher-egress";

    @Autowired
    private SupplierGuideUseCase supplierUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create supplier.",
            description = "Create supplier."
    )
    public ApiResponse create(
            @RequestBody SupplierGuideCreateRequest request) {
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
            @PathVariable(SUPPLIER_GUIDE_ID) String id) {
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
            @PathVariable(SUPPLIER_GUIDE_ID) String id,
            @RequestBody SupplierGuideUpdateRequest request) {
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
           @PathVariable(SUPPLIER_GUIDE_ID) String id) {
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
