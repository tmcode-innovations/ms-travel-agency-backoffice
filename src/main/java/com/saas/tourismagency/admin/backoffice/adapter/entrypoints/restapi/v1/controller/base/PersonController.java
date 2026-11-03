package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.base.PersonUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.base.PersonController.PERSON;

@Slf4j
@Tag(name = "Base - Person", description = "Base Module")
@RestController
@RequestMapping(PERSON)
public class PersonController {

    public static final String PERSON = "/persons";

    public static final String PERSON_ID = "person_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PERSON_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PERSON_ID + "}";
    public static final String DELETE = "/delete/{" + PERSON_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private PersonUseCase personUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create person.",
            description = "Create person."
    )
    public ApiResponse create(
            @RequestBody PersonCreateRequest request) {
        return ApiResponse.success(
                personUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find person by id.",
            description = "Find person by id."
    )
    public ApiResponse findBy(
            @PathVariable(PERSON_ID) String id) {
        return ApiResponse.success(
                personUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all persons.",
            description = "Find all persons."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                personUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit person.",
            description = "Edit person."
    )
    public ApiResponse edit(
            @PathVariable(PERSON_ID) String id,
            @RequestBody PersonUpdateRequest request) {
        return ApiResponse.success(
                personUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete person.",
            description = "Delete person."
    )
    public ApiResponse deleteById(
           @PathVariable(PERSON_ID) String id) {
        personUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate persons.",
            description = "Search all persons."
    )
    public ApiResponse searchPaginate(
            @RequestBody PersonSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                personUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate persons.",
            description = "Search filter paginate persons."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody PersonSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                personUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
