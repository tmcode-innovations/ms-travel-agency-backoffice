package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.CustomerUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.CustomerController.CUSTOMER;

@Slf4j
@Tag(name = "Maintenance - Customer", description = "Maintenance Module")
@RestController
@RequestMapping(CUSTOMER)
public class CustomerController {

    public static final String CUSTOMER = "/customers";

    public static final String CUSTOMER_ID = "customer_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CUSTOMER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CUSTOMER_ID + "}";
    public static final String DELETE = "/delete/{" + CUSTOMER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private CustomerUseCase customerUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create customer.",
            description = "Create customer."
    )
    public ApiResponse create(
            @RequestBody CustomerCreateRequest request) {
        return ApiResponse.success(
                customerUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find customer by id.",
            description = "Find customer by id."
    )
    public ApiResponse findBy(
            @PathVariable(CUSTOMER_ID) String id) {
        return ApiResponse.success(
                customerUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all customers.",
            description = "Find all customers."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                customerUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit customer.",
            description = "Edit customer."
    )
    public ApiResponse edit(
            @PathVariable(CUSTOMER_ID) String id,
            @RequestBody CustomerUpdateRequest request) {
        return ApiResponse.success(
                customerUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete customer.",
            description = "Delete customer."
    )
    public ApiResponse deleteById(
           @PathVariable(CUSTOMER_ID) String id) {
        customerUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate customers.",
            description = "Search all customers."
    )
    public ApiResponse searchPaginate(
            @RequestBody CustomerSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                customerUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate customers.",
            description = "Search filter paginate customers."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CustomerSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                customerUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all customers.",
            description = "Find all customers."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                customerUseCase.findAllSimple()
        );
    }
}
