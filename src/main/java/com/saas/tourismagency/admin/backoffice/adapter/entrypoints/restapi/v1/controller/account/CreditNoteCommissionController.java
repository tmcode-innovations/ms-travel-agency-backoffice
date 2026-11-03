package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission.CreditNoteCommissionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission.CreditNoteCommissionSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission.CreditNoteCommissionSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnotecommission.CreditNoteCommissionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.CreditNoteCommissionUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.CreditNoteCommissionController.CREDIT_NOTE_COMMISSION;

@Slf4j
@Tag(name = "Finance - Credit Note Commission", description = "Finance Module")
@RestController
@RequestMapping(CREDIT_NOTE_COMMISSION)
public class CreditNoteCommissionController {

    public static final String CREDIT_NOTE_COMMISSION = "/credit-note-commissions";

    public static final String CREDIT_NOTE_COMMISSION_ID = "credit_note_commission_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CREDIT_NOTE_COMMISSION_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CREDIT_NOTE_COMMISSION_ID + "}";
    public static final String DELETE = "/delete/{" + CREDIT_NOTE_COMMISSION_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + CREDIT_NOTE_COMMISSION_ID + "}/export-credit-note-commission/pdf";

    @Autowired
    private CreditNoteCommissionUseCase creditNoteCommissionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create credit note commission.",
            description = "Create credit note commission."
    )
    public ApiResponse create(
            @RequestBody CreditNoteCommissionCreateRequest request) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find credit note commission by id.",
            description = "Find credit note commission by id."
    )
    public ApiResponse findBy(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String id) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all credit note commissions.",
            description = "Find all credit note commissions."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit credit note commission.",
            description = "Edit credit note commission."
    )
    public ApiResponse edit(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String id,
            @RequestBody CreditNoteCommissionUpdateRequest request) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete credit note commission.",
            description = "Delete credit note commission."
    )
    public ApiResponse deleteById(
           @PathVariable(CREDIT_NOTE_COMMISSION_ID) String id) {
        creditNoteCommissionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate credit note commissions.",
            description = "Search all credit note commissions."
    )
    public ApiResponse searchPaginate(
            @RequestBody CreditNoteCommissionSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate credit note commissions.",
            description = "Search filter paginate credit note commissions."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CreditNoteCommissionSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.addDocumentFinanceDetail(request, idCreditNoteCommission)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.editDocumentFinanceDetail(idDetail, request, idCreditNoteCommission)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        creditNoteCommissionUseCase.deleteDocumentFinanceDetail(idDetail, idCreditNoteCommission);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and credit note commission.",
            description = "Find detail by id and credit note commission."
    )
    public ApiResponse findDetailByIdAndCreditNoteCommission(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.findByIdAndDocumentFinance(idDetail, idCreditNoteCommission)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by credit note commission.",
            description = "Find all detail by credit note commission."
    )
    public ApiResponse findAllDetailByCreditNoteCommission(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission
    ) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.findAllDocumentFinanceDetailByDocumentFinance(idCreditNoteCommission)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by collection.",
            description = "Find data updated by collection."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission) {
        return ApiResponse.success(
                creditNoteCommissionUseCase.getCreditNoteCommissionModificationData(idCreditNoteCommission)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export credit note commission.",
            description = "Export credit note commission."
    )
    public ResponseEntity<Resource> exportCreditNoteCommissionPdf(
            @PathVariable(CREDIT_NOTE_COMMISSION_ID) String idCreditNoteCommission
    ) {
        byte[] reportContent = creditNoteCommissionUseCase.exportCreditNoteCommissionPdf(idCreditNoteCommission);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("credit_note_commission_report.pdf").build().toString())
                .body(resource);
    }

}
