package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.InvoiceCommissionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.InvoiceCommissionSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.InvoiceCommissionSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoicecommission.InvoiceCommissionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.InvoiceCommissionUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ContentDisposition;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.InvoiceCommissionController.INVOICE_COMMISSION;

@Slf4j
@Tag(name = "Finance - Invoice Commission", description = "Finance Module")
@RestController
@RequestMapping(INVOICE_COMMISSION)
public class InvoiceCommissionController {

    public static final String INVOICE_COMMISSION = "/invoice-commissions";

    public static final String INVOICE_COMMISSION_ID = "invoice_commission_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + INVOICE_COMMISSION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + INVOICE_COMMISSION_ID + "}";
    public static final String DELETE = "/delete/{" + INVOICE_COMMISSION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + INVOICE_COMMISSION_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + INVOICE_COMMISSION_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + INVOICE_COMMISSION_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + INVOICE_COMMISSION_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + INVOICE_COMMISSION_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + INVOICE_COMMISSION_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + INVOICE_COMMISSION_ID + "}/export-invoice-commission/pdf";

    @Autowired
    private InvoiceCommissionUseCase InvoiceCommissionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create invoice commission.",
            description = "Create invoice commission."
    )
    public ApiResponse create(
            @RequestBody InvoiceCommissionCreateRequest request) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find invoice commission by id.",
            description = "Find invoice commission by id."
    )
    public ApiResponse findBy(
            @PathVariable(INVOICE_COMMISSION_ID) String id) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all invoice commissions.",
            description = "Find all invoice commissions."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit invoice commission.",
            description = "Edit invoice commission."
    )
    public ApiResponse edit(
            @PathVariable(INVOICE_COMMISSION_ID) String id,
            @RequestBody InvoiceCommissionUpdateRequest request) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete invoice commission.",
            description = "Delete invoice commission."
    )
    public ApiResponse deleteById(
           @PathVariable(INVOICE_COMMISSION_ID) String id) {
        InvoiceCommissionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate invoice commissions.",
            description = "Search all invoice commissions."
    )
    public ApiResponse searchPaginate(
            @RequestBody InvoiceCommissionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate invoice commissions.",
            description = "Search filter paginate invoice commissions."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody InvoiceCommissionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.addDocumentFinanceDetail(request, idInvoiceCommission)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.editDocumentFinanceDetail(idDetail, request, idInvoiceCommission)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        InvoiceCommissionUseCase.deleteDocumentFinanceDetail(idDetail, idInvoiceCommission);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and invoice commission.",
            description = "Find detail by id and invoice commission."
    )
    public ApiResponse findDetailByIdAndInvoiceCommission(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.findByIdAndDocumentFinance(idDetail, idInvoiceCommission)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by invoice commission.",
            description = "Find all detail by invoice commission."
    )
    public ApiResponse findAllDetailByInvoiceCommission(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission
    ) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.findAllDocumentFinanceDetailByDocumentFinance(idInvoiceCommission)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by collection.",
            description = "Find data updated by collection."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission) {
        return ApiResponse.success(
                InvoiceCommissionUseCase.getInvoiceCommissionModificationData(idInvoiceCommission)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export invoice commission.",
            description = "Export invoice commission."
    )
    public ResponseEntity<Resource> exportInvoiceCommissionPdf(
            @PathVariable(INVOICE_COMMISSION_ID) String idInvoiceCommission
    ) {
        byte[] reportContent = InvoiceCommissionUseCase.exportInvoiceCommissionPdf(idInvoiceCommission);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("invoice_commission_report.pdf").build().toString())
                .body(resource);
    }

}
