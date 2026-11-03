package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountrySearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountrySearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.CountryUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.CountryController.COUNTRY;

@Slf4j
@Tag(name = "Base- Country", description = "Base Module")
@RestController
@RequestMapping(COUNTRY)
public class CountryController {

    public static final String COUNTRY = "/countries";

    public static final String COUNTRY_ID = "country_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + COUNTRY_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + COUNTRY_ID + "}";
    public static final String DELETE = "/delete/{" + COUNTRY_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private CountryUseCase countryUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create country.",
            description = "Create country."
    )
    public ApiResponse create(
            @RequestBody CountryCreateRequest request) {
        return ApiResponse.success(
                countryUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find country by id.",
            description = "Find country by id."
    )
    public ApiResponse findBy(
            @PathVariable(COUNTRY_ID) String id) {
        return ApiResponse.success(
                countryUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all countries.",
            description = "Find all countries."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                countryUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit country.",
            description = "Edit country."
    )
    public ApiResponse edit(
            @PathVariable(COUNTRY_ID) String id,
            @RequestBody CountryUpdateRequest request) {
        return ApiResponse.success(
                countryUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete country.",
            description = "Delete country."
    )
    public ApiResponse deleteById(
           @PathVariable(COUNTRY_ID) String id) {
        countryUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate countries.",
            description = "Search all countries."
    )
    public ApiResponse searchPaginate(
            @RequestBody CountrySearchRequest searchRequest
    ) {
        return ApiResponse.success(
                countryUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate countries.",
            description = "Search filter paginate countries."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CountrySearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                countryUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
