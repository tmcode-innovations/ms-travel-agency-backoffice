package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.*;
import com.saas.tourismagency.admin.backoffice.usecase.operation.RequestOrderServiceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation.RequestOrderServiceController.REQUEST_ORDER_SERVICE;

@Slf4j
@Tag(name = "Operation - Request Order Service", description = "Operation Module")
@RestController
@RequestMapping(REQUEST_ORDER_SERVICE)
public class RequestOrderServiceController {

    public static final String REQUEST_ORDER_SERVICE = "/request-order-services";

    public static final String REQUEST_ORDER_SERVICE_ID = "request_order_service_id";

    public static final String REGISTER_GROUP_ID = "register_group_id";
    public static final String REGISTER_BOOKING_OPERATOR_ID = "register_booking_operator_id";
    public static final String SUPPLIER_OPERATOR_ID = "supplier_operator_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REQUEST_ORDER_SERVICE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REQUEST_ORDER_SERVICE_ID + "}";
    public static final String DELETE = "/delete/{" + REQUEST_ORDER_SERVICE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_STATE = "/change-state/{" + REQUEST_ORDER_SERVICE_ID + "}";

    public static final String GENERATE_REQUEST_ORDER_SERVICE = "/generate/{" + REGISTER_GROUP_ID + "}/{" + REGISTER_BOOKING_OPERATOR_ID + "}/{" + SUPPLIER_OPERATOR_ID + "}";
    public static final String RESEND_REQUEST_ORDER_SERVICE = "/resend/{" + REQUEST_ORDER_SERVICE_ID + "}";


    @Autowired
    private RequestOrderServiceUseCase requestOrderServiceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create request order service.",
            description = "Create request order service."
    )
    public ApiResponse create(
            @RequestBody RequestOrderServiceCreateRequest request) {
        return ApiResponse.success(
                requestOrderServiceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find request order service by id.",
            description = "Find request order service by id."
    )
    public ApiResponse findBy(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String id) {
        return ApiResponse.success(
                requestOrderServiceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all request order services.",
            description = "Find all request order services."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                requestOrderServiceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit request order service.",
            description = "Edit request order service."
    )
    public ApiResponse edit(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String id,
            @RequestBody RequestOrderServiceUpdateRequest request) {
        return ApiResponse.success(
                requestOrderServiceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete request order service.",
            description = "Delete request order service."
    )
    public ApiResponse deleteById(
           @PathVariable(REQUEST_ORDER_SERVICE_ID) String id) {
        requestOrderServiceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate request order services.",
            description = "Search all request order services."
    )
    public ApiResponse searchPaginate(
            @RequestBody RequestOrderServiceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                requestOrderServiceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate request order services.",
            description = "Search filter paginate request order services."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RequestOrderServiceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                requestOrderServiceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state request order service.",
            description = "Change state request order service."
    )
    public ApiResponse changeStateRequestOrderService(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String id,
            @RequestBody RequestOrderServiceChangeStateRequest request) {
        return ApiResponse.success(
                requestOrderServiceUseCase.changeState(id, request)
        );
    }

    @PostMapping(GENERATE_REQUEST_ORDER_SERVICE)
    @Operation(
            summary = "Generate request order service.",
            description = "Generate request order service."
    )
    public ApiResponse generateRequestOrderService(
            @PathVariable(REGISTER_GROUP_ID) String idRegisterGroup,
            @PathVariable(REGISTER_BOOKING_OPERATOR_ID) String idRegisterBookingOperator,
            @PathVariable(SUPPLIER_OPERATOR_ID) String idSupplierOperator) {
        return ApiResponse.success(
                requestOrderServiceUseCase.generate(idRegisterGroup, idRegisterBookingOperator, idSupplierOperator)
        );
    }

    @PostMapping(RESEND_REQUEST_ORDER_SERVICE)
    @Operation(
            summary = "Resend request order service.",
            description = "Resend request order service."
    )
    public ApiResponse resnedById(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String id) {
        requestOrderServiceUseCase.reSendRequestOrderService(id);
        return ApiResponse.success();
    }
}
