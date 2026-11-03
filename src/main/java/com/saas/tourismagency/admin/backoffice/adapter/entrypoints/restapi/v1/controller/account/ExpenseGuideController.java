package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.ExpenseGuideCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.ExpenseGuideSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.ExpenseGuideSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.ExpenseGuideUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.ExpenseGuideUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ExpenseGuideController.EXPENSE_GUIDE;

@Slf4j
@Tag(name = "Finance - Expense Guide", description = "Finance Module")
@RestController
@RequestMapping(EXPENSE_GUIDE)
public class ExpenseGuideController {

    public static final String EXPENSE_GUIDE = "/expense-guides";

    public static final String EXPENSE_GUIDE_ID = "expense_guide_id";
    public static final String DETAIL_ID = "detail_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + EXPENSE_GUIDE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + EXPENSE_GUIDE_ID + "}";
    public static final String DELETE = "/delete/{" + EXPENSE_GUIDE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_DETAIL = "/{" + EXPENSE_GUIDE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + EXPENSE_GUIDE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + EXPENSE_GUIDE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + EXPENSE_GUIDE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + EXPENSE_GUIDE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + EXPENSE_GUIDE_ID + "}/find-data-updated";

    public static final String EXPORT_EXPENSE_GUIDE = "/{" + EXPENSE_GUIDE_ID + "}/export-expense-guide/pdf";

    @Autowired
    private ExpenseGuideUseCase expenseGuideUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create expense guide.",
            description = "Create expense guide."
    )
    public ApiResponse create(
            @RequestBody ExpenseGuideCreateRequest request) {
        return ApiResponse.success(
                expenseGuideUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find expense guide by id.",
            description = "Find expense guide by id."
    )
    public ApiResponse findBy(
            @PathVariable(EXPENSE_GUIDE_ID) String id) {
        return ApiResponse.success(
                expenseGuideUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all expense guides.",
            description = "Find all expense guides."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                expenseGuideUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit expense guide.",
            description = "Edit expense guide."
    )
    public ApiResponse edit(
            @PathVariable(EXPENSE_GUIDE_ID) String id,
            @RequestBody ExpenseGuideUpdateRequest request) {
        return ApiResponse.success(
                expenseGuideUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete expense guide.",
            description = "Delete expense guide."
    )
    public ApiResponse deleteById(
           @PathVariable(EXPENSE_GUIDE_ID) String id) {
        expenseGuideUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate expense guides.",
            description = "Search all expense guides."
    )
    public ApiResponse searchPaginate(
            @RequestBody ExpenseGuideSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                expenseGuideUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate expense guides.",
            description = "Search filter paginate expense guides."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ExpenseGuideSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                expenseGuideUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "Add expense guide detail.",
            description = "Add expense guide detail."
    )
    public ApiResponse addDetail(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide,
            @RequestBody ExpenseGuideDetailCreateRequest request) {
        return ApiResponse.success(
                expenseGuideUseCase.addExpenseGuideDetail(request, idExpenseGuide)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit expense guide detail.",
            description = "Edit expense guide detail."
    )
    public ApiResponse editDetail(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody ExpenseGuideDetailUpdateRequest request) {
        return ApiResponse.success(
                expenseGuideUseCase.editExpenseGuideDetail(idDetail, request, idExpenseGuide)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete expense guide detail.",
            description = "Delete expense guide detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        expenseGuideUseCase.deleteExpenseGuideDetail(idDetail, idExpenseGuide);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find expense guide detail by id.",
            description = "Find expense guide detail by id."
    )
    public ApiResponse findDetailByIdAndExpenseGuide(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                expenseGuideUseCase.findByIdAndExpenseGuide(idDetail, idExpenseGuide)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all expense guide details.",
            description = "Find all expense guide details."
    )
    public ApiResponse findAllDetailByExpenseGuide(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide
    ) {
        return ApiResponse.success(
                expenseGuideUseCase.findAllExpenseGuideDetailByExpenseGuide(idExpenseGuide)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by expense guide.",
            description = "Find data updated by expense guide."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide) {
        return ApiResponse.success(
                expenseGuideUseCase.getExpenseGuideModificationData(idExpenseGuide)
        );
    }

    @GetMapping(EXPORT_EXPENSE_GUIDE)
    @Operation(
            summary = "Export expense guide.",
            description = "Export expense guide."
    )
    public ResponseEntity<Resource> exportExpenseGuidePdf(
            @PathVariable(EXPENSE_GUIDE_ID) String idExpenseGuide
    ) {
        byte[] reportContent = expenseGuideUseCase.exportExpenseGuidePdf(idExpenseGuide);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("expense_guide_report.pdf").build().toString())
                .body(resource);
    }

}
