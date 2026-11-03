package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.usecase.account.AccountNomenclatureUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.AccountNomenclatureController.ACCOUNT_NOMENCLATURE;

@Slf4j
@Tag(name = "Finance - Nomenclature", description = "Finance Module")
@RestController
@RequestMapping(ACCOUNT_NOMENCLATURE)
public class AccountNomenclatureController {

    public static final String ACCOUNT_NOMENCLATURE = "/account-nomenclatures";

    public static final String SEARCH_PAYMENT_TYPE = "/search-payment-types";
    public static final String SEARCH_VOUCHER_STATE = "/search-voucher-states";
    public static final String SEARCH_VOUCHER_TYPE = "/search-voucher-types";
    public static final String SEARCH_COST_SHEET_TWO_STATE = "/search-cost-sheet-two-states";
    public static final String SEARCH_EXPENSE_GUIDE_STATE = "/search-expense-guide-states";
    public static final String SEARCH_PROFORMA_STATE = "/search-proforma-states";
    public static final String SEARCH_PROFORMA_TYPE = "/search-proforma-types";
    public static final String SEARCH_DOCUMENT_ACCOUNT_STATE = "/search-document-account-states";
    public static final String SEARCH_DOCUMENT_ACCOUNT_TYPE = "/search-document-account-types";
    public static final String SEARCH_VALUE_TYPE_SHOW_SUPPLIER = "/search-value-type-show-supplier";

    @Autowired
    private AccountNomenclatureUseCase accountNomenclatureUseCase;

    @PostMapping(SEARCH_PAYMENT_TYPE)
    @Operation(
            summary = "Search payment types.",
            description = "Search all payment types."
    )
    public ApiResponse searchAllPaymentType() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllPaymentType()
        );
    }

    @PostMapping(SEARCH_VOUCHER_STATE)
    @Operation(
            summary = "Search voucher states.",
            description = "Search all voucher states."
    )
    public ApiResponse searchAllVoucherState() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllVoucherState()
        );
    }

    @PostMapping(SEARCH_VOUCHER_TYPE)
    @Operation(
            summary = "Search voucher types.",
            description = "Search all voucher types."
    )
    public ApiResponse searchAllVoucherType() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllVoucherType()
        );
    }

    @PostMapping(SEARCH_COST_SHEET_TWO_STATE)
    @Operation(
            summary = "Search cost sheet two states.",
            description = "Search all cost sheet two states."
    )
    public ApiResponse searchAllCostSheetTwoState() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllCostSheetTwoState()
        );
    }

    @PostMapping(SEARCH_EXPENSE_GUIDE_STATE)
    @Operation(
            summary = "Search expense guide states.",
            description = "Search all expense guide states."
    )
    public ApiResponse searchAllExpenseGuideState() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllExpenseGuideState()
        );
    }

    @PostMapping(SEARCH_PROFORMA_STATE)
    @Operation(
            summary = "Search proforma states.",
            description = "Search all proforma states."
    )
    public ApiResponse searchAllProformaState() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllProformaState()
        );
    }

    @PostMapping(SEARCH_PROFORMA_TYPE)
    @Operation(
            summary = "Search proforma types.",
            description = "Search all proforma types."
    )
    public ApiResponse searchAllProformaType() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllProformaType()
        );
    }

    @PostMapping(SEARCH_DOCUMENT_ACCOUNT_STATE)
    @Operation(
            summary = "Search document account states.",
            description = "Search all document account states."
    )
    public ApiResponse searchAllDocumentAccountState() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllDocumentAccountState()
        );
    }

    @PostMapping(SEARCH_DOCUMENT_ACCOUNT_TYPE)
    @Operation(
            summary = "Search document account types.",
            description = "Search all document account types."
    )
    public ApiResponse searchAllDocumentAccountType() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllDocumentAccountType()
        );
    }

    @PostMapping(SEARCH_VALUE_TYPE_SHOW_SUPPLIER)
    @Operation(
            summary = "Search value type show suppliers.",
            description = "Search all value type show suppliers."
    )
    public ApiResponse searchAllValueTypeShowSupplier() {
        return ApiResponse.success(
                accountNomenclatureUseCase.searchAllValueTypeShowSupplier()
        );
    }
}
