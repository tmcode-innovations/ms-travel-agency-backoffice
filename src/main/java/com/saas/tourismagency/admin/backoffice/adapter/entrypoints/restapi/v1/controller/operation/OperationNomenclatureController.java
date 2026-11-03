package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation;

import com.saas.tourismagency.admin.backoffice.usecase.operation.OperationNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation.OperationNomenclatureController.OPERATION_NOMENCLATURE;

@Slf4j
@Tag(name = "Operation - Nomenclature", description = "Operation Module")
@RestController
@RequestMapping(OPERATION_NOMENCLATURE)
public class OperationNomenclatureController {

    public static final String OPERATION_NOMENCLATURE = "/operation-nomenclatures";

    public static final String SEARCH_REQUEST_ORDER_SERVICE_STATE = "/search-request-order-service-states";
    public static final String SEARCH_ORDER_SERVICE_STATE = "/search-order-service-states";

    @Autowired
    private OperationNomenclatureUseCase operationNomenclatureUseCase;

    @PostMapping(SEARCH_REQUEST_ORDER_SERVICE_STATE)
    @Operation(
            summary = "Search request order service states.",
            description = "Search all request order service states."
    )
    public ApiResponse searchAllRequestOrderServiceState() {
        return ApiResponse.success(
                operationNomenclatureUseCase.searchAllRequestOrderServiceState()
        );
    }

    @PostMapping(SEARCH_ORDER_SERVICE_STATE)
    @Operation(
            summary = "Search order service states.",
            description = "Search all order service states."
    )
    public ApiResponse searchAllOrderServiceState() {
        return ApiResponse.success(
                operationNomenclatureUseCase.searchAllOrderServiceState()
        );
    }
}
