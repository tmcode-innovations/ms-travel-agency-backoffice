package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote.ProformaCreditNoteCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote.ProformaCreditNoteSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote.ProformaCreditNoteSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformacreditnote.ProformaCreditNoteUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaCreditNoteUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.ProformaCreditNoteController.PROFORMA_CREDIT_NOTE;

@Slf4j
@Tag(name = "Finance - Proforma Credit Note", description = "Finance Module")
@RestController
@RequestMapping(PROFORMA_CREDIT_NOTE)
public class ProformaCreditNoteController {

    public static final String PROFORMA_CREDIT_NOTE = "/proforma-credit-notes";

    public static final String PROFORMA_CREDIT_NOTE_ID = "proforma_credit_note_id";
    public static final String DETAIL_ID = "detail_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + PROFORMA_CREDIT_NOTE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + PROFORMA_CREDIT_NOTE_ID + "}";
    public static final String DELETE = "/delete/{" + PROFORMA_CREDIT_NOTE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_DETAIL = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + PROFORMA_CREDIT_NOTE_ID + "}/export-proforma-credit-note/pdf";

    @Autowired
    private ProformaCreditNoteUseCase collectionUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create proforma credit note.",
            description = "Create proforma credit note."
    )
    public ApiResponse create(
            @RequestBody ProformaCreditNoteCreateRequest request) {
        return ApiResponse.success(
                collectionUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find proforma credit note by id.",
            description = "Find proforma credit note by id."
    )
    public ApiResponse findBy(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String id) {
        return ApiResponse.success(
                collectionUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all proforma credit notes.",
            description = "Find all proforma credit notes."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                collectionUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit proforma credit note.",
            description = "Edit proforma credit note."
    )
    public ApiResponse edit(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String id,
            @RequestBody ProformaCreditNoteUpdateRequest request) {
        return ApiResponse.success(
                collectionUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete proforma credit note.",
            description = "Delete proforma credit note."
    )
    public ApiResponse deleteById(
           @PathVariable(PROFORMA_CREDIT_NOTE_ID) String id) {
        collectionUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate proforma credit notes.",
            description = "Search all proforma credit notes."
    )
    public ApiResponse searchPaginate(
            @RequestBody ProformaCreditNoteSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                collectionUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate proforma credit notes.",
            description = "Search filter paginate proforma credit notes."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody ProformaCreditNoteSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                collectionUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProforma,
            @RequestBody ProformaDetailCreateRequest request) {
        return ApiResponse.success(
                collectionUseCase.addProformaDetail(request, idProforma)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody ProformaDetailUpdateRequest request) {
        return ApiResponse.success(
                collectionUseCase.editProformaDetail(idDetail, request, idProforma)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        collectionUseCase.deleteProformaDetail(idDetail, idProforma);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and proforma credit note.",
            description = "Find detail by id and proforma credit note."
    )
    public ApiResponse findDetailByIdAndProforma(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProforma,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                collectionUseCase.findByIdAndProforma(idDetail, idProforma)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by proforma credit note.",
            description = "Find all detail by proforma credit note."
    )
    public ApiResponse findAllDetailByProforma(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProforma
    ) {
        return ApiResponse.success(
                collectionUseCase.findAllProformaDetailByProforma(idProforma)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by proforma credit note.",
            description = "Find data updated by proforma credit note."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProformaCreditNote) {
        return ApiResponse.success(
                collectionUseCase.getProformaCreditNoteModificationData(idProformaCreditNote)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export proforma credit note.",
            description = "Export proforma credit note."
    )
    public ResponseEntity<Resource> exportProformaCreditNotePdf(
            @PathVariable(PROFORMA_CREDIT_NOTE_ID) String idProformaCreditNote
    ) {
        byte[] reportContent = collectionUseCase.exportProformaCreditNotePdf(idProformaCreditNote);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("proforma_credit_note_report.pdf").build().toString())
                .body(resource);
    }
}
