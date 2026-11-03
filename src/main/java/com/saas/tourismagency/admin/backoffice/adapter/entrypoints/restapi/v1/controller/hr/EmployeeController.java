package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.hr.EmployeeController.EMPLOYEE;

@Slf4j
@Tag(name = "Human Resources - Employee", description = "Human Resources Module")
@RestController
@RequestMapping(EMPLOYEE)
public class EmployeeController {

    public static final String EMPLOYEE = "/employees";

    public static final String EMPLOYEE_ID = "employee_id";
    public static final String EMPLOYEE_SIGN_FILE_FILENAME = "employee_sign_file_filename";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + EMPLOYEE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + EMPLOYEE_ID + "}";
    public static final String DELETE = "/delete/{" + EMPLOYEE_ID + "}";

    public static final String UPLOAD_SIGN = "/sign/{" + EMPLOYEE_ID + "}";
    public static final String DOWNLOAD_SIGN = "/download-sign/{" + EMPLOYEE_ID + "}/{"+EMPLOYEE_SIGN_FILE_FILENAME+"}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";

    public static final String FIND_ALL_SIMPLE_WITH_CONDITIONAL_AREA = "/find-all-simple-with-conditional-area";

    public static final String FIND_BY_ACTIVE_USER = "/find-by-active-user";

    @Autowired
    private EmployeeUseCase employeeUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create employee.",
            description = "Create employee."
    )
    public ApiResponse create(
            @RequestBody EmployeeCreateRequest request) {
        return ApiResponse.success(
                employeeUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find employee by id.",
            description = "Find employee by id."
    )
    public ApiResponse findBy(
            @PathVariable(EMPLOYEE_ID) String id) {
        return ApiResponse.success(
                employeeUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all employees.",
            description = "Find all employees."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                employeeUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit employee.",
            description = "Edit employee."
    )
    public ApiResponse edit(
            @PathVariable(EMPLOYEE_ID) String id,
            @RequestBody EmployeeUpdateRequest request) {
        return ApiResponse.success(
                employeeUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete employee.",
            description = "Delete employee."
    )
    public ApiResponse deleteById(
           @PathVariable(EMPLOYEE_ID) String id) {
        employeeUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate employees.",
            description = "Search all employees."
    )
    public ApiResponse searchPaginate(
            @RequestBody EmployeeSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                employeeUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate employees.",
            description = "Search filter paginate employees."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody EmployeeSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                employeeUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all employees.",
            description = "Find all employees."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                employeeUseCase.findAllSimple()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_WITH_CONDITIONAL_AREA)
    @Operation(
            summary = "Find all employees.",
            description = "Find all employees."
    )
    public ApiResponse findAllSimpleWithConditionalArea(
    ) {
        return ApiResponse.success(
                employeeUseCase.findAllSimpleWithConditionalArea()
        );
    }

    @PostMapping(
            value = UPLOAD_SIGN,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = "application/json;charset=UTF-8"
    )
    @Operation(
            summary = "Upload sign.",
            description = "Upload sign."
    )
    public ApiResponse uploadSign(
            @PathVariable(EMPLOYEE_ID) String id,
            @RequestParam(value = "files") MultipartFile[] multipartFile
    ) {
        return ApiResponse.success(employeeUseCase.uploadSign(id, multipartFile));
    }

    @GetMapping(value = DOWNLOAD_SIGN)
    @Operation(summary = "Download sign")
    public ResponseEntity<Resource> downloadSign(
            @PathVariable(value = EMPLOYEE_ID) String employeeId,
            @PathVariable(value = EMPLOYEE_SIGN_FILE_FILENAME) String filename) throws IOException {

        ByteArrayResource resource = employeeUseCase.downloadSign(employeeId);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }

    @GetMapping(FIND_BY_ACTIVE_USER)
    @Operation(
            summary = "Find employee by active user.",
            description = "Find employee by active user."
    )
    public ApiResponse findByActiveUser() {
        return ApiResponse.success(
                employeeUseCase.findByActiveUser()
        );
    }
}
