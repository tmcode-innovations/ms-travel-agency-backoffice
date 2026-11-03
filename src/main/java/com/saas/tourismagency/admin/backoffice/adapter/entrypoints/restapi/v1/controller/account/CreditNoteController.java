package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.CreditNoteCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.CreditNoteSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.CreditNoteSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.CreditNoteUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.CreditNoteUseCase;
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
import org.springframework.http.ContentDisposition;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.CreditNoteController.CREDIT_NOTE;

@Slf4j
@Tag(name = "Finance - Credit Note", description = "Finance Module")
@RestController
@RequestMapping(CREDIT_NOTE)
public class CreditNoteController {

    public static final String CREDIT_NOTE = "/credit-notes";

    public static final String CREDIT_NOTE_ID = "credit_note_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + CREDIT_NOTE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + CREDIT_NOTE_ID + "}";
    public static final String DELETE = "/delete/{" + CREDIT_NOTE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + CREDIT_NOTE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + CREDIT_NOTE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + CREDIT_NOTE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + CREDIT_NOTE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + CREDIT_NOTE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + CREDIT_NOTE_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + CREDIT_NOTE_ID + "}/export-credit-note/pdf";

    @Autowired
    private CreditNoteUseCase creditNoteUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create credit note.",
            description = "Create credit note."
    )
    public ApiResponse create(
            @RequestBody CreditNoteCreateRequest request) {
        return ApiResponse.success(
                creditNoteUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find credit note by id.",
            description = "Find credit note by id."
    )
    public ApiResponse findBy(
            @PathVariable(CREDIT_NOTE_ID) String id) {
        return ApiResponse.success(
                creditNoteUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all credit notes.",
            description = "Find all credit notes."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                creditNoteUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit credit note.",
            description = "Edit credit note."
    )
    public ApiResponse edit(
            @PathVariable(CREDIT_NOTE_ID) String id,
            @RequestBody CreditNoteUpdateRequest request) {
        return ApiResponse.success(
                creditNoteUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete credit note.",
            description = "Delete credit note."
    )
    public ApiResponse deleteById(
           @PathVariable(CREDIT_NOTE_ID) String id) {
        creditNoteUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate credit notes.",
            description = "Search all credit notes."
    )
    public ApiResponse searchPaginate(
            @RequestBody CreditNoteSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                creditNoteUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate credit notes.",
            description = "Search filter paginate credit notes."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CreditNoteSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                creditNoteUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                creditNoteUseCase.addDocumentFinanceDetail(request, idCreditNote)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                creditNoteUseCase.editDocumentFinanceDetail(idDetail, request, idCreditNote)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        creditNoteUseCase.deleteDocumentFinanceDetail(idDetail, idCreditNote);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and credit note.",
            description = "Find detail by id and credit note."
    )
    public ApiResponse findDetailByIdAndCreditNote(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                creditNoteUseCase.findByIdAndDocumentFinance(idDetail, idCreditNote)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by credit note.",
            description = "Find all detail by credit note."
    )
    public ApiResponse findAllDetailByCreditNote(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote
    ) {
        return ApiResponse.success(
                creditNoteUseCase.findAllDocumentFinanceDetailByDocumentFinance(idCreditNote)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by credit note.",
            description = "Find data updated by credit note."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote) {
        return ApiResponse.success(
                creditNoteUseCase.getCreditNoteModificationData(idCreditNote)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export credit note.",
            description = "Export credit note."
    )
    public ResponseEntity<Resource> exportCreditNotePdf(
            @PathVariable(CREDIT_NOTE_ID) String idCreditNote
    ) {
        byte[] reportContent = creditNoteUseCase.exportCreditNotePdf(idCreditNote);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("credit_note_report.pdf").build().toString())
                .body(resource);
    }

}
