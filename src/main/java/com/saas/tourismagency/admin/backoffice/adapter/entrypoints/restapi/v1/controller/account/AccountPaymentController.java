package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.AccountPaymentCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.AccountPaymentSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.AccountPaymentSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.accountpayment.AccountPaymentUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.AccountPaymentUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.AccountPaymentController.ACCOUNT_PAYMENT;

@Slf4j
@Tag(name = "Finance - Account Payment", description = "Finance Module")
@RestController
@RequestMapping(ACCOUNT_PAYMENT)
public class AccountPaymentController {

    public static final String ACCOUNT_PAYMENT = "/account-payments";

    public static final String ACCOUNT_PAYMENT_ID = "account_payment_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + ACCOUNT_PAYMENT_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + ACCOUNT_PAYMENT_ID + "}";
    public static final String DELETE = "/delete/{" + ACCOUNT_PAYMENT_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";
    public static final String FIND_BY_PROFORMA_TYPE_AND_IDENTIFY = "/find-by-proforma-type-and-identify";

    @Autowired
    private AccountPaymentUseCase accountPaymentUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create account payment.",
            description = "Create account payment."
    )
    public ApiResponse create(
            @RequestBody AccountPaymentCreateRequest request) {
        return ApiResponse.success(
                accountPaymentUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find account payment by id.",
            description = "Find account payment by id."
    )
    public ApiResponse findBy(
            @PathVariable(ACCOUNT_PAYMENT_ID) String id) {
        return ApiResponse.success(
                accountPaymentUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all account payments.",
            description = "Find all account payments."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                accountPaymentUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit account payment.",
            description = "Edit account payment."
    )
    public ApiResponse edit(
            @PathVariable(ACCOUNT_PAYMENT_ID) String id,
            @RequestBody AccountPaymentUpdateRequest request) {
        return ApiResponse.success(
                accountPaymentUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete account payment.",
            description = "Delete account payment."
    )
    public ApiResponse deleteById(
           @PathVariable(ACCOUNT_PAYMENT_ID) String id) {
        accountPaymentUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate account payments.",
            description = "Search all account payments."
    )
    public ApiResponse searchPaginate(
            @RequestBody AccountPaymentSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                accountPaymentUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate account payments.",
            description = "Search filter paginate account payments."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody AccountPaymentSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                accountPaymentUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @GetMapping(FIND_BY_PROFORMA_TYPE_AND_IDENTIFY)
    @Operation(
            summary = "Find account payments by proforma type and identify.",
            description = "Find all account payments for a given proforma type and identify."
    )
    public ApiResponse findByProformaTypeAndIdentify(
            @RequestParam("proformaType") String proformaType,
            @RequestParam("identify") String identify
    ) {
        return ApiResponse.success(
                accountPaymentUseCase.findByProformaTypeAndIdentify(proformaType, identify)
        );
    }

}
