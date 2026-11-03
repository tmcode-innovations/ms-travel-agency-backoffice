package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUserUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr.EmployeeUserController.EMPLOYEE_USER;

@Slf4j
@Tag(name = "Human Resources - Relation Employee and User ", description = "Human Resources Module")
@RestController
@RequestMapping(EMPLOYEE_USER)
public class EmployeeUserController {

    public static final String EMPLOYEE_USER = "/employee-users";

    public static final String EMPLOYEE_USER_ID = "employee_user_id";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String USER_ID = "user_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + EMPLOYEE_USER_ID + "}";
    public static final String FIND_BY_EMPLOYEE = "/find-by-employee/{" + EMPLOYEE_ID + "}";
    public static final String FIND_BY_USER = "/find-by-user/{" + USER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + EMPLOYEE_USER_ID + "}";
    public static final String DELETE = "/delete/{" + EMPLOYEE_USER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private EmployeeUserUseCase employeeUserUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create relation employee and user.",
            description = "Create relation employee and user."
    )
    public ApiResponse create(
            @RequestBody EmployeeUserCreateRequest request) {
        return ApiResponse.success(
                employeeUserUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find relation employee and user by id.",
            description = "Find relation employee and user by id."
    )
    public ApiResponse findBy(
            @PathVariable(EMPLOYEE_USER_ID) String id) {
        return ApiResponse.success(
                employeeUserUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all relation employee and user.",
            description = "Find all relation employee and user."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                employeeUserUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit relation employee and user.",
            description = "Edit relation employee and user."
    )
    public ApiResponse edit(
            @PathVariable(EMPLOYEE_USER_ID) String id,
            @RequestBody EmployeeUserUpdateRequest request) {
        return ApiResponse.success(
                employeeUserUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete relation employee and user.",
            description = "Delete relation employee and user."
    )
    public ApiResponse deleteById(
           @PathVariable(EMPLOYEE_USER_ID) String id) {
        employeeUserUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate relation employee and user.",
            description = "Search all relation employee and user."
    )
    public ApiResponse searchPaginate(
            @RequestBody EmployeeUserSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                employeeUserUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate relation employee and user.",
            description = "Search filter paginate relation employee and user."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody EmployeeUserSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                employeeUserUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_BY_EMPLOYEE)
    @Operation(
            summary = "Find relation employee and user by employee.",
            description = "Find relation employee and user by employee."
    )
    public ApiResponse findByEmployee(
            @PathVariable(EMPLOYEE_ID) String id) {
        return ApiResponse.success(
                employeeUserUseCase.findByEmployee(id)
        );
    }

    @GetMapping(FIND_BY_USER)
    @Operation(
            summary = "Find relation employee and user by user.",
            description = "Find relation employee and user by user."
    )
    public ApiResponse findByUser(
            @PathVariable(USER_ID) String id) {
        return ApiResponse.success(
                employeeUserUseCase.findByUser(id)
        );
    }

}
