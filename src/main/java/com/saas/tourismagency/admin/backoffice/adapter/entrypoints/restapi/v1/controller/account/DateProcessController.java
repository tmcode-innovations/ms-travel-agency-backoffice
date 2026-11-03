package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.dateprocess.DateProcessUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.DateProcessUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.DateProcessController.DATE_PROCESS;

@Slf4j
@Tag(name = "Finance - Date Process", description = "Finance Module")
@RestController
@RequestMapping(DATE_PROCESS)
public class DateProcessController {

    public static final String DATE_PROCESS = "/date-process";

    public static final String DATE_PROCESS_ID = "date_process_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DATE_PROCESS_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DATE_PROCESS_ID + "}";
    public static final String DELETE = "/delete/{" + DATE_PROCESS_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_BY_CURRENT = "/find-by-current";

    @Autowired
    private DateProcessUseCase dateProcessUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create date process.",
            description = "Create date process."
    )
    public ApiResponse create(
            @RequestBody DateProcessCreateRequest request) {
        return ApiResponse.success(
                dateProcessUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find date process by id.",
            description = "Find date process by id."
    )
    public ApiResponse findBy(
            @PathVariable(DATE_PROCESS_ID) String id) {
        return ApiResponse.success(
                dateProcessUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all date process.",
            description = "Find all date process."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                dateProcessUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit date process.",
            description = "Edit date process."
    )
    public ApiResponse edit(
            @PathVariable(DATE_PROCESS_ID) String id,
            @RequestBody DateProcessUpdateRequest request) {
        return ApiResponse.success(
                dateProcessUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete date process.",
            description = "Delete date process."
    )
    public ApiResponse deleteById(
           @PathVariable(DATE_PROCESS_ID) String id) {
        dateProcessUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate date process.",
            description = "Search all date process."
    )
    public ApiResponse searchPaginate(
            @RequestBody DateProcessSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                dateProcessUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate date process.",
            description = "Search filter paginate date process."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DateProcessSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                dateProcessUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_BY_CURRENT)
    @Operation(
            summary = "Find date process by current.",
            description = "Find date process by current."
    )
    public ApiResponse findByCurrent() {
        return ApiResponse.success(
                dateProcessUseCase.findDateProcessCurrent()
        );
    }
}
