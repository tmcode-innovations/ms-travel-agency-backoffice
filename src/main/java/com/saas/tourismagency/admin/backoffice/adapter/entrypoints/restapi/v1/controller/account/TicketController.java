package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.TicketCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.TicketSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.TicketSearchRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.TicketUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.TicketUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.TicketController.TICKET;

@Slf4j
@Tag(name = "Finance - Ticket (Boleta)", description = "Finance Module")
@RestController
@RequestMapping(TICKET)
public class TicketController {

    public static final String TICKET = "/tickets";

    public static final String TICKET_ID = "ticket_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + TICKET_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + TICKET_ID + "}";
    public static final String DELETE = "/delete/{" + TICKET_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String DETAIL_ID = "detail_id";

    public static final String ADD_DETAIL = "/{" + TICKET_ID + "}/detail/add";
    public static final String EDIT_DETAIL = "/{" + TICKET_ID + "}/detail/edit/{" + DETAIL_ID + "}";
    public static final String DELETE_DETAIL = "/{" + TICKET_ID + "}/detail/delete/{" + DETAIL_ID + "}";
    public static final String FIND_DETAIL = "/{" + TICKET_ID + "}/detail/find/{" + DETAIL_ID + "}";
    public static final String FIND_ALL_DETAIL = "/{" + TICKET_ID + "}/detail/find-all";

    public static final String FIND_DATA_UPDATED = "/{" + TICKET_ID + "}/find-data-updated";

    public static final String EXPORT_DOCUMENT = "/{" + TICKET_ID + "}/export-ticket/pdf";

    @Autowired
    private TicketUseCase ticketUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create ticket.",
            description = "Create ticket."
    )
    public ApiResponse create(
            @RequestBody TicketCreateRequest request) {
        return ApiResponse.success(
                ticketUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find ticket by id.",
            description = "Find ticket by id."
    )
    public ApiResponse findBy(
            @PathVariable(TICKET_ID) String id) {
        return ApiResponse.success(
                ticketUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all tickets.",
            description = "Find all tickets."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                ticketUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit ticket.",
            description = "Edit ticket."
    )
    public ApiResponse edit(
            @PathVariable(TICKET_ID) String id,
            @RequestBody TicketUpdateRequest request) {
        return ApiResponse.success(
                ticketUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete ticket.",
            description = "Delete ticket."
    )
    public ApiResponse deleteById(
           @PathVariable(TICKET_ID) String id) {
        ticketUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate tickets.",
            description = "Search all tickets."
    )
    public ApiResponse searchPaginate(
            @RequestBody TicketSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                ticketUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate tickets.",
            description = "Search filter paginate tickets."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody TicketSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                ticketUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_DETAIL)
    @Operation(
            summary = "add detail.",
            description = "Add detail."
    )
    public ApiResponse addDetail(
            @PathVariable(TICKET_ID) String idTicket,
            @RequestBody DocumentFinanceDetailCreateRequest request) {
        return ApiResponse.success(
                ticketUseCase.addDocumentFinanceDetail(request, idTicket)
        );
    }

    @PutMapping(EDIT_DETAIL)
    @Operation(
            summary = "Edit detail .",
            description = "Edit detail."
    )
    public ApiResponse editDetail(
            @PathVariable(TICKET_ID) String idTicket,
            @PathVariable(DETAIL_ID) String idDetail,
            @RequestBody DocumentFinanceDetailUpdateRequest request) {
        return ApiResponse.success(
                ticketUseCase.editDocumentFinanceDetail(idDetail, request, idTicket)
        );
    }

    @DeleteMapping(DELETE_DETAIL)
    @Operation(
            summary = "Delete detail.",
            description = "Delete detail."
    )
    public ApiResponse deleteDetail(
            @PathVariable(TICKET_ID) String idTicket,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        ticketUseCase.deleteDocumentFinanceDetail(idDetail, idTicket);
        return ApiResponse.success();
    }

    @GetMapping(FIND_DETAIL)
    @Operation(
            summary = "Find detail by id and ticket.",
            description = "Find detail by id and ticket."
    )
    public ApiResponse findDetailByIdAndTicket(
            @PathVariable(TICKET_ID) String idTicket,
            @PathVariable(DETAIL_ID) String idDetail
    ) {
        return ApiResponse.success(
                ticketUseCase.findByIdAndDocumentFinance(idDetail, idTicket)
        );
    }

    @GetMapping(FIND_ALL_DETAIL)
    @Operation(
            summary = "Find all detail by ticket.",
            description = "Find all detail by ticket."
    )
    public ApiResponse findAllDetailByTicket(
            @PathVariable(TICKET_ID) String idTicket
    ) {
        return ApiResponse.success(
                ticketUseCase.findAllDocumentFinanceDetailByDocumentFinance(idTicket)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by ticket.",
            description = "Find data updated by ticket."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(TICKET_ID) String idTicket) {
        return ApiResponse.success(
                ticketUseCase.getTicketModificationData(idTicket)
        );
    }

    @GetMapping(EXPORT_DOCUMENT)
    @Operation(
            summary = "Export ticket.",
            description = "Export ticket."
    )
    public ResponseEntity<Resource> exportTicketPdf(
            @PathVariable(TICKET_ID) String idTicket
    ) {
        byte[] reportContent = ticketUseCase.exportTicketPdf(idTicket);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("ticket_report.pdf").build().toString())
                .body(resource);
    }
}
