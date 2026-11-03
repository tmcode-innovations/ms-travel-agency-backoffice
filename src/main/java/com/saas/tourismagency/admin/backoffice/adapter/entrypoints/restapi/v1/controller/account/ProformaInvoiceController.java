package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.ProformaInvoiceCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.ProformaInvoiceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.ProformaInvoiceSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.ProformaInvoiceUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaInvoiceUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ProformaInvoiceController.PROFORMA_INVOICE;

@Slf4j
@Tag(name = "Finance - Proforma Invoice", description = "Finance Module")
@RestController
@RequestMapping(PROFORMA_INVOICE)
public class ProformaInvoiceController {

    public static final String PROFORMA_INVOICE = "/proforma-invoices";

    public static final String PROFORMA_INVOICE_ID = "proforma_invoice_id";
    public static final String DETAIL_ID = "detail_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROFORMA_INVOICE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROFORMA_INVOICE_ID + "}";
    public static final String DELETE = "/delete/{" + PROFORMA_INVOICE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_DETAIL = "/{" + PROFORMA_INVOICE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + PROFORMA_INVOICE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + PROFORMA_INVOICE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + PROFORMA_INVOICE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + PROFORMA_INVOICE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + PROFORMA_INVOICE_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + PROFORMA_INVOICE_ID + "}/export-proforma-invoice/pdf";

    @Autowired
    private ProformaInvoiceUseCase proformaInvoiceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create proforma invoice.",
            description = "Create proforma invoice."
    )
    public ApiResponse create(
            @RequestBody ProformaInvoiceCreateRequest request) {
        return ApiResponse.success(
                proformaInvoiceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find proforma invoice by id.",
            description = "Find proforma invoice by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROFORMA_INVOICE_ID) String id) {
        return ApiResponse.success(
                proformaInvoiceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all proforma invoices.",
            description = "Find all proforma invoices."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                proformaInvoiceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit proforma invoice.",
            description = "Edit proforma invoice."
    )
    public ApiResponse edit(
            @PathVariable(PROFORMA_INVOICE_ID) String id,
            @RequestBody ProformaInvoiceUpdateRequest request) {
        return ApiResponse.success(
                proformaInvoiceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete proforma invoice.",
            description = "Delete proforma invoice."
    )
    public ApiResponse deleteById(
           @PathVariable(PROFORMA_INVOICE_ID) String id) {
        proformaInvoiceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate proforma invoices.",
            description = "Search all proforma invoices."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProformaInvoiceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                proformaInvoiceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate proforma invoices.",
            description = "Search filter paginate proforma invoices."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProformaInvoiceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                proformaInvoiceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(PROFORMA_INVOICE_ID) String idProforma,
            @RequestBody ProformaDetailCreateRequest request) {
        return ApiResponse.success(
                proformaInvoiceUseCase.addProformaDetail(request, idProforma)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(PROFORMA_INVOICE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody ProformaDetailUpdateRequest request) {
        return ApiResponse.success(
                proformaInvoiceUseCase.editProformaDetail(idDetail, request, idProforma)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(PROFORMA_INVOICE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        proformaInvoiceUseCase.deleteProformaDetail(idDetail, idProforma);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and proforma invoice.",
            description = "Find detail by id and proforma invoice."
    )
    public ApiResponse findDetailByIdAndProforma(
            @PathVariable(PROFORMA_INVOICE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                proformaInvoiceUseCase.findByIdAndProforma(idDetail, idProforma)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by proforma invoice.",
            description = "Find all detail by proforma invoice."
    )
    public ApiResponse findAllDetailByProforma(
            @PathVariable(PROFORMA_INVOICE_ID) String idProforma
    ) {
        return ApiResponse.success(
                proformaInvoiceUseCase.findAllProformaDetailByProforma(idProforma)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by proforma invoice.",
            description = "Find data updated by proforma invoice."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(PROFORMA_INVOICE_ID) String idProformaInvoice) {
        return ApiResponse.success(
                proformaInvoiceUseCase.getProformaInvoiceModificationData(idProformaInvoice)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export proforma invoice.",
            description = "Export proforma invoice."
    )
    public ResponseEntity<Resource> exportProformaInvoicePdf(
            @PathVariable(PROFORMA_INVOICE_ID) String idProformaInvoice
    ) {
        byte[] reportContent = proformaInvoiceUseCase.exportProformaInvoicePdf(idProformaInvoice);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("proforma_invoice_report.pdf").build().toString())
                .body(resource);
    }
}
