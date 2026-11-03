package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.IdiomUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.IdiomController.IDIOM;

@Slf4j
@Tag(name = "Base - Idiom", description = "Base Module")
@RestController
@RequestMapping(IDIOM)
public class IdiomController {

    public static final String IDIOM = "/idioms";

    public static final String IDIOM_ID = "idiom_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + IDIOM_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + IDIOM_ID + "}";
    public static final String DELETE = "/delete/{" + IDIOM_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private IdiomUseCase idiomUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create idiom.",
            description = "Create idiom."
    )
    public ApiResponse create(
            @RequestBody IdiomCreateRequest request) {
        return ApiResponse.success(
                idiomUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find idiom by id.",
            description = "Find idiom by id."
    )
    public ApiResponse findBy(
            @PathVariable(IDIOM_ID) String id) {
        return ApiResponse.success(
                idiomUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all idioms.",
            description = "Find all idioms."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                idiomUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit idiom.",
            description = "Edit idiom."
    )
    public ApiResponse edit(
            @PathVariable(IDIOM_ID) String id,
            @RequestBody IdiomUpdateRequest request) {
        return ApiResponse.success(
                idiomUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete idiom.",
            description = "Delete idiom."
    )
    public ApiResponse deleteById(
           @PathVariable(IDIOM_ID) String id) {
        idiomUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate idioms.",
            description = "Search all idioms."
    )
    public ApiResponse searchPaginate(
            @RequestBody IdiomSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                idiomUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate idioms.",
            description = "Search filter paginate idioms."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody IdiomSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                idiomUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all idioms.",
            description = "Find all idioms."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                idiomUseCase.findAllSimple()
        );
    }
}
