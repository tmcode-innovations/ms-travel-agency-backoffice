package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.ConfigurationSupplierOperatorCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.ConfigurationSupplierOperatorSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.ConfigurationSupplierOperatorSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.configurationsupplieroperator.ConfigurationSupplierOperatorUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.operation.ConfigurationSupplierOperatorUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation.ConfigurationSupplierOperatorController.CONFIGURATION_SUPPLIER_OPERATOR;

@Slf4j
@Tag(name = "Operation - Configuration Supplier Operator", description = "Operation Module")
@RestController
@RequestMapping(CONFIGURATION_SUPPLIER_OPERATOR)
public class ConfigurationSupplierOperatorController {

    public static final String CONFIGURATION_SUPPLIER_OPERATOR = "/configuration-supplier-operators";

    public static final String CONFIGURATION_SUPPLIER_OPERATOR_ID = "configuration_supplier_operator_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CONFIGURATION_SUPPLIER_OPERATOR_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CONFIGURATION_SUPPLIER_OPERATOR_ID + "}";
    public static final String DELETE = "/delete/{" + CONFIGURATION_SUPPLIER_OPERATOR_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private ConfigurationSupplierOperatorUseCase configurationSupplierOperatorUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create configuration supplier operator.",
            description = "Create configuration supplier operator."
    )
    public ApiResponse create(
            @RequestBody ConfigurationSupplierOperatorCreateRequest request) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find configuration supplier operator by id.",
            description = "Find configuration supplier operator by id."
    )
    public ApiResponse findBy(
            @PathVariable(CONFIGURATION_SUPPLIER_OPERATOR_ID) String id) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all configuration supplier operators.",
            description = "Find all configuration supplier operators."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit configuration supplier operator.",
            description = "Edit configuration supplier operator."
    )
    public ApiResponse edit(
            @PathVariable(CONFIGURATION_SUPPLIER_OPERATOR_ID) String id,
            @RequestBody ConfigurationSupplierOperatorUpdateRequest request) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete configuration supplier operator.",
            description = "Delete configuration supplier operator."
    )
    public ApiResponse deleteById(
           @PathVariable(CONFIGURATION_SUPPLIER_OPERATOR_ID) String id) {
        configurationSupplierOperatorUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate configuration supplier operators.",
            description = "Search all configuration supplier operators."
    )
    public ApiResponse searchPaginate(
            @RequestBody ConfigurationSupplierOperatorSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate configuration supplier operators.",
            description = "Search filter paginate configuration supplier operators."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ConfigurationSupplierOperatorSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                configurationSupplierOperatorUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
