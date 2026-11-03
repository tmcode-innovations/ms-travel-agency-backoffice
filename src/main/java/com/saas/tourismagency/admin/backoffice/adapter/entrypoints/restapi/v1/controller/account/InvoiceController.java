package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.InvoiceUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.InvoiceController.INVOICE;

@Slf4j
@Tag(name = "Finance - Invoice", description = "Finance Module")
@RestController
@RequestMapping(INVOICE)
public class InvoiceController {

    public static final String INVOICE = "/invoices";

    public static final String INVOICE_ID = "invoice_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + INVOICE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + INVOICE_ID + "}";
    public static final String DELETE = "/delete/{" + INVOICE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + INVOICE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + INVOICE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + INVOICE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + INVOICE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + INVOICE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + INVOICE_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + INVOICE_ID + "}/export-invoice/pdf";

    @Autowired
    private InvoiceUseCase invoiceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create invoice.",
            description = "Create invoice."
    )
    public ApiResponse create(
            @RequestBody InvoiceCreateRequest request) {
        return ApiResponse.success(
                invoiceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find invoice by id.",
            description = "Find invoice by id."
    )
    public ApiResponse findBy(
            @PathVariable(INVOICE_ID) String id) {
        return ApiResponse.success(
                invoiceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all invoices.",
            description = "Find all invoices."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                invoiceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit invoice.",
            description = "Edit invoice."
    )
    public ApiResponse edit(
            @PathVariable(INVOICE_ID) String id,
            @RequestBody InvoiceUpdateRequest request) {
        return ApiResponse.success(
                invoiceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete invoice.",
            description = "Delete invoice."
    )
    public ApiResponse deleteById(
           @PathVariable(INVOICE_ID) String id) {
        invoiceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate invoices.",
            description = "Search all invoices."
    )
    public ApiResponse searchPaginate(
            @RequestBody InvoiceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                invoiceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate invoices.",
            description = "Search filter paginate invoices."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody InvoiceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                invoiceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(INVOICE_ID) String idInvoice,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                invoiceUseCase.addDocumentFinanceDetail(request, idInvoice)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(INVOICE_ID) String idInvoice,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                invoiceUseCase.editDocumentFinanceDetail(idDetail, request, idInvoice)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(INVOICE_ID) String idInvoice,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        invoiceUseCase.deleteDocumentFinanceDetail(idDetail, idInvoice);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and invoice.",
            description = "Find detail by id and invoice."
    )
    public ApiResponse findDetailByIdAndInvoice(
            @PathVariable(INVOICE_ID) String idInvoice,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                invoiceUseCase.findByIdAndDocumentFinance(idDetail, idInvoice)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by invoice.",
            description = "Find all detail by invoice."
    )
    public ApiResponse findAllDetailByInvoice(
            @PathVariable(INVOICE_ID) String idInvoice
    ) {
        return ApiResponse.success(
                invoiceUseCase.findAllDocumentFinanceDetailByDocumentFinance(idInvoice)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by invoice.",
            description = "Find data updated by invoice."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(INVOICE_ID) String idInvoice) {
        return ApiResponse.success(
                invoiceUseCase.getInvoiceModificationData(idInvoice)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export invoice.",
            description = "Export invoice."
    )
    public ResponseEntity<Resource> exportInvoicePdf(
            @PathVariable(INVOICE_ID) String idInvoice
    ) {
        byte[] reportContent = invoiceUseCase.exportInvoicePdf(idInvoice);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("invoice_report.pdf").build().toString())
                .body(resource);
    }
}
