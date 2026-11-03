package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.RegisterDocumentVoucherCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.RegisterDocumentVoucherSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.RegisterDocumentVoucherSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.registerdocumentvoucher.RegisterDocumentVoucherUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.RegisterDocumentVoucherUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.RegisterDocumentVoucherController.REGISTER_DOCUMENT_VOUCHER;

@Slf4j
@Tag(name = "Finance - Register Document Voucher", description = "Finance Module")
@RestController
@RequestMapping(REGISTER_DOCUMENT_VOUCHER)
public class RegisterDocumentVoucherController {

    public static final String REGISTER_DOCUMENT_VOUCHER = "/register-document-vouchers";

    public static final String REGISTER_DOCUMENT_VOUCHER_ID = "register_document_voucher_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + REGISTER_DOCUMENT_VOUCHER_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + REGISTER_DOCUMENT_VOUCHER_ID + "}";
    public static final String DELETE = "/delete/{" + REGISTER_DOCUMENT_VOUCHER_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    @Autowired
    private RegisterDocumentVoucherUseCase registerDocumentVoucherUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create register document voucher.",
            description = "Create register document voucher."
    )
    public ApiResponse create(
            @RequestBody RegisterDocumentVoucherCreateRequest request) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find register document voucher by id.",
            description = "Find register document voucher by id."
    )
    public ApiResponse findBy(
            @PathVariable(REGISTER_DOCUMENT_VOUCHER_ID) String id) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all register document vouchers.",
            description = "Find all register document vouchers."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit register document voucher.",
            description = "Edit register document voucher."
    )
    public ApiResponse edit(
            @PathVariable(REGISTER_DOCUMENT_VOUCHER_ID) String id,
            @RequestBody RegisterDocumentVoucherUpdateRequest request) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete register document voucher.",
            description = "Delete register document voucher."
    )
    public ApiResponse deleteById(
           @PathVariable(REGISTER_DOCUMENT_VOUCHER_ID) String id) {
        registerDocumentVoucherUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate register document vouchers.",
            description = "Search all register document vouchers."
    )
    public ApiResponse searchPaginate(
            @RequestBody RegisterDocumentVoucherSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate register document vouchers.",
            description = "Search filter paginate register document vouchers."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody RegisterDocumentVoucherSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                registerDocumentVoucherUseCase.searchFilterAll(searchFilterRequest)
        );
    }

}
