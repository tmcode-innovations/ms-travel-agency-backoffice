package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency.CurrencyUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.CurrencyUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.CurrencyController.CURRENCY;

@Slf4j
@Tag(name = "Finance - Currency", description = "Finance Module")
@RestController
@RequestMapping(CURRENCY)
public class CurrencyController {

    public static final String CURRENCY = "/currencies";

    public static final String CURRENCY_ID = "currency_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CURRENCY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CURRENCY_ID + "}";
    public static final String DELETE = "/delete/{" + CURRENCY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private CurrencyUseCase currencyUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create currency.",
            description = "Create currency."
    )
    public ApiResponse create(
            @RequestBody CurrencyCreateRequest request) {
        return ApiResponse.success(
                currencyUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find currency by id.",
            description = "Find currency by id."
    )
    public ApiResponse findBy(
            @PathVariable(CURRENCY_ID) String id) {
        return ApiResponse.success(
                currencyUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all currencies.",
            description = "Find all currencies."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                currencyUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit currency.",
            description = "Edit currency."
    )
    public ApiResponse edit(
            @PathVariable(CURRENCY_ID) String id,
            @RequestBody CurrencyUpdateRequest request) {
        return ApiResponse.success(
                currencyUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete currency.",
            description = "Delete currency."
    )
    public ApiResponse deleteById(
           @PathVariable(CURRENCY_ID) String id) {
        currencyUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate currencies.",
            description = "Search all currencies."
    )
    public ApiResponse searchPaginate(
            @RequestBody CurrencySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                currencyUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate currencies.",
            description = "Search filter paginate currences."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CurrencySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                currencyUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
