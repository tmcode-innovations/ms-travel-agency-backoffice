package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.PaymentTypeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.PaymentTypeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.PaymentTypeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.PaymentTypeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.PaymentTypeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.PaymentTypeController.PAYMENT_TYPE;

@Slf4j
@Tag(name = "Maintenance - Payment Type", description = "Maintenance Module")
@RestController
@RequestMapping(PAYMENT_TYPE)
public class PaymentTypeController {

    public static final String PAYMENT_TYPE = "/payment-types";

    public static final String PAYMENT_TYPE_ID = "payment_type_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PAYMENT_TYPE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PAYMENT_TYPE_ID + "}";
    public static final String DELETE = "/delete/{" + PAYMENT_TYPE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PaymentTypeUseCase paymentTypeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create payment type.",
            description = "Create payment type."
    )
    public ApiResponse create(
            @RequestBody PaymentTypeCreateRequest request) {
        return ApiResponse.success(
                paymentTypeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find payment type by id.",
            description = "Find payment type by id."
    )
    public ApiResponse findBy(
            @PathVariable(PAYMENT_TYPE_ID) String id) {
        return ApiResponse.success(
                paymentTypeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all payment types.",
            description = "Find all payment types."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                paymentTypeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit payment type.",
            description = "Edit payment type."
    )
    public ApiResponse edit(
            @PathVariable(PAYMENT_TYPE_ID) String id,
            @RequestBody PaymentTypeUpdateRequest request) {
        return ApiResponse.success(
                paymentTypeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete payment type.",
            description = "Delete payment type."
    )
    public ApiResponse deleteById(
           @PathVariable(PAYMENT_TYPE_ID) String id) {
        paymentTypeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate payment types.",
            description = "Search all payment types."
    )
    public ApiResponse searchPaginate(
            @RequestBody PaymentTypeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                paymentTypeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate payment types.",
            description = "Search filter paginate payment types."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PaymentTypeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                paymentTypeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
