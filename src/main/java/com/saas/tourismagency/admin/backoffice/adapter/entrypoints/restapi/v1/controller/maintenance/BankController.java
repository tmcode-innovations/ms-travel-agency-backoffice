package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.bank.BankUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.BankUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.maintenance.BankController.BANK;

@Slf4j
@Tag(name = "Maintenance - Bank", description = "Maintenance Module")
@RestController
@RequestMapping(BANK)
public class BankController {

    public static final String BANK = "/banks";

    public static final String BANK_ID = "bank_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + BANK_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + BANK_ID + "}";
    public static final String DELETE = "/delete/{" + BANK_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private BankUseCase bankUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create bank.",
            description = "Create bank."
    )
    public ApiResponse create(
            @RequestBody BankCreateRequest request) {
        return ApiResponse.success(
                bankUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find bank by id.",
            description = "Find bank by id."
    )
    public ApiResponse findBy(
            @PathVariable(BANK_ID) String id) {
        return ApiResponse.success(
                bankUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all banks.",
            description = "Find all banks."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                bankUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit bank.",
            description = "Edit bank."
    )
    public ApiResponse edit(
            @PathVariable(BANK_ID) String id,
            @RequestBody BankUpdateRequest request) {
        return ApiResponse.success(
                bankUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete bank.",
            description = "Delete bank."
    )
    public ApiResponse deleteById(
           @PathVariable(BANK_ID) String id) {
        bankUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate banks.",
            description = "Search all banks."
    )
    public ApiResponse searchPaginate(
            @RequestBody BankSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                bankUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate banks.",
            description = "Search filter paginate banks."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody BankSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                bankUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all banks.",
            description = "Find all banks."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                bankUseCase.findAllSimple()
        );
    }

}
