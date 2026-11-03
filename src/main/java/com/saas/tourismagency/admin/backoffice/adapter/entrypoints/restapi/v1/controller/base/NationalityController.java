package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalitySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalitySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.NationalityUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.NationalityController.NATIONALITY;

@Slf4j
@Tag(name = "Base - Nationality", description = "Base Module")
@RestController
@RequestMapping(NATIONALITY)
public class NationalityController {

    public static final String NATIONALITY = "/nationalities";

    public static final String NATIONALITY_ID = "nationality_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + NATIONALITY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + NATIONALITY_ID + "}";
    public static final String DELETE = "/delete/{" + NATIONALITY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private NationalityUseCase nationalityUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create nationality.",
            description = "Create nationality."
    )
    public ApiResponse create(
            @RequestBody NationalityCreateRequest request) {
        return ApiResponse.success(
                nationalityUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find nationality by id.",
            description = "Find nationality by id."
    )
    public ApiResponse findBy(
            @PathVariable(NATIONALITY_ID) String id) {
        return ApiResponse.success(
                nationalityUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all nationalities.",
            description = "Find all nationalities."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                nationalityUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit nationality.",
            description = "Edit nationality."
    )
    public ApiResponse edit(
            @PathVariable(NATIONALITY_ID) String id,
            @RequestBody NationalityUpdateRequest request) {
        return ApiResponse.success(
                nationalityUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete nationality.",
            description = "Delete nationality."
    )
    public ApiResponse deleteById(
           @PathVariable(NATIONALITY_ID) String id) {
        nationalityUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate nationalities.",
            description = "Search all nationalities."
    )
    public ApiResponse searchPaginate(
            @RequestBody NationalitySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                nationalityUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate nationalities.",
            description = "Search filter paginate nationalities."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody NationalitySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                nationalityUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all nationalities.",
            description = "Find all nationalities."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                nationalityUseCase.findAllSimple()
        );
    }
}
