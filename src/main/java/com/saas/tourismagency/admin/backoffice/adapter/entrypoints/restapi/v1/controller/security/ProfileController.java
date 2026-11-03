package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.ProfileCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.ProfileSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.ProfileSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.profile.ProfileUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.security.ProfileUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Profile Controller", description = "Profile Controller")
@RestController
@RequestMapping(ProfileController.PROFILE)
public class ProfileController {

    public static final String PROFILE = "security/profiles";

    public static final String PROFILE_ID = "profile_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROFILE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROFILE_ID + "}";
    public static final String DELETE = "/delete/{" + PROFILE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private ProfileUseCase profileUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create profile.",
            description = "Create profile."
    )
    public ApiResponse create(
            @RequestBody ProfileCreateRequest request) {
        return ApiResponse.success(
                profileUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find profile by id.",
            description = "Find profile by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROFILE_ID) String id) {
        return ApiResponse.success(
                profileUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all profiles.",
            description = "Find all profiles."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                profileUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit bank.",
            description = "Edit bank."
    )
    public ApiResponse edit(
            @PathVariable(PROFILE_ID) String id,
            @RequestBody ProfileUpdateRequest request) {
        return ApiResponse.success(
                profileUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete profile.",
            description = "Delete profile."
    )
    public ApiResponse deleteById(
           @PathVariable(PROFILE_ID) String id) {
        profileUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate profiles.",
            description = "Search all profiles."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProfileSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                profileUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate profiles.",
            description = "Search filter paginate profiles."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProfileSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                profileUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all profiles.",
            description = "Find all profiles."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                profileUseCase.findAllSimple()
        );
    }

}
