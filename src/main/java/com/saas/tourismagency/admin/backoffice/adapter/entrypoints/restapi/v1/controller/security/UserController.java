package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "User Controller", description = "User Controller")
@RestController
@RequestMapping(UserController.USER)
public class UserController {

    public static final String USER = "security/users";

    public static final String USER_ID = "user_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + USER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + USER_ID + "}";
    public static final String DELETE = "/delete/{" + USER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    @Autowired
    private UserUseCase userUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create user.",
            description = "Create user."
    )
    public ApiResponse create(
            @RequestBody UserCreateRequest request) {
        return ApiResponse.success(
                userUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find user by id.",
            description = "Find user by id."
    )
    public ApiResponse findBy(
            @PathVariable(USER_ID) String id) {
        return ApiResponse.success(
                userUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all users.",
            description = "Find all users."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                userUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit user.",
            description = "Edit user."
    )
    public ApiResponse edit(
            @PathVariable(USER_ID) String id,
            @RequestBody UserUpdateRequest request) {
        return ApiResponse.success(
                userUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete user.",
            description = "Delete user."
    )
    public ApiResponse deleteById(
           @PathVariable(USER_ID) String id) {
        userUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate users.",
            description = "Search all users."
    )
    public ApiResponse searchPaginate(
            @RequestBody UserSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                userUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate users.",
            description = "Search filter paginate users."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody UserSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                userUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all users.",
            description = "Find all users."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                userUseCase.findAllSimple()
        );
    }

}
