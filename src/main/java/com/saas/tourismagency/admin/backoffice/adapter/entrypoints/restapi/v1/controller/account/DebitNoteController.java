package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.DebitNoteCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.DebitNoteSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.DebitNoteSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.debitnote.DebitNoteUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.DebitNoteUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.DebitNoteController.DEBIT_NOTE;

@Slf4j
@Tag(name = "Finance - Debit Note", description = "Finance Module")
@RestController
@RequestMapping(DEBIT_NOTE)
public class DebitNoteController {

    public static final String DEBIT_NOTE = "/debit-notes";

    public static final String DEBIT_NOTE_ID = "debit_note_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + DEBIT_NOTE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + DEBIT_NOTE_ID + "}";
    public static final String DELETE = "/delete/{" + DEBIT_NOTE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + DEBIT_NOTE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + DEBIT_NOTE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + DEBIT_NOTE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + DEBIT_NOTE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + DEBIT_NOTE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + DEBIT_NOTE_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + DEBIT_NOTE_ID + "}/export-debit-note/pdf";

    @Autowired
    private DebitNoteUseCase debitNoteUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create debit note.",
            description = "Create debit note."
    )
    public ApiResponse create(
            @RequestBody DebitNoteCreateRequest request) {
        return ApiResponse.success(
                debitNoteUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find debit note by id.",
            description = "Find debit note by id."
    )
    public ApiResponse findBy(
            @PathVariable(DEBIT_NOTE_ID) String id) {
        return ApiResponse.success(
                debitNoteUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all debit notes.",
            description = "Find all debit notes."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                debitNoteUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit debit note.",
            description = "Edit debit note."
    )
    public ApiResponse edit(
            @PathVariable(DEBIT_NOTE_ID) String id,
            @RequestBody DebitNoteUpdateRequest request) {
        return ApiResponse.success(
                debitNoteUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete debit note.",
            description = "Delete debit note."
    )
    public ApiResponse deleteById(
           @PathVariable(DEBIT_NOTE_ID) String id) {
        debitNoteUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate debit notes.",
            description = "Search all debit notes."
    )
    public ApiResponse searchPaginate(
            @RequestBody DebitNoteSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                debitNoteUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate debit notes.",
            description = "Search filter paginate debit notes."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody DebitNoteSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                debitNoteUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                debitNoteUseCase.addDocumentFinanceDetail(request, idDebitNote)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                debitNoteUseCase.editDocumentFinanceDetail(idDetail, request, idDebitNote)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        debitNoteUseCase.deleteDocumentFinanceDetail(idDetail, idDebitNote);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and debit note.",
            description = "Find detail by id and debit note."
    )
    public ApiResponse findDetailByIdAndDebitNote(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                debitNoteUseCase.findByIdAndDocumentFinance(idDetail, idDebitNote)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by debit note.",
            description = "Find all detail by debit note."
    )
    public ApiResponse findAllDetailByDebitNote(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote
    ) {
        return ApiResponse.success(
                debitNoteUseCase.findAllDocumentFinanceDetailByDocumentFinance(idDebitNote)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by debit note.",
            description = "Find data updated by debit note."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote) {
        return ApiResponse.success(
                debitNoteUseCase.getDebitNoteModificationData(idDebitNote)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export debit note.",
            description = "Export debit note."
    )
    public ResponseEntity<Resource> exportDebitNotePdf(
            @PathVariable(DEBIT_NOTE_ID) String idDebitNote
    ) {
        byte[] reportContent = debitNoteUseCase.exportDebitNotePdf(idDebitNote);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("debit_note_report.pdf").build().toString())
                .body(resource);
    }

}
